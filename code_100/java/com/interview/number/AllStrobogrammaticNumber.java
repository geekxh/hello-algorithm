package com.interview.number;

/**
 * Date 04/17/2016
 * @author Tushar Roy
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * https://leetcode.com/problems/strobogrammatic-number-iii/
 */
public class AllStrobogrammaticNumber {
    private static char[][] pairs = {{'6', '9'}, {'9', '6'}, {'0', '0'}, {'1', '1'}, {'8', '8'}};

    public int strobogrammaticInRange(String low, String high) {
        int count = 0;
        for (int i = low.length(); i <= high.length(); i++) {
            char[] result = new char[i];
            count += strobogrammaticInRangeUtil(low, high, 0, result.length - 1, result);
        }
        return count;
    }

    private int strobogrammaticInRangeUtil(String low, String high, int left, int right, char[] result) {
        if (left > right) {
            String r = new String(result);
            if ((r.length() == low.length() && low.compareTo(r) > 0) || (high.length() == result.length && high.compareTo(r) < 0)) {
                return 0;
            }
            return 1;
        }

        int count = 0;

        for (char[] pair : pairs) {
            result[left] = pair[0];
            result[right] = pair[1];
            //number should not start with 0 if its length is greater than 0
            if (result.length != 1 && result[0] == '0') {
                continue;
            }
            //if left == right then we got to make sure we dont pick pair 6, 9 or 9, 6
            if ((left < right) || (left == right && pair[0] == pair[1])) {
                count += strobogrammaticInRangeUtil(low, high, left + 1, right - 1, result);
            }
        }
        return count;
    }
}