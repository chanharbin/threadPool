package com.crc.threadPool.executor;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0;i<5;i++){
            Future<?> submit = executorService.submit(new ThreadTest());
        }
        executorService.shutdown();
    }
}
