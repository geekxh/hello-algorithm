package com.interview.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 */
public class MaximumSumThreeNonOverlappingSubarray {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum = 0;
        int[] sumArray = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                sum += nums[i];
            } else {
                sumArray[i - k] = sum;
                sum += nums[i];
                sum -= nums[i - k];
            }
        }
        sumArray[sumArray.length - 1] = sum;

        int[][] dp = new int[4][sumArray.length + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= sumArray.length; j++) {
                if (j >= k) {
                    if (dp[i][j - 1] >= sumArray[j - 1] + dp[i - 1][j - k]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = sumArray[j - 1] + dp[i - 1][j - k];
                    }
                } else {
                    if (dp[i][j - 1] >= sumArray[j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = sumArray[j - 1];
                    }
                }
            }
        }
        int[] output = new int[3];
        int j = dp[0].length - 1;
        for (int i = 3; i > 0;) {
            if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                output[i - 1] = j - 1;
                i--;
                j = j - k;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        MaximumSumThreeNonOverlappingSubarray mss = new MaximumSumThreeNonOverlappingSubarray();
        int[] input = {3, 2, 2, 1, 1, 0, 5};
        int[] input1 = {1, 2, 1, 2, 6, 7, 5, 1};
        int[] output = mss.maxSumOfThreeSubarrays(input1, 2);
        for (int i : output) {
            System.out.println(i);
        }
    }
}
