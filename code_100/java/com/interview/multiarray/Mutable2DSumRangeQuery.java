package com.interview.multiarray;

/**
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 *
 * https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class Mutable2DSumRangeQuery {
    private int[][] prefixSum;
    private int rows;
    private int cols;
    private int[][] matrix;

    public Mutable2DSumRangeQuery(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        prefixSum = new int[matrix.length][matrix[0].length + 1];
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j] = prefixSum[i][j - 1] + matrix[i][j - 1];
            }
        }

    }

    public void update(int row, int col, int val) {
        int delta = val - matrix[row][col];
        matrix[row][col] = val;
        for (int i = col + 1; i <= cols; i++) {
            prefixSum[row][i] += delta;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += prefixSum[i][col2 + 1] - prefixSum[i][col1];
        }
        return sum;
    }
}