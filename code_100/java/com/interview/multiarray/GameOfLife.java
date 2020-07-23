package com.interview.multiarray;

/**
 * Date 10/20/2016
 * @author Tushar Roy
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following
 * four rules (taken from the above Wikipedia article):
 * Read full qs on leetcode.
 *
 * Solution - Keep two array prev and current. Fill the values in current array. As soon as current row is done
 * replace elemments of board with prev array.
 *
 * Time complexity O(n * m)
 *
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int n = board.length;
        int m = board[0].length;
        int[] prevRow = new int[m];
        int[] currentRow = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                currentRow[j] = doesLive(i, j, board) ? 1 : 0;
            }
            if (i != 0) {
                copyRow(prevRow, board[i - 1]);
            }
            if (i != n - 1) {
                copyRow(currentRow, prevRow);
            }
        }
        copyRow(currentRow, board[n - 1]);
    }

    private void copyRow(int[] source, int[] dest) {
        for (int i = 0; i < source.length; i++) {
            dest[i] = source[i];
        }
    }

    private boolean doesLive(int x, int y, int[][] board) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (x == i && y == j) {
                    continue;
                }
                if (i < 0 || i >= board.length) {
                    break;
                }
                if (j < 0 || j >= board[0].length) {
                    continue;
                }
                count += board[i][j];
            }
        }
        if (board[x][y] == 1) {
            return count == 2 || count == 3;
        } else {
            return count == 3;
        }
    }
}
