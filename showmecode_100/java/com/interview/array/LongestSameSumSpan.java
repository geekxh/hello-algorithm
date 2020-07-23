package com.interview.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 12/29/2015
 * @author Tushar Roy
 *
 * Give two arrays of same size consisting of 0s and 1s find span (i, j) such that
 * sum of input1[i..j] = sum of input2[i..j]
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
 */
public class LongestSameSumSpan {

    public int longestSpan(int input1[], int input2[]) {
        if (input1.length != input2.length) {
            throw new IllegalArgumentException("Not same length input");
        }
        Map<Integer, Integer> diff = new HashMap<>();
        int prefix1 = 0, prefix2 = 0;
        int maxSpan = 0;
        diff.put(0, -1);
        for (int i = 0; i < input1.length ; i++) {
            prefix1 += input1[i];
            prefix2 += input2[i];
            int currDiff = prefix1 - prefix2;
            if (diff.containsKey(currDiff)) {
                maxSpan = Math.max(maxSpan, i - diff.get(currDiff));
            } else {
                diff.put(currDiff, i);
            }
        }
        return maxSpan;
    }

    public static void main(String args[]) {
        int input1[] = {1, 0, 0, 1, 1, 0};
        int input2[] = {0, 1, 1, 0, 1, 1};
        LongestSameSumSpan lsss = new LongestSameSumSpan();
        System.out.print(lsss.longestSpan(input1, input2));
    }

}
