package com.interview.graph;

import org.junit.Assert;
import org.junit.Test;

public class TravelingSalesmanHeldKarpTest {

    @Test
    public void testDifferentCases() {
        TravelingSalesmanHeldKarp tsp = new TravelingSalesmanHeldKarp();
        int[][] distance = {{0, 12, 3, 9, 6, 1, 2},
                {12, 0, 6, 1, 8, 2, 10},
                {3, 6, 0, 6, 7, 11, 7},
                {9, 1, 6, 0, 9, 10, 3},
                {6, 8, 7, 9, 0, 1, 11},
                {1, 2, 11, 10, 1, 0, 12},
                {2, 10, 7, 3, 11, 12, 0}};

        int cost = tsp.minCost(distance);
        Assert.assertEquals(19, cost);

        int[][] distance1 = {{0, 1, 15, 6},
                {2, 0, 7, 3},
                {9, 6, 0, 12},
                {10, 4, 8, 0},
        };

        cost = tsp.minCost(distance1);
        Assert.assertEquals(21, cost);
    }
}
