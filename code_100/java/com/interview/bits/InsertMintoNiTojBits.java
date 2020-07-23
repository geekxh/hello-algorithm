package com.interview.bits;

/**
 * Exercise 5.1 150 qs
 */
public class InsertMintoNiTojBits {
    
    public int insert(int M,int N, int i, int j){
        int  mask = 1<<(j+1) -1;
        mask = mask<<i;
        mask = ~mask;
        
        N = N & mask;
        N = N | M << i;
        return N;
    }
    
    public static void main(String args[]){
        int N = 0;
        int M = 35;
        InsertMintoNiTojBits imn = new InsertMintoNiTojBits();
        int result = imn.insert(M,N,3,8);
        System.out.println(result);
        
    }
}
