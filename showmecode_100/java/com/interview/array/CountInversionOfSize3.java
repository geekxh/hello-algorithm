package com.interview.array;

/**
 * Date 12/29/15
 * @author Tushar Roy
 *
 * Given input array find number of inversions where i < j < k and input[i] > input[j] > input[k]
 *
 * http://www.geeksforgeeks.org/count-inversions-of-size-three-in-a-give-array/
 */
public class CountInversionOfSize3 {

    /**
     * Time complexity of this method is O(n^2)
     * Space complexity is O(1)
     */
    public int findInversions(int input[]) {
        int inversion = 0;
        for (int i = 1; i < input.length - 1 ; i++) {
            int larger = 0;
            for (int k = 0; k < i; k++) {
                if (input[k] > input[i]) {
                    larger++;
                }
            }
            int smaller = 0;
            for (int k = i+1; k < input.length; k++) {
                if (input[k] < input[i]) {
                    smaller++;
                }
            }
            inversion += smaller*larger;
        }
        return inversion;
    }

    public static void main(String args[]) {
        int input[] = {9, 6, 4, 5, 8};
        CountInversionOfSize3 ci = new CountInversionOfSize3();
        System.out.print(ci.findInversions(input));
    }
}
