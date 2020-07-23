package com.interview.array;

import java.util.Arrays;

/**
 * Date 12/30/2015
 * @author Tushar Roy
 *
 * Given an array of unique elements rearrange the array to be a < b > c < d > e form
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/
 */
public class ZigZagArrangement {

    public void rearrange(int input[]) {
        boolean isLess = true;
        for (int i = 0; i < input.length - 1; i++) {
            if(isLess) {
                if (input[i] > input[i+1]) {
                    swap(input, i, i+1);
                }
            } else {
                if (input[i] < input[i+1]) {
                    swap(input, i, i+1);
                }
            }
            isLess = !isLess;
        }
    }

    private void swap(int input[], int i, int j) {
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }

    public static void main(String args[]) {
        int input[] = {4, 3, 2, 6, 7, 1, 9};
        ZigZagArrangement zza = new ZigZagArrangement();
        zza.rearrange(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
    }
}
