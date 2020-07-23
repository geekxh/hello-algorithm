package com.interview.binarysearch;

/**
 * @author Tushar Roy
 * Date 01/17/2107
 * A peak element is an element that is greater than its neighbors. Find index of peak element in the array.
 *
 * Space complexity is O(1)
 * Time complexity is O(n)
 *
 * https://leetcode.com/problems/find-peak-element/
 */
public class PeakElement {

    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int middle = 0;
        while (low <= high) {
            middle = (low + high)/2;
            int before = Integer.MIN_VALUE;
            if (middle > 0) {
                before = nums[middle - 1];
            }
            int after = Integer.MIN_VALUE;
            if (middle < nums.length - 1) {
                after = nums[middle + 1];
            }
            if (nums[middle] > before && nums[middle] > after) {
                return middle;
            } else if (before > after) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return middle;
    }

    public static void main(String args[]){
        int arr[] = {10,5,15,2,23,90,67};
        PeakElement pe = new PeakElement();
        System.out.println(pe.findPeakElement(arr));
        int arr1[] = {10,20,30,40,50};
        System.out.println(pe.findPeakElement(arr1));
        int arr2[] = {100,90,80,70,60};
        System.out.println(pe.findPeakElement(arr2));
                
    }
}
