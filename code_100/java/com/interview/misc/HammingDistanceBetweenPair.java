package com.interview.misc;

/**
 * http://www.glassdoor.com/Interview/-a-first-write-a-function-to-calculate-the-hamming-distance-between-two-binary-numbers-b-write-a-function-that-takes-QTN_450885.htm
 * Test cases
 * Not equal length strings
 * String containing anything other than 0 and 1
 */
public class HammingDistanceBetweenPair {

    public int hammingDistance(String input[]){
        int size = input[0].length();
        int total = 0;
        for(int i=0; i < size; i++){
            int count0s = 0;
            int count1s = 0;
            for(String str : input){
                if(str.charAt(i) == '0'){
                    count0s++;
                }else{
                    count1s++;
                }
            }
            total += count0s * count1s;
        }
        return total;
    }
    
    public static void main(String args[]){
        String input[] = {"10011","00011","11101","01010"};
        HammingDistanceBetweenPair hdb = new HammingDistanceBetweenPair();
        System.out.println(hdb.hammingDistance(input));
    }
}
