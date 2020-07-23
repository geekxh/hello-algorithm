package com.interview.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 03/01/2016
 * @author Tushar Roy
 *
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two
 *
 * e.g
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 * 
 * https://leetcode.com/problems/create-maximum-number/
 */
public class MaxNumberFromTwoArray {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        for (int i = 0; i <= k; i++) {
            if (nums1.length < i || nums2.length < k - i) {
                continue;
            }
            int[] a = merge(findLargest1(nums1, i), findLargest1(nums2, k - i));
            if (isGreater(a, max, 0, 0)) {
                max = a;
            }
        }
        return max;
    }

    private int[] merge(int[] a1, int[] a2) {
        int[] result = new int[a1.length + a2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a1.length || j < a2.length) {
            if (i == a1.length) {
                result[k++] = a2[j++];
            } else if (j == a2.length) {
                result[k++] = a1[i++];
            } else if (a1[i] > a2[j]) {
                result[k++] = a1[i++];
            } else if (a1[i] < a2[j]) {
                result[k++] = a2[j++];
            } else {
                if (isGreater(a1, a2, i, j)) {
                    result[k++] = a1[i++];
                } else {
                    result[k++] = a2[j++];
                }
            }
        }
        return result;
    }

    private boolean isGreater(int[] a, int[] b, int i, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                return true;
            } else if (a[i] < b[j]) {
                return false;
            }
            i++;
            j++;
        }
        return j == b.length;
    }

    private int[] findLargest1(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] result = new int[k];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (index > 0 && index + (nums.length - i - 1) >= k && result[index - 1] < nums[i]) {
                index--;
            }
            if (index < k) {
                result[index++] = nums[i];
            }
        }
        return result;
    }

    public static void main(String args[]) {
        MaxNumberFromTwoArray max = new MaxNumberFromTwoArray();
        int[] input1 = {9,1,2,5,8,3};
        int[] input2 = {3,4,6,5};

        int[] input3 = {2,5,6,4,4,0};
        int[] input4 = {7,3,8,0,6,5,7,6,2};
        int[] result = max.maxNumber(input3, input4, 15);
        System.out.print(Arrays.toString(result));
    }
}