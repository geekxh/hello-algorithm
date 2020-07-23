package com.interview.array;

/**
 * Date 12/31/2015
 * @author Tushar Roy
 *
 * Given array in non decreasing order find smallest integer which cannot be represented by
 * subset sum of these integers.
 *
 * Time complexity is O(n)
 *
 * http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
 */
public class SmallestIntegerNotRepresentedBySubsetSum {

    public int findSmallestInteger(int input[]) {
        int result = 1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] <= result) {
                result += input[i];
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Leetcode variation https://leetcode.com/problems/patching-array/
     */
    public int minPatches(int[] nums, int n) {
        int patch = 0;
        long t = 1;
        int i = 0;
        while(t <= n) {
            if (i == nums.length || t < nums[i]) {
                patch++;
                t += t;
            } else {
                t = nums[i] + t;
                i++;
            }
        }
        return patch;
    }


    public static void main(String args[]) {
        int input[] = {1, 2, 3, 8};
        SmallestIntegerNotRepresentedBySubsetSum ss = new SmallestIntegerNotRepresentedBySubsetSum();
        System.out.println(ss.findSmallestInteger(input));

        int input1[] = {};
        System.out.println(ss.minPatches(input1, 7));
    }
}
