package com.interview.bits;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tushar_v_roy on 4/3/16.
 */
public class CountingBitsTillNumTest {

    @Test
    public void testDifferentCases() {
        CountingBitsTillNum countingBitsTillNum = new CountingBitsTillNum();
        int[] expected1 = {0, 1, 1};
        int[] expected2 = {0, 1, 1, 2, 1, 2};
        int[] expected3 = {0, 1, 1, 2, 1, 2, 2, 3, 1};
        int[] expected4 = {0, 1, 1, 2, 1, 2, 2, 3 ,1, 2, 2, 3};

        Assert.assertArrayEquals(expected1, countingBitsTillNum.countBits(2));
        Assert.assertArrayEquals(expected2, countingBitsTillNum.countBits(5));
        Assert.assertArrayEquals(expected3, countingBitsTillNum.countBits(8));
        Assert.assertArrayEquals(expected4, countingBitsTillNum.countBits(11));
    }
}
