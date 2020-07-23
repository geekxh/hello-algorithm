package com.interview.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return Collections.singletonList(makeRange(lower, upper));
        }
        List<String> result = new ArrayList<>();
        if (lower < nums[0]) {
            result.add(makeRange(lower, nums[0] - 1));
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if ((nums[i] + 1) != nums[i + 1]) {
                result.add(makeRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }
        if (nums[nums.length - 1] < upper) {
            result.add(makeRange(nums[nums.length - 1] + 1, upper));
        }
        return result;
    }

    private String makeRange(int a, int b) {
        if (a == b) {
            return String.valueOf(a);
        } else {
            return a + "->" + b;
        }
    }
}
