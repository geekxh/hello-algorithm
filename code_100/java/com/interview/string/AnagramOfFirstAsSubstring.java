package com.interview.string;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.careercup.com/question?id=5389078581215232
 * Given two strings tells if anagram of first is substring of another
 * Keep map of characters in array1 and keep checking if array2 has these characters.
 * main string : a b a c a b b and looking for a a b b c when 3rd a is encountered
 * we move index to second a and start from there.
 * 
 * Another idea is to keep a sorted linklist of string in comparison. Whenever a new character
 * is to be added remove last character from linklist and add this new one.
 */
public class AnagramOfFirstAsSubstring {

    public boolean isSubString(char str1[], char str2[]) {
        int index = 0;
        int curLen = 0;
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        for (int i = 0; i < str1.length; i++) {
            incrementCount(str1[i], count);
        }
        Map<Character, Integer> currentCount = new HashMap<Character, Integer>();
        Map<Character, Integer> pos = new HashMap<Character, Integer>();
        while (index < str2.length) {
            if (containsAndUpdate(currentCount, count, str2[index], pos, index)) {
                index++;
                curLen++;
            } else {
                Integer p = pos.get(str2[index]);
                if (p != null) {
                    curLen = index - p;
                    index = p;
                } else {
                    index++;
                }
                currentCount.clear();
                pos.clear();
            }
            if (curLen == str1.length) {
                return true;
            }
        }
        return false;
    }

    private boolean containsAndUpdate(Map<Character, Integer> currentCount,
            Map<Character, Integer> count, Character ch,
            Map<Character, Integer> pos, int index) {
        if (count.containsKey(ch)) {
            if(currentCount.containsKey(ch)) {
                if (currentCount.get(ch) < count.get(ch)) {
                    if (currentCount.get(ch) == 1) {
                        pos.put(ch, index);
                    }
                    currentCount.put(ch, currentCount.get(ch) + 1);
                    return true;
                }
            }else{
                currentCount.put(ch, 1);
                pos.put(ch,index);
                return true;
            }
        }
        return false;
    }

    private void incrementCount(Character ch, Map<Character, Integer> count) {
        if (count.containsKey(ch)) {
            int c = count.get(ch);
            count.put(ch, c + 1);
        } else {
            count.put(ch, 1);
        }
    }
    
    public static void main(String args[]){
        char str1[] = "aaabccde".toCharArray();
        char str2[] = "tbcdaacaaecbd".toCharArray();
        AnagramOfFirstAsSubstring ana = new AnagramOfFirstAsSubstring();
        System.out.println(ana.isSubString(str1, str2));
    }
}
