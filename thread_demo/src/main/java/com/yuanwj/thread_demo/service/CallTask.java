package com.yuanwj.thread_demo.service;

import java.util.concurrent.*;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/9
 * Description:
 */
public class CallTask implements Callable<String> {

    private int count =0;

    @Override
    public String call() throws Exception {
        for (int i=0;i<1000000;i++) {

        }
        count++;
        System.out.println(Thread.currentThread().getName()+"===========");
        System.out.println(count);
        return "success";
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i=0;i<100;i++) {
                CallTask task = new CallTask();
                Future<String > future = executorService.submit(task);
                String result = future.get();
//                System.out.println(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
