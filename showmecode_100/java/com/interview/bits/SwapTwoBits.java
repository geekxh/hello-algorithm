package com.interview.bits;

/**
 * http://www.careercup.com/question?id=17542662
 */
public class SwapTwoBits {

    public int swap(int num,int i, int j){
        int t1 = (num & 1<<j) != 0 ? 1 : 0;
        int t2 = (num & 1<<i) !=0 ? 1 : 0;
        num = num & ~(1<<j);
        num = num & ~(1<<i);
        num = num | t1<<i;
        num = num | t2<<j;
        return num;
    }
    
    public int betterSwap(int num,int i,int j){
        if(((num & 1<<i)>>i ^ (num & 1<<j)>>j)  != 0){
            num ^= 1<<i;
            num ^= 1<<j;
        }
        return num;
    }
    
    public static void main(String args[]){
        SwapTwoBits stb = new SwapTwoBits();
        System.out.println(stb.betterSwap(172, 2, 4));
    }
}
