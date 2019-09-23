package com.crc.threadPool.executor;

import com.crc.threadPool.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class VisiableThreadPoolTaskExecutorTest {
    @Test
    public void test(){
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,queue,handler);

        for(int i = 0;i<16;i++){
            //executor.execute(new Thread(new ThreadTest(),"Thread".concat(i + "")));
            executor.execute(new ThreadTest());
            System.out.println("线程池中活跃的线程数： " + executor.getPoolSize());
            if (queue.size() > 0)
            {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        executor.shutdown();
    }

}