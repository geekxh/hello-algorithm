package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
 */
public class MaximumProductCutting {

    public int maxProduct(int num){
        int T[] = new int[num+1];
        T[0] = 1;
        for(int i=1; i <= num; i++){
            T[i] = i;
        }
        for(int i=2; i <= num; i++){
            for(int j=1; j <= i; j++){
                T[i] = Math.max(T[i],T[j]*T[i-j]);
            }
        }
        return T[num];
    }
    
    public static void main(String args[]){
        MaximumProductCutting mpc = new MaximumProductCutting();
        System.out.println(mpc.maxProduct(13));
    }
}
