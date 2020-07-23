package com.interview.array;

/**
 * https://leetcode.com/problems/longest-mountain-in-array/description/
 */
public class LargestMountain {

    public int longestMountain(int[] nums) {
        int start = 0;
        int max = 0;
        State state = State.STARTED;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                start = i;
                state = State.STARTED;
            }
            else if (nums[i] > nums[i - 1]) {
                if (state == State.DECREASING || state == State.STARTED) {
                    start = i - 1;
                    state = State.INCREASING;
                }
            } else {
                if (state == State.INCREASING || state == State.DECREASING) {
                    state = State.DECREASING;
                    max = Math.max(max, i - start + 1);
                } else {
                    start = i;
                }
            }
        }
        return max;
    }

    enum State {
        STARTED,
        INCREASING,
        DECREASING;
    }

    public static void main(String[] args) {
        LargestMountain lm = new LargestMountain();
        int[] nums = {2, 1, 4, 7, 3, 2, 5};
        System.out.println(lm.longestMountain(nums));
    }
}
