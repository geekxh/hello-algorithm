package com.interview.dynamic;

/**
 * Date 03/11/2016
 * @author Tushar Roy
 *
 * Given a 2D array find the sum in given range defining a rectangle.
 *
 * Time complexity construction O(n*m)
 * Time complexity of query O(1)
 * Space complexity is O(n*m)
 * 
 * Reference
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class Immutable2DSumRangeQuery {
    private int[][] T;

    public Immutable2DSumRangeQuery(int[][] matrix) {
        int row = 0;
        int col = 0;
        if (matrix.length != 0) {
            row = matrix.length;
            col = matrix[0].length;
        }
        T = new int[row + 1][col + 1];
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = T[i - 1][j] + T[i][j - 1] + matrix[i - 1][j - 1] - T[i - 1][j - 1];
            }
        }
   }

    public int sumQuery(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return T[row2][col2] - T[row1 - 1][col2] - T[row2][col1 - 1] + T[row1 - 1][col1 - 1];
    }

    public static void main(String args[]) {
        int[][] input = {{3, 0, 1, 4, 2},
                        {5, 6, 3, 2, 1},
                        {1, 2, 0, 1, 5},
                        {4, 1, 0, 1, 7},
                        {1, 0, 3, 0, 5}};

        int[][] input1 = {{2,0,-3,4}, {6, 3, 2, -1}, {5, 4, 7, 3}, {2, -6, 8, 1}};
        Immutable2DSumRangeQuery isr = new Immutable2DSumRangeQuery(input1);
        System.out.println(isr.sumQuery(1, 1, 2, 2));
    }
}
