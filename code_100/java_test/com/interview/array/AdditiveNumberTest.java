package com.interview.array;

import org.junit.Assert;

public class AdditiveNumberTest {
    public static void main(String args[]) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
        Assert.assertTrue(additiveNumber.isAdditiveNumber("12351174"));
        Assert.assertFalse(additiveNumber.isAdditiveNumber("1023"));
        Assert.assertTrue(additiveNumber.isAdditiveNumber("198019823962"));
    }
}
