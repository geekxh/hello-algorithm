package com.interview.recursion;

/**
 * Date 05/09/2017
 * @author Tushar Roy
 *
 * https://leetcode.com/problems/optimal-division/#/description
 */
public class OptimalDivision {
    public String optimalDivision(int[] nums) {
        Result r = optimalDivison(nums, 0, nums.length - 1, true);
        System.out.println(r.val);
        return r.str;
    }

    private Result optimalDivison(int[] nums, int start, int end, boolean maximize) {
        if (start == end) {
            return new Result(nums[start], String.valueOf(nums[start]));
        }

        double maxResult = 0;
        double minResult = Double.MAX_VALUE;
        String result = "";
        int cutI = start;
        String part1 = "";
        String part2 = "";
        for (int i = start; i < end; i++) {
            Result d1 = optimalDivison(nums, start, i, maximize);
            Result d2 = optimalDivison(nums, i + 1, end, !maximize);
            double val = d1.val / d2.val;
            if (maximize) {
                if (maxResult < val) {
                    maxResult = val;
                    part1 = d1.str;
                    part2 = d2.str;
                    cutI = i;
                }
            } else {
                if (minResult > val) {
                    minResult = val;
                    part1 = d1.str;
                    part2 = d2.str;
                    cutI = i;
                }
            }
        }
        if (cutI < end - 1) {
            result = part1 + "/(" + part2 + ")";
        } else {
            result = part1 + "/" + part2;
        }
        return maximize ? new Result(maxResult, result) : new Result(minResult, result);
    }

    class Result {
        double val;
        String str;
        Result(double val, String str) {
            this.val = val;
            this.str = str;
        }
    }

    public static void main(String args[]) {
       // int[] nums = {1000, 100, 10, 2};
        int[] nums = {6,2,3,4,5};
        OptimalDivision od = new OptimalDivision();
        System.out.println(od.optimalDivision(nums));
    }
}
