package com.interview.bits;

import org.junit.Assert;
import org.junit.Test;

public class MaxProductWordLengthTest {

    @Test
    public void testDifferentCases() {
        MaxProductWordLength maxProductWordLength = new MaxProductWordLength();
        String[] words1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        Assert.assertEquals(16, maxProductWordLength.maxProduct(words1));

        String[] words2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        Assert.assertEquals(4, maxProductWordLength.maxProduct(words2));
    }
}
