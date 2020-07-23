package com.interview.number;

/**
 * You are given an array of n elements [1,2,....n]. For example {3,2,1,6,7,4,5}. Now we create a signature of this
 * array by comparing every consecutive pir of elements. If they increase, write I else write D.
 * For example for the above array, the signature would be "DDIIDI". The signature thus has a length of N-1.
 * Now the question is given a signature, compute the lexicographically smallest permutation of [1,2,....n].
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * Reference https://learn.hackerearth.com/forum/182/lexicographically-smallest-permutation-given-a-signature/
 */
public class GenerateSignature {

    public int[] generate(char[] input) {
        int n = input.length+1;
        int[] result = new int[n];
        int i;
        for (i = 0; i < n; i++) {
            result[i] = i+1;
        }
        i = 0;
        while(i < n-1) {
            int start = -1, end = -1;
            while(i < n-1 && input[i] == 'D') {
                if(start == -1) {
                    start = i;
                }
                end = i;
                i++;
            }
            if(start != -1) {
                reverse(result, start, end+1);
            }
            i++;
        }
        return result;
    }

    private void reverse(int[] result, int start, int end) {

        while(start < end) {
            int tmp = result[start];
            result[start] = result[end];
            result[end] = tmp;
            start++;
            end--;
        }
    }
    
    public static void main(String args[]){
        String input = "IIIDIIDDDDIIDDD";
        GenerateSignature gs = new GenerateSignature();
        int result[] = gs.generate(input.toCharArray());
        for(int i=0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }
    }
}
