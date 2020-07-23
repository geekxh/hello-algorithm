package com.interview.multithreaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date 03/05/2015
 * @author tusroy
 * 
 * Write a program which keeps min and max value in threadsafe way
 * Support 2 apis
 * void updateMaxMin(int val)
 * int getMin()
 * int getMax()
 * 
 * Solution
 * Use compareAndSet method of AtomicInteger to update min and max
 *
 * @ThreadSafe
 */
public class MinMaxKeeper {

    private AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    private AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    
    /**
     * Threadsafe way of updating min and max
     * @param value
     */
    public void updateMinMax(int value){
        //update min
        while(true){
            //if value is greater than whatever is in min just break right away
            int minVal = min.get();
            if(value >= minVal){
                break;
            }
            //try to update value only if minVal is in min
            boolean isSetSuccesful = min.compareAndSet(minVal, value);
            //if set was successful break from while loop else keep looping
            if(isSetSuccesful){
                break;
            }
        }
        
        //update max
        while(true){
            int maxVal = max.get();
            if(value <= maxVal){
                break;
            }
            boolean isSetSuccesful = max.compareAndSet(maxVal, value);
            if(isSetSuccesful){
                break;
            }
        }
    }
    
    public int getMin(){
        return min.get();
    }
    
    public int getMax(){
        return max.get();
    }
    
    public static void main(String args[]) throws InterruptedException{
        ExecutorService executors = Executors.newFixedThreadPool(100);
        MinMaxKeeper mmKeeper = new MinMaxKeeper();
        
        for(int i=0 ; i < 100000; i++){
            GenerateRand rand = new GenerateRand(mmKeeper, i);
            executors.execute(rand);
        }
        
        executors.shutdown();
        executors.awaitTermination(10, TimeUnit.SECONDS);
        
        assert mmKeeper.getMin() == -1;
        assert mmKeeper.getMax() == 1000001;
    }

    static class GenerateRand implements Runnable{
        int index = 0;
        MinMaxKeeper mmKeeper;
        public GenerateRand(MinMaxKeeper mmKeeper, int index) {
            this.index = index;
            this.mmKeeper = mmKeeper;
        }
        @Override
        public void run() {
            int rand = ThreadLocalRandom.current().nextInt(1000000);
            if(index == 999){
                rand = -1;
            }
            if(index == 1001){
                rand = 1000001;
            }
            mmKeeper.updateMinMax(rand);
        }
        
    }
}
