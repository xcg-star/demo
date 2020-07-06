package com.chenxt.bootdemo.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通用线程池
 *
 * @author chenxt
 * @date 2020/07/06
 */
public class CommonThreadPool {
    static ThreadPoolExecutor executor = null;
    static BlockingQueue<Runnable> workQueue = null;
    private static final Logger log = LoggerFactory.getLogger(CommonThreadPool.class);
    /**
     * 队列容量
     */
    final static int THREAD_QUEUE_SIZE = 1000;
    /**
     * 池中线程数
     */
    final static int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数
     */
    final static int MAX_POOL_SIZE = 15;

    static void init() {
        try {
            if (workQueue == null) {
                workQueue = new ArrayBlockingQueue<>(THREAD_QUEUE_SIZE);
            }
            if (executor == null) {
                executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 30, TimeUnit.SECONDS, workQueue);
                executor.allowCoreThreadTimeOut(true);
            }
        } catch (Exception t) {
            log.error("初始化线程池时出现异常......" + t.getMessage(), t);
        }
    }

    public static void execute(Runnable runnable) {
        if (executor == null || workQueue == null) {
            init();
        }
        executor.execute(runnable);
    }

}
