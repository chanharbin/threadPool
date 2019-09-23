package com.crc.threadPool.service;


/**
 * DCL 双重检测机制懒汉式
 */
public class Singleton {

    //重排序问题
    private static volatile Singleton singleton;

    private Singleton(){
    }


    public static Singleton getInstance(){
        //提升性能
        if(singleton == null){
            //将锁的范围缩小
            synchronized (Singleton.class){
                //再次判断是否为空，防止产生多个对象实例
                if(singleton == null){
                    Singleton singleton =  new Singleton();
                }
            }
        }
        return singleton;
    }
}
