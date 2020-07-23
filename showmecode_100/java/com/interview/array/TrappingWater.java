package com.interview.array;
/**
 * References
 * https://oj.leetcode.com/problems/trapping-rain-water/
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingWater {

    public int trap(int[] height) {
       if(height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int left[] = new int[len];
        int right[] = new int[len];
        left[0] = height[0];
        right[len-1] = height[len -1];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(height[i], left[i-1]);
            right[len - i - 1] = Math.max(height[len- i - 1], right[len-i]);
        }

        int maxWaterTrapped = 0;
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(left[i], right[i]);
            if (height[i] < min) {
                maxWaterTrapped += min - height[i];
            }
        }
        return maxWaterTrapped;
    }

    public static void main(String args[]){
        int input[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingWater tw = new TrappingWater();
        System.out.println(tw.trap(input));
    }
}
