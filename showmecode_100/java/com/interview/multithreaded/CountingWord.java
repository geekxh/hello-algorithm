package com.interview.multithreaded;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Date 03/05/2015
 * @author tusroy
 * 
 * Write a program to count words. This program should be threadsafe.
 * Implement two apis 
 * 1) void addWord(String word) -> increment count of this word
 * 2) long getCount(String word) -> get count of this word
 * 
 * Solution
 * Keep a concurrent map. Key to this map should be word while value should be AtomicLong to update it
 * in threadsafe way
 * 
 * Test cases
 * One word updated by many threads
 * Many words updated by many threads
 *
 *@Threadsafe
 */
public class CountingWord {

    private ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<>();
   
    public void addWord(String word){
        AtomicLong l = map.get(word);
        if(l == null){
            l = new AtomicLong(1);
            l = map.putIfAbsent(word, l);
            if(l != null){
                l.incrementAndGet();
            }
        }else{
            l.incrementAndGet();
        }
    }
    
    public long getCount(String word){
        AtomicLong l = map.get(word);
        if(l != null){
            return l.longValue();
        }
        return 0;
    }
    
    public static void main(String args[]) throws InterruptedException{
        ExecutorService executor1 = Executors.newFixedThreadPool(20);
        ExecutorService executor2 = Executors.newFixedThreadPool(20);
        ExecutorService executor3 = Executors.newFixedThreadPool(20);
     
        int total = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(3*total);
        CountingWord cw = new CountingWord();
        for(int i= 0; i < total; i++){
            executor1.execute(() -> cw.addWord("word1"));
            countDownLatch.countDown();
        }
        for(int i= 0; i < total; i++){
            executor2.execute(() -> cw.addWord("word2"));
            countDownLatch.countDown();
        }
        for(int i= 0; i < total; i++){
            executor3.execute(() -> cw.addWord("word3"));
            countDownLatch.countDown();
        }
        
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw e;
        }
        executor1.shutdownNow();
        executor2.shutdownNow();
        executor3.shutdownNow();
        
        long count1 = cw.getCount("word1");
        long count2 = cw.getCount("word2");
        long count3 = cw.getCount("word3");
        assert count1 == total;
        assert count2 == total;
        assert count3 == total;
        
    }
}
