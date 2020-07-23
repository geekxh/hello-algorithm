package com.interview.bits;

import java.util.Arrays;

/**
 * Date 04/21/2016
 * @author Tushar Roy
 *
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words
 * do not share common letters. You may assume that each word will contain only lower case letters.
 * If no such two words exist, return 0.
 *
 * Space complexity O(n)
 * Time complexity O(n^2)
 *
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 */
public class MaxProductWordLength {
    public int maxProduct(String[] words) {
        int max = 0;

        Arrays.sort(words, (a, b) -> b.length() - a.length());

        int[] masks = new int[words.length]; // alphabet masks

        for(int i = 0; i < masks.length; i++){
            for(char c: words[i].toCharArray()){
                masks[i] |= 1 << (c - 'a');
            }
        }

        for(int i = 0; i < masks.length; i++){
            if(words[i].length() * words[i].length() <= max) {
                break; //prunning
            }
            for(int j = i + 1; j < masks.length; j++){
                if((masks[i] & masks[j]) == 0){
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }

        return max;
    }
}
