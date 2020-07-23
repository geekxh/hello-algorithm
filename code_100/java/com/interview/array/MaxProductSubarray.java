package com.interview.array;

/**
 * Date 04/`7/2016
 * @author Tushar Roy
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * http://www.geeksforgeeks.org/maximum-product-subarray/
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaxProductSubarray {

    public int maxProduct(int[] nums) {
        //neg maintains the multiplication which is negative since last 0
        //pos maintains the multiplication which is positive since last 0
        int neg = 1;
        int pos = 1;
        int maxProduct = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                neg = 1;
                pos = 1;
                maxProduct = Math.max(maxProduct, 0);
            } else if (nums[i] < 0) {
                int temp = pos;
                if (neg < 0) {
                    pos = neg * nums[i];
                    maxProduct = Math.max(pos, maxProduct);
                } else {
                    pos = 1;
                }
                neg = temp * nums[i];
            } else {
                if (neg < 0) {
                    neg *= nums[i];
                }
                pos *= nums[i];
                maxProduct = Math.max(pos, maxProduct);
            }
        }
        return maxProduct;
    }
    
    public static void main(String args[]){
        MaxProductSubarray mps = new MaxProductSubarray();
        int input[] = {-6, -3, 8, -9, -1, -1, 3, 6, 9, 0, 3, -1};
        System.out.println(mps.maxProduct(input));
    }
}
