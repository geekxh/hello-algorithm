package com.interview.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 10/19/2016
 * @author Tushar Roy
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 *
 * Solution -
 * Just check if num[i] + 1 != num[i + 1]. If its not equal means you need to add previous range to result
 * and start a new range.
 * 
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/summary-ranges/
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        if (nums.length == 1) {
            return Collections.singletonList(String.valueOf(nums[0]));
        }
        int start = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i] + 1) != nums[i + 1]) {
                result.add(makeRange(nums[start], nums[i]));
                start = i + 1;
            }
        }
        if ((nums[nums.length - 2] + 1) != nums[nums.length - 1]) {
            start = nums.length - 1;
        }
        result.add(makeRange(nums[start], nums[nums.length - 1]));
        return result;
    }

    private String makeRange(int a, int b) {
        if (a == b) {
            return String.valueOf(a);
        }
        return a + "->" + b;
    }
}
