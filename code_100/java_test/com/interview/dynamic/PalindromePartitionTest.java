package com.interview.dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitionTest {

    @Test
    public void testAllPartitions() {
        PalindromePartition palindromePartition = new PalindromePartition();
        List<List<String>> result = palindromePartition.partition("aab");

        List<List<String>> expected = new ArrayList<>();
        List<String> r1 = new ArrayList<>();
        r1.add("a");
        r1.add("a");
        r1.add("b");

        expected.add(r1);
        r1 = new ArrayList<>();
        r1.add("aa");
        r1.add("b");
        expected.add(r1);
        int index = 0;
        for (List<String> r : result) {
            Assert.assertEquals(expected.get(index++), r);
        }
    }

    @Test
    public void palindromePartitionMinCuts() {
        PalindromePartition palindromePartition = new PalindromePartition();
        Assert.assertEquals(3, palindromePartition.minCut("ABCCDCCLMLCCD"));
        Assert.assertEquals(0, palindromePartition.minCut("bb"));
        Assert.assertEquals(0, palindromePartition.minCut("b"));

    }
}
