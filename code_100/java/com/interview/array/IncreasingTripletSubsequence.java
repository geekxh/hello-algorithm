package com.interview.array;

/**
 * Date 03/06/2016
 * @author Tushar Roy
 *
 * Find if there exists an increasing triplet subsequence.
 * Similar method to longest increasing subsequence in nlogn time.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int T[] = new int[3];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean found = false;
            for (int j = 0; j < len; j++) {
                if (T[j] >= nums[i]) {
                    T[j] = nums[i];
                    found = true;
                    break;
                }
            }
            if (!found) {
                T[len++] = nums[i];
            }
            if (len == 3) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        int input[] = {9, 10, -2, 12, 6, 7, -1};
        System.out.print(tripletSubsequence.increasingTriplet(input));
    }
}
