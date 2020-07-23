package com.interview.dynamic;

/**
 * Date 11/03/2016
 * @author Tushar Roy
 *
 * Find maximum sum for non adjacent elements.
 * Variation is finding maximum sum non adjacent elements assuming its a circular array.
 * So first element cannot be with last element.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/house-robber/
 * https://leetcode.com/problems/house-robber-ii/
 */
public class MaxSumForNonAdjacentElements {

    /**
     * Fast DP solution.
     */
    public int maxSum(int arr[]) {
        int excl = 0;
        int incl = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp = incl;
            incl = Math.max(excl + arr[i], incl);
            excl = temp;
        }
        return incl;
    }

    /**
     * Recursive slow solution.
     */
    public int maxSum(int arr[], int index) {
        if (index == 0) {
            return arr[0];
        } else if (index == 1) {
            return Math.max(arr[0], arr[1]);
        }
        return Math.max(this.maxSum(arr, index - 2) + arr[index], this.maxSum(arr, index - 1));
    }

    /**
     * Find maximum sum from left to right ignoring first element.
     * Find maximum sum from right to left ignoring last element.
     * Maximum of two will be the answer. It gurantees that both first and last element
     * will be not selected together.
     */
    public int maxSumCircularArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int with = nums[1];
        int without = 0;
        for (int i = 2; i < nums.length; i++) {
            int newWith = without + nums[i];
            without = with;
            with = Math.max(with, newWith);
        }

        int with1 = nums[nums.length - 2];
        int without1 = 0;
        for (int i = nums.length - 3; i >= 0; i--) {
            int newWith1 = without1 + nums[i];
            without1 = with1;
            with1 = Math.max(with1, newWith1);
        }
        return Math.max(with, with1);
    }

    public static void main(String args[]) {
        MaxSumForNonAdjacentElements msn = new MaxSumForNonAdjacentElements();
        int arr[] = { 2, 10, 13, 4, 2, 15, 10 };
        System.out.println(msn.maxSum(arr));
        System.out.println(msn.maxSum(arr, arr.length-1));

    }
}
