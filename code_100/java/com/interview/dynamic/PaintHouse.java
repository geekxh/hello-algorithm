package com.interview.dynamic;

/**
 * Paint House
 * https://leetcode.com/problems/paint-house/
 * https://leetcode.com/problems/paint-house-ii/
 */
public class PaintHouse {

    public int minCostTopDownPainHouse1or2(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int[][] dp = new int[costs.length][3];
        return minCostUtil(costs, 0, -1, 3, dp);
    }

    private int minCostUtil(int[][] costs, int house, int prevColor, int k, int[][] dp) {
        if (house == costs.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (i == prevColor) {
                continue;
            }
            int val;
            if (dp[house][i] != 0) {
                val = dp[house][i];
            } else {
                val = costs[house][i] + minCostUtil(costs, house + 1, i, k, dp);
                dp[house][i] = val;
            }
            min = Math.min(min, val);
        }
        return min;
    }

    public int minCostBottomUpPaintHouse2(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[costs.length][costs[0].length];
        for (int i = 0; i < costs[0].length; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < costs.length; i++) {
            Pair p = findMinSecondMin(dp[i - 1]);
            for (int j = 0; j < costs[0].length; j++) {
                dp[i][j] = costs[i][j];
                if (j == p.minIndex) {
                    dp[i][j] += dp[i-1][p.secondMinIndex];
                } else {
                    dp[i][j] += dp[i-1][p.minIndex];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp[0].length; i++) {
            min = Math.min(min, dp[dp.length - 1][i]);
        }
        return min;
    }

    class Pair {
        int minIndex;
        int secondMinIndex;
    }

    private Pair findMinSecondMin(int[] input) {
        int minIndex;
        int secondMinIndex;
        if (input[0] < input[1]) {
            minIndex = 0;
            secondMinIndex = 1;
        } else {
            minIndex = 1;
            secondMinIndex = 0;
        }
        for (int i = 2; i < input.length; i++) {
            if (input[i] < input[minIndex]) {
                secondMinIndex = minIndex;
                minIndex = i;
            } else if (input[i] < input[secondMinIndex]) {
                secondMinIndex = i;
            }
        }
        Pair p = new Pair();
        p.minIndex = minIndex;
        p.secondMinIndex = secondMinIndex;
        return p;
    }

    public static void main(String args[]) {
        PaintHouse ph = new PaintHouse();
        int[][] input = {{1, 2, 1}, {1, 4, 5}, {2, 6, 1}, {3, 3, 2}};
        System.out.println(ph.minCostBottomUpPaintHouse2(input));
    }
}
