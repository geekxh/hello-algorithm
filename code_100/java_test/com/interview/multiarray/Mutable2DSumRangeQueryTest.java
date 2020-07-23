package com.interview.multiarray;

import org.junit.Assert;
import org.junit.Test;

public class Mutable2DSumRangeQueryTest {

    @Test
    public void testDifferentCases() {
        int[][] input = {{2, 3, 6}, {-1, 2, 4}, {-3, 2, 5}};
        Mutable2DSumRangeQuery mutable2DSumRangeQuery = new Mutable2DSumRangeQuery(input);
        int total = mutable2DSumRangeQuery.sumRegion(1, 1, 2, 2);
        Assert.assertEquals(13, total);

        total = mutable2DSumRangeQuery.sumRegion(0, 1, 2, 1);
        Assert.assertEquals(7, total);

        mutable2DSumRangeQuery.update(1, 1, 4);
        total = mutable2DSumRangeQuery.sumRegion(1, 1, 2, 2);
        Assert.assertEquals(15, total);

        total = mutable2DSumRangeQuery.sumRegion(0, 1, 2, 1);
        Assert.assertEquals(9, total);

    }
}
