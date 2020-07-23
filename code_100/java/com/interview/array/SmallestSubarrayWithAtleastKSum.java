package com.interview.array;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 */
public class SmallestSubarrayWithAtleastKSum {

    public int shortestSubarray(int[] A, int K) {
        int[] skip = new int[A.length];

        int sum = 0;
        int start = A.length - 1;
        skip[A.length - 1] = 1;
        for (int i = A.length - 1; i > 0; i--) {
            skip[i - 1] = 1;
            sum += A[i];
            if (sum <= 0) {
                skip[i - 1] = start - i + 1;
            } else {
                start = i;
                sum = 0;
            }
        }

        start = 0;
        int end = 0;
        sum = 0;
        int min = Integer.MAX_VALUE;
        while (end < A.length) {
            sum += A[end++];
            while (start <= end && sum >= K) {
                min = Math.min(end - start, min);
                for (int j = start; j < start + skip[start]; j++) {
                    sum -= A[j];
                }
                start = start + skip[start];
            }
            if (sum <= 0) {
                start = end;
                sum = 0;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public  static void main(String[] args) {
        int[] input = {1, 3, -1, -4, -2, 3, 4, -5, -1, 8};
        SmallestSubarrayWithAtleastKSum ss = new SmallestSubarrayWithAtleastKSum();
        ss.shortestSubarray(input, 8);
    }
}
