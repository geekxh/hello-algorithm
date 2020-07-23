package com.interview.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 12/03/2016
 * @author Tushar Roy
 *
 * Find longest consecutive subsequence in unsorted array.
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * Reference
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSubsequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 1;
        for (int i : nums) {
            if (map.containsKey(i)) {
                continue;
            }
            int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
            int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;

            int sum = left + right + 1;
            map.put(i, sum);
            result = Math.max(sum, result);
            map.put(i - left, sum);
            map.put(i + right, sum);
        }
        return result;
    }

    public static void main(String args[]) {
        LongestConsecutiveSubsequence lcs = new LongestConsecutiveSubsequence();
        int[] input = {100, 4, 200, 1, 3, 2};
        System.out.println(lcs.longestConsecutive(input));
    }
}