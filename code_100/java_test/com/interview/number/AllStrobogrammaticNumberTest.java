package com.interview.number;

import org.junit.Assert;
import org.junit.Test;

public class AllStrobogrammaticNumberTest {

    @Test
    public void testDifferentCases() {
        AllStrobogrammaticNumber allStrobogrammaticNumber = new AllStrobogrammaticNumber();
        Assert.assertEquals(19, allStrobogrammaticNumber.strobogrammaticInRange("0", "1000"));
        Assert.assertEquals(34171, allStrobogrammaticNumber.strobogrammaticInRange("1011010", "2210101121121"));

    }
}
