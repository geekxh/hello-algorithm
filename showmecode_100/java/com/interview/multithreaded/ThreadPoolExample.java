package com.interview.multithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolExample {

    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
    public static ExecutorService threadPool =  Executors.newFixedThreadPool(5);
    public void doWork() throws Exception{
        CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPool);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for(int i=0; i < 20; i++){
            futureList.add(completionService.submit(new Count10(i)));
        }
        for(int i=0; i < 20; i++){
            Future<String> future = completionService.take();
            System.out.println(future.get());
        }
    }
    
    public static void main(String args[]) throws Exception{
        ThreadPoolExample tpe = new ThreadPoolExample();
        tpe.doWork();
    }
    
}

class Count10 implements Callable<String>{

    private int index = 0;
    Count10(int i){
        index = i;
    }
    @Override
    public String call() throws Exception {
  //      for(int i=0; i < 10; i++){
            int sleepTime = 5000 + 1;
            try {
             
                System.out.println("Before sleep " + index);
                Thread.sleep(sleepTime);
                System.out.println("After sleep " + index);
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 //       }
        return "Done" + index;
    }
    
}
