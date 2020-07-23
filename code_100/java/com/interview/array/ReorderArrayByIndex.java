package com.interview.array;

import java.util.Arrays;

/**
 * Date 12/29/2015
 * @author Tushar Roy
 *
 * Given two arrays one with values and other with index where values should be positioned. Move values to correct
 * position
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/reorder-a-array-according-to-given-indexes/
 */
public class ReorderArrayByIndex {
    public void reorder(int input[], int index[]) {
        if(index.length != input.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0 ; i < index.length; i++) {
            while (index[i] != i) {
                int sIndex = index[index[i]];
                int sVal = input[index[i]];

                index[index[i]] = index[i];
                input[index[i]] = input[i];

                index[i] = sIndex;
                input[i] = sVal;
            }
        }
    }

    public static void main(String args[]) {
        int input[] = {50, 40, 70, 60, 90};
        int index[] = {3,  0,  4,  1,  2};
        ReorderArrayByIndex reorderArrayByIndex = new ReorderArrayByIndex();
        reorderArrayByIndex.reorder(input, index);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(index).forEach(i -> System.out.print(i + " "));
    }
}
