package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/longest-even-length-substring-sum-first-second-half/
 * Test cases
 * Even length string
 * odd length string
 * 0 length string
 */
public class LongestEvenLengthSubstringOfEqualHalf {
    
    public int findMaxLength(int input[]){
        assert input != null;
        int T[][] = new int[input.length][input.length];
        
        for(int i=0; i < T.length; i++){
            T[i][i] = input[i];
        }
        int max = 0;
        for(int len = 2; len <= input.length; len++){
            for(int i=0; i < input.length - len+1; i++){
                int j = i + len-1;
                //updating sum for each lenght
                T[i][j] = T[i][j-1] + input[j];
                
                if(len % 2 == 0){
                    int k = (i + j)/2;
                    if(T[i][k] == T[k+1][j]){
                        if(max < len){
                            max = len;
                        }
                    }
                }
            }
        }
        return max;
    }
    
    public static void main(String args[]){
        int input[] = {1,5,3,8,0,2,14,9};
        LongestEvenLengthSubstringOfEqualHalf lel = new LongestEvenLengthSubstringOfEqualHalf();
        System.out.println(lel.findMaxLength(input));
        
    }
}
