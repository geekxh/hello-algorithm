package com.interview.graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 *      -1 - A wall or an obstacle.
 *      0 - A gate.
 *      INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as
 *      you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF
 *
 * Time complexity O(n*m)
 * Space complexity O(n*m)
 *
 * https://leetcode.com/problems/walls-and-gates/
 */
public class WallsAndGates {
    private static final int d[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGates(int[][] rooms) {
        Deque<Cell> queue = new LinkedList<>();
        gates(rooms, queue);

        while (!queue.isEmpty()) {
            Cell cell = queue.pollLast();
            addNeighbors(rooms, cell.row, cell.col, queue);
        }

    }

    private  void addNeighbors(int[][] rooms, int row, int col, Deque<Cell> queue) {
        for (int[] d1 : d) {
            int r1 = row + d1[0];
            int c1 = col + d1[1];
            if (r1 < 0 || c1 < 0 || r1 >= rooms.length || c1 >= rooms[0].length || rooms[r1][c1] != INF) {
                continue;
            }
            rooms[r1][c1] = 1 + rooms[row][col];
            queue.offerFirst(new Cell(r1, c1));
        }
    }

    private void gates(int[][] rooms, Deque<Cell> queue) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.offerFirst(new Cell(i, j));
                }
            }
        }
    }

    class Cell {
        int row;
        int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}