package com.crc.threadPool.executor;

public class ThreadTest implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis());
        }
        catch (Exception e){
            System.out.println("出错！！");
        }
    }
}
