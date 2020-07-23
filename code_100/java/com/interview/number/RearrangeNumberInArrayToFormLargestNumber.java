package com.interview.number;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * https://leetcode.com/problems/largest-number/
 */
public class RearrangeNumberInArrayToFormLargestNumber {

    public String largestNumber(int[] nums) {
        Integer[] nums1 = new Integer[nums.length];
        int i = 0;
        for (int n : nums) {
            nums1[i++] = n;
        }

        IntegerComparator comparator = new IntegerComparator();
        Arrays.sort(nums1, comparator);
        StringBuffer buff = new StringBuffer();

        //handle 0s in the front by removing them.
        for (i = 0; i < nums1.length; i++) {
            if (nums1[i] != 0) {
                break;
            }
        }
        //if no element is left means return one 0
        if (i == nums1.length) {
            return "0";
        }
        for (;i < nums1.length; i++) {
            buff.append(nums1[i]);
        }
        return buff.toString();
    }
    
    public static void main(String args[]){
        RearrangeNumberInArrayToFormLargestNumber rni = new RearrangeNumberInArrayToFormLargestNumber();
        int[] input = {999999998, 999999997, 999999999};
        int[] input1 = {0, 9, 8};
        int[] input2 = {0, 0};

        String result = rni.largestNumber(input2);
        System.out.print(result);
    }

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int pow1 = 10;
            while(pow1 <= o1){
                pow1 *= 10;
            }
            int pow2 = 10;
            while(pow2 <= o2){
                pow2 *= 10;
            }
            int r1 = o1*pow2 + o2;
            int r2 = o2*pow1 + o1;
            return r2 - r1;
        }
    }
}
