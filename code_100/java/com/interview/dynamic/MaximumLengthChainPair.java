package com.interview.dynamic;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
 */
public class MaximumLengthChainPair {

    static class Pair implements Comparable<Pair>{
        public Pair(int a,int b){
            this.a = a;
            this.b = b;
        }
        int a;
        int b;
        @Override
        public int compareTo(Pair o) {
            if(this.a <= o.a){
                return -1;
            }else{
                return 1;
            }
        }
    }
    
    public int maxLength(Pair[] arr){
        Arrays.sort(arr);
        
        int T[] = new int[arr.length+1];
        for(int i=0; i < T.length; i++){
            T[i] = 1;
        }
        for(int i=2; i < T.length; i++){
            for(int j=1; j < i; j++){
                if(arr[j-1].b < arr[i-1].a){
                    T[i] = Math.max(T[j] + 1, T[i]);
                }
            }
        }
        
        int max =0 ;
        for(int i=1; i <T.length; i++){
            if(max < T[i]){
                max = T[i];
            }
        }
        return max;
    }
    
    public static void main(String args[]){
        Pair p = new Pair(5,7);
        Pair p1 = new Pair(2,3);
        Pair p2 = new Pair(2,6);
        Pair p3 = new Pair(9,11);
        Pair p4 = new Pair(8,10);
        Pair p5 = new Pair(11,14);
        
        Pair[] arr = {p,p1,p2,p3,p4,p5};
        MaximumLengthChainPair mlcp = new MaximumLengthChainPair();
        int r = mlcp.maxLength(arr);
        System.out.print(r);
    }
}
