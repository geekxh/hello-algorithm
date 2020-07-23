package com.interview.string;

import java.util.HashSet;
import java.util.Set;

/**
 * References
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepetingCharacter {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> uniqueSet = new HashSet<>();
        int maxSize = 0;
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            if(!uniqueSet.contains(s.charAt(i))) {
                uniqueSet.add(s.charAt(i));
                if(uniqueSet.size() > maxSize) {
                    maxSize = uniqueSet.size();
                }
            } else {
                while (s.charAt(start) != s.charAt(i)) {
                    uniqueSet.remove(s.charAt(start));
                    start++;
                }
                start++;
            }
        }
        return maxSize;
    }
    
    public static void main(String args[]){
        LongestSubstringWithoutRepetingCharacter lsw = new LongestSubstringWithoutRepetingCharacter();
        System.out.println(lsw.lengthOfLongestSubstring("ABCDECAMNCZB"));
    }
}
