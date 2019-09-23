package com.crc.threadPool.executor;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadDemo {
    /**
     * thread.join
     */
 /*   public static void main(String[] args) {
        final Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 3) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A:" + "print:" + i);
                }
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                while (i++ < 3) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B: " + "print:" + i);
                }
            }
        });
        B.start();
        A.start();
    }*/

    /**
     * wait()和notify()方法同时使用并加锁控制线程执行顺序
     * 首先创建一个 A 和 B 共享的对象锁 lock = new Object();
     * 当 A 得到锁后，先打印 1，然后调用 lock.wait() 方法，交出锁的控制权，进入 wait 状态；
     * 对 B 而言，由于 A 最开始得到了锁，导致 B 无法执行；直到 A 调用 lock.wait() 释放控制权后， B 才得到了锁；
     * B 在得到锁后打印 1， 2， 3；然后调用 lock.notify() 方法，唤醒正在 wait 的 A;
     * A 被唤醒后，继续打印剩下的 2，3。
     */
  /*  public static void main(String[] args) {
        final Object lock = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("A  1");
                    try{
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A  2");
                    System.out.println("A  3");
                }
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("B  1");
                    System.out.println("B  2");
                    System.out.println("B  3");
                    lock.notify();
                }
            }
        });
        A.start();
        B.start();
    }*/
    /**
     * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
     * CountdownLatch
     */
/*    public static void main(String[] args) {
        int worker = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(worker);
        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("D is processing");
            }
        });
        for (int i =0 ;i < worker;i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.valueOf(finalI) + " worker is processing");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.valueOf(finalI) + " worker is finished");
                    countDownLatch.countDown();
                }
            }).start();
        }
        threadD.start();
    }*/

    /**
     * 三个线程准备好后一起运行
     * CyclicBarrier
     */
    public static void main(String[] args) {
        int runner = 3;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        final Random random = new Random();
        for (char runnerName = 'A';runnerName <= 'C';runnerName++){
            final char finalRunnerName = runnerName;
            final int prepareTime = random.nextInt(10000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalRunnerName + "is preparing");
                    try{
                        Thread.sleep(prepareTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(finalRunnerName + "is finished");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 在子线程中返回结果给主线程，使用calleable方法，但futureTask.get()方法会阻塞主线程
     */
    /*
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue, handler);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task start");
                Thread.sleep(10000);
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    result += 1;
                }
                System.out.println("Task finished and return result");
                return result;
            }
        };
        //FutureTask<Integer> integerFutureTask = new FutureTask<>(callable);
        Future<Integer> submit = executorService.submit(callable);
        //executor.execute(new Thread(integerFutureTask));
        //new Thread(integerFutureTask).start();
        try {
            System.out.println("Before futureTask.get()");
            //System.out.println("Result" + submit.get());
            System.out.println("After futureTask.get()");
            System.out.println("Result" + submit.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
}



