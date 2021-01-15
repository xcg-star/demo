package com.chenxt.bootdemo.concurrent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

/**
 * 使用链表以及wait/notifyAll实现生产者消费者模式
 */
@RunWith(SpringRunner.class)
public class BlockQueueTest {
    @Test
    public void blockQueueTest() throws InterruptedException {
        MyBlockQueue myBlockQueue = new MyBlockQueue();
        new Thread(() -> {
            for (int i = 0; i < 32; i++) {
                try {
                    myBlockQueue.add(i);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 32; i++) {
                try {
                    myBlockQueue.take();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(5000);
    }

    class MyBlockQueue {
        private int maxSize = 16;
        private LinkedList<Integer> list = new LinkedList<>();

        private void take() throws InterruptedException {
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                Integer item = list.removeFirst();
                System.out.println("消费了" + item);
                notifyAll();
            }
        }

        private void add(Integer item) throws InterruptedException {
            synchronized (this) {
                while (list.size() >= maxSize) {
                    wait();
                }
                list.add(item);
                System.out.println("生产了" + item);
                notifyAll();
            }
        }
    }
}
