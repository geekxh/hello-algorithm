package com.interview.binarysearch;

/**
 * https://leetcode.com/problems/search-insert-position/
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < target && (middle == nums.length - 1 ||  nums[middle + 1] > target)) {
                return middle + 1;
            }
            if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return 0;
    }
}
