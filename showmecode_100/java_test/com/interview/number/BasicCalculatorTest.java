package com.interview.number;

import org.junit.Assert;
import org.junit.Test;

public class BasicCalculatorTest {

    @Test
    public void testDifferentCases() {
        BasicCalculator basicCalculator = new BasicCalculator();
        Assert.assertEquals(0, basicCalculator.calculate("0"));
        Assert.assertEquals(9, basicCalculator.calculate("0 +  9"));
        Assert.assertEquals(19, basicCalculator.calculate("1 + 9 * 2"));
        Assert.assertEquals(15, basicCalculator.calculate("3 + 9/2 * 3"));
        Assert.assertEquals(6, basicCalculator.calculate("8 -2 + 3/ 5  "));

    }
}
