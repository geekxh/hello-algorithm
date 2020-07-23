package com.interview.array;

/**
 * Date 12/31/2015
 * @author Tushar Roy
 *
 * Given two sorted arrays such the arrays may have some common elements. Find the sum of the maximum sum
 * path to reach from beginning of any array to end of any of the two arrays. We can switch from one array
 * to another array only at common elements.
 *
 * Time complexity is O(n + m)
 * Space complexity is O(1)
 *
 * http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
 */
public class MaximumSumPathTwoArrays {

    public int maxSum(int input1[], int input2[]) {
        int maxSum = 0;
        int i = 0, j = 0;
        int sum1 = 0;
        int sum2 = 0;
        while (i < input1.length && j < input2.length) {
            if (input1[i] == input2[j]) {
                if (sum1 > sum2) {
                    maxSum += sum1 + input1[i];
                } else {
                    maxSum += sum2 + input2[j];
                }
                i++;
                j++;
                sum1 = 0;
                sum2 = 0;
            } else if (input1[i] < input2[j]) {
                sum1 += input1[i++];
            } else {
                sum2 += input2[j++];
            }
        }
        while(i < input1.length) {
            sum1 += input1[i++];
        }
        while(j < input2.length) {
            sum2 += input2[j++];
        }

        if (sum1 > sum2) {
            maxSum += sum1;
        } else {
            maxSum += sum2;
        }
        return maxSum;
    }

    public static void main(String args[]) {
        int input1[] = {2, 3, 7, 10, 12, 15, 30, 34};
        int input2[] = {1, 5, 7, 8, 10, 15, 16, 19};

        MaximumSumPathTwoArrays msp = new MaximumSumPathTwoArrays();
        System.out.println(msp.maxSum(input1, input2));
    }
}
