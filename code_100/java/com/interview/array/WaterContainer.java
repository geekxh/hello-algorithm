package com.interview.array;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 * which together with x-axis forms a container, such that the container contains the most water.
 *
 * https://leetcode.com/problems/container-with-most-water/
 */
public class WaterContainer {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                maxArea = Math.max(maxArea, (height[i]) * (j - i));
                i++;
            } else {
                maxArea = Math.max(maxArea, height[j] * (j - i));
                j--;
            }
        }
        return maxArea;
    }
}
