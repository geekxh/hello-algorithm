package com.interview.string;

import org.junit.Assert;
import org.junit.Test;

public class ValidPalindromeTest {

    @Test
    public void testDifferentCases() {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        Assert.assertTrue(validPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(validPalindrome.isPalindrome("race a car"));
    }
}
