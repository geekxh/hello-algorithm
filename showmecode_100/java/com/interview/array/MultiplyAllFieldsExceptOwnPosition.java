package com.interview.array;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class MultiplyAllFieldsExceptOwnPosition {

    public int[] multiply(int nums[]) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] output = new int[nums.length];
        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        int mult = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= mult;
            mult *= nums[i];
        }
        return output;
    }
}
