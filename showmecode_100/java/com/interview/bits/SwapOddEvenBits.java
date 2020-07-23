package com.interview.bits;

/**
 * Exercise 5.6 150 qs
 */
public class SwapOddEvenBits {

    public int swap(int num){
        int mask1 = 0xAAAAAAAA;
        int mask2 = 0x55555555;
        return (num << 1 & mask1) | ( num >> 1 & mask2);
    }
    
    public static void main(String args[]){
        SwapOddEvenBits soe = new SwapOddEvenBits();
        System.out.println(soe.swap(697));
    }
}
