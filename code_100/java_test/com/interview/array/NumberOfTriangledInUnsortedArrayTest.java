package com.interview.array;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfTriangledInUnsortedArrayTest {

    @Test
    public void testSimpleCase() {
        NumberOfTrianglesInUnsortedArray numberOfTrianglesInUnsortedArray = new NumberOfTrianglesInUnsortedArray();
        int[] input = {3, 4, 5, 6, 8, 9, 15};
        int result = numberOfTrianglesInUnsortedArray.numberOfTriangles(input);
        Assert.assertEquals(15, result);
    }
}
