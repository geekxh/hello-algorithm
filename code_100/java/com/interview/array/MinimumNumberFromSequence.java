package com.interview.array;

import java.util.Arrays;

/**
 * Date 02/26/2016
 * @author Tushar Roy
 *
 * Time complexity : O(n^2)
 * Space complexity : O(n)
 *
 * Reference
 * http://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
 */
public class MinimumNumberFromSequence {

    public int[] find(char[] input) {
        int[] output = new int[input.length + 1];
        output[0] = 1;
        int low = 0;
        int start = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'D') {
                output[i + 1] = output[i] - 1;
                if (output[i+1] == low) {
                    for (int j = start; j <= i + 1; j++) {
                        output[j] = output[j] + 1;
                    }
                }
            } else {
                low = output[start];
                output[i + 1] = low + 1;
                start = i + 1;
            }
        }
        return output;
    }

    public static void main(String args[]) {
        MinimumNumberFromSequence ms = new MinimumNumberFromSequence();
        int output[] = ms.find("DDIDDIID".toCharArray());
        System.out.println(Arrays.toString(output));

        output = ms.find("IIDDD".toCharArray());
        System.out.println(Arrays.toString(output));

        output = ms.find("DIDI".toCharArray());
        System.out.println(Arrays.toString(output));

    }
}
