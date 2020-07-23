package com.interview.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Date 04/15/2016
 * @author Tushar Roy
 *
 * Given a dictionary of words and a word tell if there is unique abbrreviation of this word in the dictionary.
 */
public class ValidWordAbbreviation {
    private final Map<String, Map<Integer, Integer>> map = new HashMap<>();
    public ValidWordAbbreviation(String[] dictionary) {
        for (String str : dictionary) {
            String key = "";
            int len = 0;
            if (str.length() > 0) {
                key = str.charAt(0) + "" + str.charAt(str.length() - 1);
                len = str.length() - 2;
            }
            Map<Integer, Integer> innerMap = map.get(key);
            if (innerMap == null) {
                innerMap = new HashMap<>();
                map.put(key, innerMap);
            }
            Integer count = innerMap.get(len);
            if (count == null) {
                count = 0;
            }
            innerMap.put(len, count + 1);
        }
    }

    public boolean isUnique(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        String key = "";
        int len = 0;
        if (word.length() > 0) {
            key = word.charAt(0) + "" + word.charAt(word.length() - 1);
            len = word.length() - 2;
        }
        Map<Integer, Integer> set = map.get(key);
        if (set == null) {
            return true;
        }
        Integer count = set.get(len);
        return count == null || count == 1;
    }
}
