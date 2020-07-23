package com.interview.string;

import com.interview.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PalindromePairTest {

    @Test
    public void testDifferentCases() {
        PalindromePair palindromePair = new PalindromePair();
        TestUtil testUtil = new TestUtil();
        String[] words = {"bat", "tab"};
        List<List<Integer>> result = palindromePair.palindromePairs(words);
        testUtil.compareListOfList(createList(0, 1, 1, 0), result);
        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
        result = palindromePair.palindromePairs(words1);
        testUtil.compareListOfList(createList(0, 1, 1, 0, 3, 2, 2, 4), result);
        String[] words2 = {"", "abcd", "abba"};
        result = palindromePair.palindromePairs(words2);
        testUtil.compareListOfList(createList(0, 2, 2, 0), result);
        String[] words3 = {"a","abc","aba",""};
        result = palindromePair.palindromePairs(words3);
        testUtil.compareListOfList(createList(3, 0, 0, 3, 3, 2, 2, 3), result);
        String[] words4 = {"abcd","dcba","lls","s","sssll"};
        result = palindromePair.palindromePairs(words4);
        testUtil.compareListOfList(createList(0, 1, 1, 0, 3, 2, 2, 4), result);
    }

    private List<List<Integer>> createList(int... index) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < index.length; i += 2) {
            List<Integer> r = new ArrayList<>();
            r.add(index[i]);
            r.add((index[i + 1]));
            result.add(r);
        }
        return result;
    }
}
