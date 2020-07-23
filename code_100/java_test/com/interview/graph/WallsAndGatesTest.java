package com.interview.graph;

import org.junit.Assert;
import org.junit.Test;

public class WallsAndGatesTest {

    @Test
    public void testDifferentScenarios() {
        WallsAndGates wallsAndGates = new WallsAndGates();
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, -1, 0, INF},
                         {INF, INF, INF, -1},
                         {INF, -1, INF, -1},
                         {0, -1, INF, INF}};

        int[][] output = {{3, -1, 0, 1},
                          {2, 2, 1, -1},
                          {1, -1, 2, -1},
                          {0, -1, 3, 4}};

        wallsAndGates.wallsAndGates(rooms);
        int i = 0;
        for (int[] o : output) {
            Assert.assertArrayEquals(o, rooms[i++]);
        }
    }
}

