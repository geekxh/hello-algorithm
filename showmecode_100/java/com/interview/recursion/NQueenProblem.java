package com.interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Date 02/20/2016
 * @author Tushar Roy
 *
 * Given nxn board place n queen on this board so that they dont attack each other. One solution is to find
 * any placement of queens which do not attack each other. Other solution is to find all placements of queen
 * on the board.
 *
 * Time complexity O(n*n)
 * Space complexity O(n*n)
 */
public class NQueenProblem {

    class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public Position[] solveNQueenOneSolution(int n) {
        Position[] positions = new Position[n];
        boolean hasSolution = solveNQueenOneSolutionUtil(n, 0, positions);
        if (hasSolution) {
            return positions;
        } else {
            return new Position[0];
        }
    }

    private boolean solveNQueenOneSolutionUtil(int n, int row, Position[] positions) {
        if (n == row) {
            return true;
        }
        int col;
        for (col = 0; col < n; col++) {
            boolean foundSafe = true;

            //check if this row and col is not under attack from any previous queen.
            for (int queen = 0; queen < row; queen++) {
                if (positions[queen].col == col || positions[queen].row - positions[queen].col == row - col ||
                        positions[queen].row + positions[queen].col == row + col) {
                    foundSafe = false;
                    break;
                }
            }
            if (foundSafe) {
                positions[row] = new Position(row, col);
                if (solveNQueenOneSolutionUtil(n, row + 1, positions)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     *Solution for https://leetcode.com/problems/n-queens/
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        Position[] positions = new Position[n];
        solve(0, positions, result, n);
        return result;
    }

    public void solve(int current, Position[] positions, List<List<String>> result, int n) {
        if (n == current) {
            StringBuffer buff = new StringBuffer();
            List<String> oneResult = new ArrayList<>();
            for (Position p : positions) {
                for (int i = 0; i < n; i++) {
                    if (p.col == i) {
                        buff.append("Q");
                    } else {
                        buff.append(".");
                    }
                }
                oneResult.add(buff.toString());
                buff = new StringBuffer();

            }
            result.add(oneResult);
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean foundSafe = true;
            for (int j = 0; j < current; j++) {
                if (positions[j].col == i || positions[j].col - positions[j].row == i - current || positions[j].row + positions[j].col == i + current) {
                    foundSafe = false;
                    break;
                }
            }
            if (foundSafe) {
                positions[current] = new Position(current, i);
                solve(current + 1, positions, result, n);
            }
        }
    }

    public static void main(String args[]) {
        NQueenProblem s = new NQueenProblem();
        Position[] positions = s.solveNQueenOneSolution(6);
        Arrays.stream(positions).forEach(position -> System.out.println(position.row + " " + position.col));
    }
}

