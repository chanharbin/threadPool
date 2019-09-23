package com.crc.threadPool.service;
import java.lang.Thread;

public class ThreadDemoForSyn {
    private boolean ready = false;
    private int result = 0;
    private int number = 1;

    public synchronized void write(){
        ready = true;
        number = 2;
    }

    public synchronized int read(){
        if(ready){
            result = number * 3;
        }
        return result;
    }

    private class ReadWriteThread extends Thread {
        boolean flag = false;

        ReadWriteThread(boolean flag){
            this.flag = flag;
        }
        @Override
        public void run() {
            if (flag) {
                write();
            } else {
                int read = read();
                if(read == 3){
                    System.out.println("可见性问题！！！！！！！！！");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemoForSyn demo = new ThreadDemoForSyn();
        for(int i =0;i<100000;i++) {
            demo.new ReadWriteThread(true).start();
            demo.new ReadWriteThread(false).start();
        }
    }

}
