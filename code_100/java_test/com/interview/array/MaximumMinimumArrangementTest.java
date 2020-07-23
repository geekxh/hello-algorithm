package com.interview.array;

import org.junit.Assert;
import org.junit.Test;

public class MaximumMinimumArrangementTest {
    @Test
    public void differentCases() {
        MaximumMinimumArrangement maximumMinimumArrangement = new MaximumMinimumArrangement();
        int[] input1 = {1, 2, 3, 4, 5, 6, 7};
        maximumMinimumArrangement.rearrange(input1);
        int[] expected1 = {7, 1, 6, 2, 5, 3, 4};
        Assert.assertArrayEquals(expected1, input1);

        int[] input2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        maximumMinimumArrangement.rearrange(input2);
        int[] expected2 = {10, 1, 9, 2, 8, 3, 7, 4, 6, 5};
        Assert.assertArrayEquals(expected2, input2);
    }
}
