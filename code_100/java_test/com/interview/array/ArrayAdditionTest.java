package com.interview.array;

import org.junit.Assert;
import org.junit.Test;

public class ArrayAdditionTest {

    @Test
    public void testAddSimple() {
        ArrayAddition arrayAddition = new ArrayAddition();
        int arr1[] = {9,9,9,9,9,9,9};
        int arr2[] = {1,6,8,2,6,7};
        int[] result = arrayAddition.add(arr1, arr2);
        int[] expected = {1, 0, 1, 6, 8, 2, 6, 6};
        Assert.assertArrayEquals(expected, result);
    }
}
