package com.interview.dynamic;

/**
 * Date 03/02/2016
 * @author Tushar Roy
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented
 * by array nums. You are asked to burst all the balloons. If the you burst balloon i you will
 * get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst,
 * the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Time complexity O(n^3)
 * Space complexity O(n^2)
 *
 * Reference
 * https://leetcode.com/problems/burst-balloons/
 */
public class BurstBalloons {

    /**
     * Dynamic programming solution.
     */
    public int maxCoinsBottomUpDp(int[] nums) {

        int T[][] = new int[nums.length][nums.length];

        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    //leftValue/rightValue is initially 1. If there is element on
                    // left/right of k then left/right value will take that value.
                    int leftValue = 1;
                    int rightValue = 1;
                    if (i != 0) {
                        leftValue = nums[i-1];
                    }
                    if (j != nums.length -1) {
                        rightValue = nums[j+1];
                    }

                    //before is initially 0. If k is i then before will
                    //stay 0 otherwise it gets value T[i][k-1]
                    //after is similarly 0 initially. if k is j then after will
                    //stay 0 other will get value T[k+1][j]
                    int before = 0;
                    int after = 0;
                    if (i != k) {
                        before = T[i][k-1];
                    }
                    if (j != k) {
                        after = T[k+1][j];
                    }
                    T[i][j] = Math.max(leftValue * nums[k] * rightValue + before + after,
                            T[i][j]);
                }
            }
        }
        return T[0][nums.length - 1];
    }

    /**
     * Recursive solution.
     */
    public int maxCoinsRec(int nums[]) {
        int[] nums1 = new int[nums.length + 2];
        nums1[0] = 1;
        nums1[nums1.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            nums1[i+1] = nums[i];
        }
        return maxCoinsRecUtil(nums1);
    }

    private int maxCoinsRecUtil(int[] nums) {
        if (nums.length == 2) {
            return 0;
        }

        int max = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int val = nums[i - 1]*nums[i]*nums[i+1] + maxCoinsRecUtil(formNewArray(nums, i));
            if (val > max) {
                max = val;
            }
         }
        return max;

    }

    private int[] formNewArray(int[] input, int doNotIncludeIndex) {
        int[] newArray = new int[input.length - 1];
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (i == doNotIncludeIndex) {
                continue;
            }
            newArray[index++] = input[i];
        }
        return newArray;
    }


    public static void main(String args[]) {
        BurstBalloons bb = new BurstBalloons();
        int input[] = {2, 4, 3, 5};
        System.out.print(bb.maxCoinsBottomUpDp(input));
    }
}
