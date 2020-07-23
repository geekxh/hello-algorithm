 package com.interview.multithreaded;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Date 06/25/2015
 * @author tusroy
 * 
 * Write a program to implement bounded blocking queue. This is similar to consumer producer problem
 * Properties of queue
 * 1) If queue is empty poll will wait with timeout till item is available
 * 2) If queue is full offer will wait with timeout till space is available
 */
public class BoundedBlockingQueue<T> {

    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;
    
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    
    /**
     * @param size - Define the size of bounded blocking queue.
     */
    public BoundedBlockingQueue(int size){
        items = new Object[size];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }
    
    /**
     * Poll an item from queue. If queue is empty wait with timeout till item is available
     * @return Optional<T> depending on if item was polled or queue was empty
     */
    public Optional<T> poll(long timeout, TimeUnit timeUnit) throws InterruptedException{
        long left = timeUnit.toNanos(timeout);
        //acquire the lock on the lock object
        lock.lockInterruptibly();
        T t;
        try{
            //if count is 0 means there is no item to poll. Keep trying to poll
            //till either item is available or left gets 0 or less which means its
            //time to time out.
            while(count == 0){
                if(left <= 0){
                    return Optional.empty();
                }
                //if queue is empty wait fir signal from notEmpty condition
                left = notEmpty.awaitNanos(timeUnit.toNanos(left));
            }
            //dequeu the item.
            t = dequeue();
            //signal notFull since queue is not full anymore
            notFull.signal();
        } finally {
            //unlock the lock object
            lock.unlock();
        }
        return Optional.of(t);
    }
    
    /**
     * Offer item to queue. If queue is full wait with timeout till space is available.
     * @param t - item to offer
     * @param timeout - time out time
     * @param timeUnit - time out unit
     * @return - returns true if item was offered in queue successfully else false.
     * @throws InterruptedException
     */
    public boolean offer(T t, long timeout, TimeUnit timeUnit) throws InterruptedException{
        if(t == null) { 
            throw new IllegalArgumentException();
        }
   
        long left = timeUnit.toNanos(timeout);
        
        //acquire lock on lock object
        lock.lockInterruptibly();
        try{
            //keep trying if you do not have space available in queue or time out is reached.
            while(count == items.length){
                if(left <= 0){
                    return false;
                }
                left = notFull.awaitNanos(timeUnit.toNanos(left));
            }
            //enqueue the item into the queue 
            enqueue(t);
            //signal notEmpty condition since queue is not empty anymore
            notEmpty.signal();
        } finally {
            //release the lock.
            lock.unlock();
        }
        return true;
    }
    
    private void enqueue(T t){
        items[putIndex] = t;
        if(++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
    }
    
    @SuppressWarnings("unchecked")
    private T dequeue() {
        T t = (T)items[takeIndex];
        items[takeIndex] = null;
        if(++takeIndex == items.length) { 
            takeIndex = 0;
        }
        count--;
        return t;
    }
    
    public static void main(String args[]) throws Exception{
        verifyQueueWorks();
    }
    
    public static void verifyQueueWorks() throws Exception{
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(30);
        ExecutorService writeExecutors = Executors.newFixedThreadPool(10);
        int TOTAL = 10000;
        AtomicInteger result[] = new AtomicInteger[TOTAL];
        AtomicInteger test = new AtomicInteger(0);
        for(int i = 0; i < TOTAL; i++) { 
            writeExecutors.execute(() -> {
                try {
                    int val = test.getAndIncrement();
                    result[val] = new AtomicInteger(1);
                    while(true){
                        if(queue.offer(val, (long)(Math.random()*100 + 1), TimeUnit.MILLISECONDS)){
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Shutting down read thread");
                }
            });
        }
        
        ExecutorService readExecutors = Executors.newFixedThreadPool(10);
        for(int i = 0; i < TOTAL; i++) { 
            readExecutors.execute(() -> {
                try {
                    while(true){
                        Optional<Integer> r = queue.poll((long)(Math.random()*1000 + 1), TimeUnit.MILLISECONDS);
                        if(r.isPresent()) {
                            result[r.get()].incrementAndGet();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Shutting down read thread");
                }
            });
        }
        
        //you can replace this with countdown latch. But I do not feel like writing all that code.
        Thread.sleep(10000);
        System.out.println("Validating result after reasonable wait");
        
        //if queue worked as expected all integers in result array should have value 2.
        for(int i=0; i < result.length; i++){
            if(result[i].get() != 2){
                throw new RuntimeException(String.valueOf(i));
            }
        }
        
        System.out.println("Shutting down executors");
        //force shutdown on read/write executor
        readExecutors.shutdownNow();
        writeExecutors.shutdownNow();
    }
}
