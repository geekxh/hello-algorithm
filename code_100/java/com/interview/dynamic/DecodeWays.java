package com.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * 26-> Z
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        return numDecodingsUtil(s, 0, count);
    }

    public int numDecodingsUtil(String s, int start, Map<Integer, Integer> count) {
        if (s.length() == start) {
            return 1;
        }
        if (count.containsKey(start)) {
            return count.get(start);
        }
        String s1 = s.substring(start, start + 1);
        if (s1.equals("0")) {
            count.put(start, 0);
            return 0;
        }
        int c1 = numDecodingsUtil(s, start + 1, count);

        int c2 = 0;
        if (start < s.length() - 1) {
            s1 = s.substring(start, start + 2);
            if (Integer.valueOf(s1) <= 26) {
                c2 = numDecodingsUtil(s, start + 2, count);
            }
        }
        count.put(start, c1 + c2);
        return c1 + c2;
    }
}

