package com.interview.string;

import java.util.*;

/**
 * Date 04/17/2106
 * @author Tushar Roy
 *
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * https://leetcode.com/problems/generalized-abbreviation/
 */
public class WordAbbreviationCombination {

    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        generateAbbreviationsUtil(word, result, "", 0, 0);
        return result;
    }

    public void generateAbbreviationsUtil(String input, List<String> result, String current, int pos, int count) {
        if (input.length() == pos) {
            if (count > 0) {
                result.add(current + count);
            } else {
                result.add(current);
            }
            return;
        }

        generateAbbreviationsUtil(input, result, current, pos + 1, count + 1);
        generateAbbreviationsUtil(input, result, current + (count > 0 ? count : "") + input.charAt(pos), pos + 1, 0);
    }

    public static void main(String args[]) {
        WordAbbreviationCombination ssc = new WordAbbreviationCombination();
        List<String> result = ssc.generateAbbreviations("word");
        result.forEach(r -> System.out.println(r));
    }
}
