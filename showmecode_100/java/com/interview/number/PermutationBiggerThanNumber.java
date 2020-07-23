package com.interview.number;

import com.interview.sort.QuickSort;

/**
 * Date 02/17/2016
 * @author Tushar Roy
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * 
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/next-permutation/
 * e.g 1 7 9 8 4 will transform to 1 8 4 7 9
 * or  4 2 6 4 3 will transform to 4 3 2 4 6
 */
public class PermutationBiggerThanNumber {

    public void nextPermutation(int[] nums) {
        int i;
        for (i = nums.length - 2; i >= 0 ; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

        if (i != -1) {
            int pos = ceiling(nums[i], i + 1, nums.length - 1, nums);
            int t = nums[pos];
            nums[pos] = nums[i];
            nums[i] = t;
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int nums[], int start, int end) {
        while (start <= end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            start++;
            end--;
        }
    }

    private int ceiling(int val, int start, int end, int[] nums) {
        while (start <= end) {
            int middle = (start + end)/2;
            if (nums[middle] > val && (middle + 1 == nums.length || nums[middle+1] <= val)) {
                return middle;
            } else if (nums[middle] > val) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String args[]){
        PermutationBiggerThanNumber pb = new PermutationBiggerThanNumber();
        int arr[] = {1,7,9,8,4};
        pb.nextPermutation(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
