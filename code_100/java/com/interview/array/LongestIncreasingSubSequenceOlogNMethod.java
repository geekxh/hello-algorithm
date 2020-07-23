package com.interview.array;

import java.util.Arrays;

/**
 * Date 08/01/2015
 * @author Tushar Roy
 *
 * Given an array, find longest increasing subsequence in nlogn time complexity
 *
 * References
 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 * http://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/
 */
public class LongestIncreasingSubSequenceOlogNMethod {

    /**
     * Returns index in T for ceiling of s
     */
    private int ceilIndex(int input[], int T[], int end, int s){
        int start = 0;
        int middle;
        int len = end;
        while(start <= end){
            middle = (start + end)/2;
            if(middle < len && input[T[middle]] < s && s <= input[T[middle+1]]){
                return middle+1;
            }else if(input[T[middle]] < s){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }
    
    public int longestIncreasingSubSequence(int input[]){
        int T[] = new int[input.length];
        int R[] = new int[input.length];
        for(int i=0; i < R.length ; i++) {
            R[i] = -1;
        }
        T[0] = 0;
        int len = 0;
        for(int i=1; i < input.length; i++){
            if(input[T[0]] > input[i]){ //if input[i] is less than 0th value of T then replace it there.
                T[0] = i;
            }else if(input[T[len]] < input[i]){ //if input[i] is greater than last value of T then append it in T
                len++;
                T[len] = i;
                R[T[len]] = T[len-1];
            }else{ //do a binary search to find ceiling of input[i] and put it there.
                int index = ceilIndex(input, T, len,input[i]);
                T[index] = i;
                R[T[index]] = T[index-1];
            }
        }

        //this prints increasing subsequence in reverse order.
        System.out.print("Longest increasing subsequence ");
        int index = T[len];
        while(index != -1) {
            System.out.print(input[index] + " ");
            index = R[index];
        }

        System.out.println();
        return len+1;
    }
    
    public static void main(String args[]){
        //int input[] = {2,5,3,1,2,10,6,7,8};
        int input[] = {3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
        LongestIncreasingSubSequenceOlogNMethod lis = new LongestIncreasingSubSequenceOlogNMethod();
        System.out.println("Maximum length " + lis.longestIncreasingSubSequence(input));
    }
}
