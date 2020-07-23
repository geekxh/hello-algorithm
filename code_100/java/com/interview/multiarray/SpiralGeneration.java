package com.interview.multiarray;

/**
 * Date 07/31/2016
 * @author Tushar Roy
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralGeneration {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        int index = 1;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                result[up][i] = index++;
            }
            up++;

            for (int i = up; i <= down; i++) {
                result[i][right] = index++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                result[down][i] = index++;
            }
            down--;

            for (int i = down; i >= up; i--) {
                result[i][left] = index++;
            }
            left++;
        }
        return result;
    }

    public static void main(String args[]) {
        SpiralGeneration sg = new SpiralGeneration();
        int[][] r = sg.generateMatrix(4);
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }
    }
}
