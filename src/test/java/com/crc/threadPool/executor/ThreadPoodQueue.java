package com.crc.threadPool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoodQueue {
    public static void main(String[] args) {
        /**
         * ArrayBlockingQueue，数组实现的有界队列
         * LinkedBlockingQueue，可设置容量的链表实现的队列，不设置容量时为无限队列，最大长度为Integer.Maxvalue
         * SynchronizedQueue,一个不存储元素的阻塞队列，插入操作必须等待另一个线程调用移除操作，否则会一直被阻塞
         */
        //FixedThreadPool
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for(int i =0; i<Integer.MAX_VALUE;i++){
//            executorService.submit(new ThreadTest());
//        }
        //Synchronized
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(10000);
//                        System.out.println(Thread.currentThread().getName() + "正在执行");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }
        //SingleThreadExecutor
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(new ThreadTest());
//        }
//    }
        //ScheduledThreadPool
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(new ThreadTest(), 1, 3, TimeUnit.SECONDS);
    }
}
