package com.chenxt.bootdemo.concurrent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
public class ThreadTest {
    @Test
    public void threadTest() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Test
    public void runnableTest() {
        new Thread(new MyRunnable()).start();
    }

    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> submit = executorService.submit(new MyCallable());
        while (!submit.isDone()) {
        }
        System.out.println(submit.get());
    }

    class MyThread extends Thread {
        private int i = 0;

        @Override
        public void run() {
            while (i++ < 10) {
                System.out.println(i);
            }
        }
    }

    class MyRunnable implements Runnable {
        private int i = 0;

        @Override
        public void run() {
            while (i++ < 10) {
                System.out.println(i);
            }
        }
    }

    class MyCallable implements Callable<Integer> {
        private int i = 0;
        private int sum = 0;

        @Override
        public Integer call() throws Exception {
            while (i++ < 100) {
                sum += i;
            }
            return sum;
        }
    }
}
