package com.interview.multithreaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Date 03/05/2015
 * @author tusroy
 * 
 * Write a program which fills up boolean matrix from top left to bottom right with true. 
 * This program should support two apis
 * 1) void updateMatrix() which updates last position of matrix with true
 * 2) boolean getVal(int x,int y) return boolean val at matrix[x][y]
 * 
 * This program should be threadsafe.
 * 
 * Solution
 * Use AtomicLong to increment the value and return old value.
 * 
 * Test cases
 * 1) Try with single thread
 * 2) Try with multiple threads and big matrix size.
 *
 */
public class FillupMatrix {

    private boolean matrix[][];
    private int size;
    private AtomicLong pos;
    public FillupMatrix(int size){
        matrix = new boolean[size][size];
        this.size = size;
        pos = new AtomicLong(-1);
    }
    
    public void updateMatrix(){
        long pos = next();
        updateMatrix(pos);
    }
    
    private void updateMatrix(long pos){
        if(pos >= size*size){
            throw new IllegalArgumentException("Out of memory");
        }
        matrix[(int)(pos/size)][(int)(pos%size)] = true;
    }
    
    private long next(){
        long val = pos.incrementAndGet();
        return val;
    }
    
    public boolean getVal(int x, int y){
        return matrix[x][y];
    }
    
    public static void main(String args[]) throws InterruptedException{
        int size = 5000;
        FillupMatrix fum = new FillupMatrix(size);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i=0; i < size*size ; i++){
            executor.execute(() -> fum.updateMatrix());
        }
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        for(int i=0; i < size ; i++){
            for(int j=0; j < size; j++){
                assert fum.getVal(i, j);
            }
        }
    }
}
