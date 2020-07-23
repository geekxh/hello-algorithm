package com.interview.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 04/03/2016
 * @author Tushar Roy
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s.length() == 0) {
            return 0;
        }
        int[] ascii = new int[256];
        int count = 0;
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (count < k) {
                if (ascii[ch] == 0) {
                    count++;
                }
            } else if (ascii[ch] == 0){
                while (start < i) {
                    char ch1 = s.charAt(start++);
                    ascii[ch1]--;
                    if (ascii[ch1] == 0) {
                        break;
                    }
                }
            }
            ascii[ch]++;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstringKDistinctUsingMap(String s, int k) {
        if (k == 0 || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!countMap.containsKey(ch) && countMap.size() >= k) {
                while (start < i) {
                    countMap.compute(s.charAt(start), (key, val) -> {
                        if (val == 1) {
                            return null;
                        } else {
                            return val - 1;
                        }
                    });
                    start++;
                    if (countMap.size() < k) {
                        break;
                    }
                }
            }
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}
