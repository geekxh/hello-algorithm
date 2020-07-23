package com.interview.recursion;

import java.util.Arrays;

/**
 * Date 03/24/2016
 * @author Tushar Roy
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * https://leetcode.com/problems/sudoku-solver/
 */
public class SudokuSolver {

    public void solveSudoku(char[][] input) {
        boolean[][] horizontal = new boolean[9][9];
        boolean[][] vertical = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == '.') {
                    continue;
                }
                horizontal[i][input[i][j] - '1'] = true;
                vertical[j][input[i][j] - '1'] = true;
                int index = 3*(i/3) + j/3;
                box[index][input[i][j] - '1'] = true;
            }
        }
        solveSudokuUtil(input, horizontal, vertical, box, 0, 0);
    }

    private boolean solveSudokuUtil(char[][] input, boolean[][] horizontal, boolean[][] vertical, boolean[][] box, int row, int col) {
        if (col == 9) {
            row = row + 1;
            col = 0;
        }
        if (row == 9) {
            return true;
        }

        if (input[row][col] != '.') {
            return solveSudokuUtil(input, horizontal, vertical, box, row, col + 1);
        }

        for (int val = 1; val <= 9; val++) {
            int index = 3*(row/3) + col/3;
            if (horizontal[row][val - 1] == false && vertical[col][val - 1] == false && box[index][val - 1] == false) {
                horizontal[row][val - 1] = true;
                vertical[col][val - 1] = true;
                box[index][val - 1] = true;
                input[row][col] = (char)(val + '0');
                if (solveSudokuUtil(input, horizontal, vertical, box, row, col + 1)) {
                    return true;
                }
                input[row][col] = '.';
                horizontal[row][val - 1] = false;
                vertical[col][val - 1] = false;
                box[index][val - 1] = false;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        SudokuSolver ss = new SudokuSolver();
        char[][] input = new char[9][9];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                input[i][j] = '.';
            }
        }

        input[0] = "..9748...".toCharArray();
        input[1] = "7........".toCharArray();
        input[2] = ".2.1.9...".toCharArray();
        input[3] = "..7...24.".toCharArray();
        input[4] = ".64.1.59.".toCharArray();
        input[5] = ".98...3..".toCharArray();
        input[6] = "...8.3.2.".toCharArray();
        input[7] = "........6".toCharArray();
        input[8] = "...2759..".toCharArray();

        ss.solveSudoku(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}
