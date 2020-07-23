package com.interview.number;

/**
 * Write a program to determine whether n/2 distinctive pairs can be formed 
 * from given n integers where n is even and each pair's sum is divisible by given k. 
 * Numbers cannot be repeated in the pairs, that means only you can form total n/2 pairs.
 */
public class NBy2PairSumToK {

    //assuming input is from 1 to n. If input can contain 0 special logic will be needed
    //to handle that.
    public boolean pair(int input[],int k){
        int count[] = new int[k];
        for(int i=0; i < input.length; i++){
            count[input[i]%k]++;
        }
        
        if(count[0]%2 != 0){
            return false;
        }
        if(k%2==0){
            if(count[k/2]%2 != 0){
                return false;
            }
        }
        for(int i=1; i <= k/2; i++){
            if(count[i] != count[k-i]){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        int input[] = {5,7,6,8,2,6,10,4};
        NBy2PairSumToK nb = new NBy2PairSumToK();
        System.out.println(nb.pair(input, 6));
    }
}
