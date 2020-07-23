package com.interview.array;

import junit.framework.Assert;
import org.junit.Test;

public class ThreeSumSmallerThanTargetTest {

    @Test
    public void testDifferentCases() {
        ThreeSumSmallerThanTarget threeSumSmallerThanTarget = new ThreeSumSmallerThanTarget();
        int[] input = {-2, 0, 1, 3};
        Assert.assertEquals(2, threeSumSmallerThanTarget.threeSumSmaller(input, 2));
    }
}
