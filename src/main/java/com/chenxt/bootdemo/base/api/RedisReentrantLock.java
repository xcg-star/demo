package com.chenxt.bootdemo.base.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Redis可重入的分布式锁。
 * 锁存储在RegistryKey下。锁过期后（默认为60）秒。线程解锁
 * 过期的锁将抛出一个IllegalStateException。这是一个严重的异常，因为过期的锁不再具备阻塞特性。
 * <b>
 * 锁由注册表限制。 来自其他注册表的锁相同的键（即使注册表使用相同的“ registryKey”）也不同锁，
 * 并且第二个线程不能获取同一个锁，而是由第一个线程锁定。
 * </b>
 *
 * @author chenxt
 * @date 2020/07/15
 */
@Slf4j
public final class RedisReentrantLock implements DisposableBean {

    /**
     * 默认过期时间 60s
     */
    private static final long DEFAULT_EXPIRE_AFTER = 60000L;

    /**
     * Lua 脚本，具有原子性
     */
    private static final String OBTAIN_LOCK_SCRIPT =
            "local lockClientId = redis.call('GET', KEYS[1])\n" +
                    "if lockClientId == ARGV[1] then\n" +
                    "  redis.call('PEXPIRE', KEYS[1], ARGV[2])\n" +
                    "  return true\n" +
                    "elseif not lockClientId then\n" +
                    "  redis.call('SET', KEYS[1], ARGV[1], 'PX', ARGV[2])\n" +
                    "  return true\n" +
                    "end\n" +
                    "return false";


    private final Map<String, RedisLock> locks = new ConcurrentHashMap<>();

    private final String clientId = UUID.randomUUID().toString();

    /**
     * 锁前缀
     */
    private final String registryKey;

    private final StringRedisTemplate redisTemplate;

    private final RedisScript<Boolean> obtainLockScript;

    /**
     * 过期时间
     */
    private final long expireAfter;

    /**
     * 一个线程池，用于在当前线程中断时在单独的线程中调用 Redis 的 DELETE 命令
     */
    private Executor executor = Executors.newCachedThreadPool(new CustomizableThreadFactory("redis-lock-registry-"));

    /**
     * 一个标记，如果通过 set 方法设置了指定的线程池，则在对象销毁的时候不需要关闭线程池
     */
    private boolean executorExplicitlySet;

    /**
     * 构造一个具有默认（60秒）锁定到期时间的锁定注册表。
     *
     * @param connectionFactory 连接工厂
     * @param registryKey       锁的键前缀
     */
    public RedisReentrantLock(RedisConnectionFactory connectionFactory, String registryKey) {
        this(connectionFactory, registryKey, DEFAULT_EXPIRE_AFTER);
    }

    /**
     * 使用提供的锁到期时间构造一个锁注册表.
     *
     * @param connectionFactory 连接工厂
     * @param registryKey       锁的键前缀
     * @param expireAfter       到期时间（以毫秒为单位）
     */
    public RedisReentrantLock(RedisConnectionFactory connectionFactory, String registryKey, long expireAfter) {
        Assert.notNull(connectionFactory, "'connectionFactory' 不能为 null");
        Assert.notNull(registryKey, "锁的键前缀必须设置");
        this.redisTemplate = new StringRedisTemplate(connectionFactory);
        this.obtainLockScript = new DefaultRedisScript<>(OBTAIN_LOCK_SCRIPT, Boolean.class);
        this.registryKey = registryKey;
        this.expireAfter = expireAfter;
    }

    /**
     * 设置专门的 DELETE 线程池
     *
     * @param executor 执行器
     */
    public void setExecutor(Executor executor) {
        this.executor = executor;
        this.executorExplicitlySet = true;
    }

    /**
     * 获取Lock对象
     *
     * @param lockKey
     * @return
     */
    public Lock obtain(String lockKey) {
        return this.locks.computeIfAbsent(lockKey, RedisLock::new);
    }

    /**
     * 清除注册表中过期未使用的锁
     *
     * @param age
     */
    public void expireUnusedOlderThan(long age) {
        Iterator<Map.Entry<String, RedisLock>> iterator = this.locks.entrySet().iterator();
        long now = System.currentTimeMillis();
        while (iterator.hasNext()) {
            Map.Entry<String, RedisLock> entry = iterator.next();
            RedisLock lock = entry.getValue();
            if (now - lock.getLockedAt() > age && !lock.isAcquiredInThisProcess()) {
                iterator.remove();
            }
        }
    }

    /**
     * 对象销毁时关闭默认线程池
     */
    @Override
    public void destroy() {
        if (!this.executorExplicitlySet) {
            ((ExecutorService) this.executor).shutdown();
        }
    }

    private final class RedisLock implements Lock {

        private final String lockKey;

        /**
         * 重入锁，搭配 jvm 重入锁实现 redis 锁的可重入性
         */
        private final ReentrantLock localLock = new ReentrantLock();

        /**
         * 标识unlink 命令是否可用
         */
        private volatile boolean unlinkAvailable = true;

        /**
         * 锁定时间
         */
        private volatile long lockedAt;

        private RedisLock(String path) {
            this.lockKey = constructLockKey(path);
        }

        private String constructLockKey(String path) {
            return registryKey + ":" + path;
        }

        public long getLockedAt() {
            return this.lockedAt;
        }

        /**
         * 一直不停的尝试加锁，阻塞，且不会被interrupted
         */
        @Override
        public void lock() {
            this.localLock.lock();
            while (true) {
                try {
                    while (!obtainLock()) {
                        Thread.sleep(100); //NOSONAR
                    }
                    break;
                } catch (InterruptedException e) {
                    /*
                     * 此方法必须是不可中断的，因此可以捕获并忽略中断，并且只有在获得锁时才退出while循环.
                     */
                } catch (Exception e) {
                    this.localLock.unlock();
                    rethrowAsLockException(e);
                }
            }
        }

        private void rethrowAsLockException(Exception e) {
            throw new CannotAcquireLockException("Failed to lock mutex at " + this.lockKey, e);
        }

        /**
         * 一直不停的尝试加锁，阻塞，但是会处理Interrupted
         *
         * @throws InterruptedException
         */
        @Override
        public void lockInterruptibly() throws InterruptedException {
            this.localLock.lockInterruptibly();
            try {
                while (!obtainLock()) {
                    Thread.sleep(100); //NOSONAR
                }
            } catch (InterruptedException ie) {
                this.localLock.unlock();
                Thread.currentThread().interrupt();
                throw ie;
            } catch (Exception e) {
                this.localLock.unlock();
                rethrowAsLockException(e);
            }
        }

        /**
         * 尝试加锁一次
         */
        @Override
        public boolean tryLock() {
            try {
                return tryLock(0, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        /**
         * 一直尝试加锁到超时为止
         *
         * @param time
         * @param unit
         * @return
         * @throws InterruptedException
         */
        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            long now = System.currentTimeMillis();
            if (!this.localLock.tryLock(time, unit)) {
                return false;
            }
            try {
                long expire = now + TimeUnit.MILLISECONDS.convert(time, unit);
                boolean acquired;
                while (!(acquired = obtainLock()) && System.currentTimeMillis() < expire) { //NOSONAR
                    Thread.sleep(100); //NOSONAR
                }
                if (!acquired) {
                    this.localLock.unlock();
                }
                return acquired;
            } catch (Exception e) {
                this.localLock.unlock();
                rethrowAsLockException(e);
            }
            return false;
        }

        private boolean obtainLock() {
            Boolean success =
                    RedisReentrantLock.this.redisTemplate.execute(RedisReentrantLock.this.obtainLockScript,
                            Collections.singletonList(this.lockKey), RedisReentrantLock.this.clientId,
                            String.valueOf(RedisReentrantLock.this.expireAfter));

            boolean result = Boolean.TRUE.equals(success);

            if (result) {
                this.lockedAt = System.currentTimeMillis();
            }
            return result;
        }

        /**
         * 解锁
         */
        @Override
        public void unlock() {
            // 只能解除自己的锁
            if (!this.localLock.isHeldByCurrentThread()) {
                throw new IllegalStateException("你没有锁定" + this.lockKey);
            }
            // 判断重入次数
            if (this.localLock.getHoldCount() > 1) {
                this.localLock.unlock();
                return;
            }
            try {
                // 判断锁是否已失效过期
                if (!isAcquiredInThisProcess()) {
                    throw new IllegalStateException("由于过期，锁已在注册表中释放.受此锁保护的数据可能已受到损害！");
                }

                if (Thread.currentThread().isInterrupted()) {
                    RedisReentrantLock.this.executor.execute(this::removeLockKey);
                } else {
                    removeLockKey();
                }

                if (log.isDebugEnabled()) {
                    log.debug("释放锁：" + this);
                }
            } catch (Exception e) {
                ReflectionUtils.rethrowRuntimeException(e);
            } finally {
                this.localLock.unlock();
            }
        }

        private void removeLockKey() {
            if (this.unlinkAvailable) {
                try {
                    RedisReentrantLock.this.redisTemplate.unlink(this.lockKey);
                } catch (Exception ex) {
                    log.warn("Redis服务器不支持 UNLINK 命令（执行失败）;使用DELETE命令重试", ex);
                    this.unlinkAvailable = false;
                    RedisReentrantLock.this.redisTemplate.delete(this.lockKey);
                }
            } else {
                RedisReentrantLock.this.redisTemplate.delete(this.lockKey);
            }
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("该实现不支持此方法！");
        }

        public boolean isAcquiredInThisProcess() {
            return RedisReentrantLock.this.clientId.equals(RedisReentrantLock.this.redisTemplate.boundValueOps(this.lockKey).get());
        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd@HH:mm:ss.SSS");
            return "RedisLock [lockKey=" + this.lockKey
                    + ",lockedAt=" + dateFormat.format(new Date(this.lockedAt))
                    + ", clientId=" + RedisReentrantLock.this.clientId
                    + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((this.lockKey == null) ? 0 : this.lockKey.hashCode());
            result = prime * result + (int) (this.lockedAt ^ (this.lockedAt >>> 32));
            result = prime * result + RedisReentrantLock.this.clientId.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            RedisLock other = (RedisLock) obj;
            if (!getOuterType().equals(other.getOuterType())) {
                return false;
            }
            if (!this.lockKey.equals(other.lockKey)) {
                return false;
            }
            return this.lockedAt == other.lockedAt;
        }

        private RedisReentrantLock getOuterType() {
            return RedisReentrantLock.this;
        }

    }

}
