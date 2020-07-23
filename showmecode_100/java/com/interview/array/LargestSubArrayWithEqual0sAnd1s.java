package com.interview.array;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
 * Test cases
 * Starting with either 0 or 1
 * Maximum length of 0 1 2 or more
 * 
*/
public class LargestSubArrayWithEqual0sAnd1s {

    public int equalNumber(int arr[]){

        int sum[] = new int[arr.length];
        sum[0] = arr[0] == 0? -1 : 1;
        for(int i=1; i < sum.length; i++){
            sum[i] = sum[i-1] + (arr[i] == 0? -1 : 1);
        }
        
        Map<Integer,Integer> pos = new HashMap<Integer,Integer>();
        int maxLen = 0;
        int i = 0;
        for(int s : sum){
            if(s == 0){
                maxLen = Math.max(maxLen, i+1);
            }
            if(pos.containsKey(s)){
                maxLen = Math.max(maxLen, i-pos.get(s));
            }else{
                pos.put(s, i);
            }
            i++;
        }
        return maxLen;
    }
    
    public static void main(String args[]){
        int arr[] = {0,0,0,1,0,1,0,0,1,0,0,0};
        LargestSubArrayWithEqual0sAnd1s mse = new LargestSubArrayWithEqual0sAnd1s();
        System.out.println(mse.equalNumber(arr));
    }
    
}
