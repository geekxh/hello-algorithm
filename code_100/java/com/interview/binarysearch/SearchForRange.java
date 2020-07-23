package com.interview.binarysearch;

/**
 * Date 07/31/2016
 * @author Tushar Roy
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Time complexity O(logn)
 * Space complexity O(1)
 * 
 * https://leetcode.com/problems/search-for-a-range/
 */
public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int first = firstOccurence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = lastOccurence(nums, target);
        return new int[]{first, last};
    }

    private int firstOccurence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] < target)) {
                return mid;
            } else if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private int lastOccurence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] > target)) {
                return mid;
            } else if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        SearchForRange searchForRange = new SearchForRange();
        int[] nums = {0, 1, 1, 3, 6, 9, 11};
        int[] r = searchForRange.searchRange(nums, 11);
        System.out.println(r[0] + " " + r[1]);
        r = searchForRange.searchRange(nums, 0);
        System.out.println(r[0] + " " + r[1]);
    }
}
