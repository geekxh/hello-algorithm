package com.interview.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
 */
public class TripletInArray {

    class Triplet {
        int a;
        int b;
        int c;

        public String toString() {
            return a + " " + b + " " + c;
        }
    }

    public Triplet findTriplet(int input[], int sum) {
        Arrays.sort(input);
        for (int i = 0; i < input.length - 2; i++) {

            int start = i + 1;
            int end = input.length - 1;
            int new_sum = sum - input[i];
            while (start < end) {
                if (new_sum == input[start] + input[end]) {
                    Triplet t = new Triplet();
                    t.a = input[i];
                    t.b = input[start];
                    t.c = input[end];
                    return t;
                }
                if (new_sum > input[start] + input[end]) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return null;
    }

    /**
     * https://leetcode.com/problems/3sum/
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[i] + nums[start] + nums[end] == 0) {
                    List<Integer> r = new ArrayList<>();
                    r.add(nums[i]);
                    r.add(nums[start]);
                    r.add(nums[end]);
                    result.add(r);
                    start++;
                    end--;
                    while(start < nums.length && nums[start] == nums[start - 1]) {
                        start++;
                    }
                    while(end >= 0 && nums[end] == nums[end+1]) {
                        end--;
                    }
                } else if (nums[i] + nums[start] + nums[end] < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

    public static void main(String args[]){
        TripletInArray tip = new TripletInArray();
        int input[] = {1,2,6,9,11,18,26,28};
        int sum = 22;
        System.out.println(tip.findTriplet(input, sum));
    }
}
