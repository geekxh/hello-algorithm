package com.interview.recursion;

/**
 * Date 02/25/2016
 * @author Tushar Roy
 *
 * Print all subsequence of a given array.
 *
 * Time complexity is exponential
 * Space complexity is O(n)
 */
public class PrintAllSubsequence {
    public void print(int[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[0] = input[i];
            print(input, output, 1, i + 1, true);
        }
    }

    private void print(int[] input, int[] output, int len, int current, boolean print) {

        if (print) {
            for (int i = 0; i < len; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        }
        if (current == input.length) {
            return;
        }


        output[len] = input[current];
        print(input, output, len + 1, current + 1, true);
        print(input, output, len, current + 1, false);
    }

    public static void main(String args[]) {
        PrintAllSubsequence ps = new PrintAllSubsequence();
        int[] input = {1, 2, 3, 4};
        ps.print(input);
    }
}
