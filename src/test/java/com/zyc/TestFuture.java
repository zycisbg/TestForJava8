package com.zyc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by zyc on 17/5/16.
 */
public class TestFuture {

    /**
     *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *  newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *  newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     *  newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    ExecutorService executorService = Executors.newCachedThreadPool();



    /**
     * CompletableFuture 的使用
     * @throws Exception
     */

    @Test
    public void test2() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            int count = 0;
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + i);
                count += i;
            }
            return count;
        }, executorService);

        Integer integer = integerCompletableFuture.get();
        System.out.println(integer);
    }

    @Test
    public void test1() throws Exception{
        CompletableFuture<Integer> integerCompletableFuture = new CompletableFuture<>();

        new Thread(() -> {
            int count = 0;
            for(int i=0; i<1000;i++){
                System.out.println(Thread.currentThread().getName()+i);
                count+=i;
            }
            integerCompletableFuture.complete(count);
        }).start();
        int i=1;
        while (!integerCompletableFuture.isDone()){
            System.out.println(Thread.currentThread().getName()+i++);
        }
//        if(integerCompletableFuture.isDone()){
//            System.out.println(integerCompletableFuture.get());
//        }

    }

    public Future<Integer> getCount(final Integer integer){

        CompletableFuture<Integer> completableFuture = new CompletableFuture();
        new Thread(() ->{
            int count = 0;
            for(int i=0; i<integer;i++){
                count+=i;
                System.out.println(i);
            }
            completableFuture.complete(count);
        });

        return completableFuture;

    }

    /**
     * 异步 多线程
     * @throws Exception
     */
    @Test
    public void test() throws Exception{


        //创建一个线程异步做事情
        Future<Integer> submit = executorService.submit(() -> {
            int count = 0;
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + "," + i);
                count += i;
            }
            return count;
        });

        //主线程做的事情
        int count =0 ;
        for (int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+","+i);
            count+=i;
        }
        Integer integer = submit.get(2, TimeUnit.SECONDS);
        System.out.println(count);
        System.out.println(integer);
    }
}
