/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 计算能到达的格子数
     * 
     * @param threshold 限定的数字
     * @param rows      行数
     * @param cols      列数
     * @return 格子数
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 1 || cols < 1) {
            return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        return getCount(threshold, 0, 0, rows, cols, visited);
    }

    private int getCount(int threshold, int i, int j, int rows, int cols, boolean[] visited) {
        if (check(threshold, i, j, rows, cols, visited)) {
            visited[i * cols + j] = true;
            return 1 + getCount(threshold, i - 1, j, rows, cols, visited)
                    + getCount(threshold, i + 1, j, rows, cols, visited)
                    + getCount(threshold, i, j - 1, rows, cols, visited)
                    + getCount(threshold, i, j + 1, rows, cols, visited);
        }
        return 0;
    }

    private boolean check(int threshold, int i, int j, int rows, int cols, boolean[] visited) {
        return i >= 0 && i < rows && j >= 0 && j < cols && !visited[i * cols + j]
                && getDigitSum(i) + getDigitSum(j) <= threshold;
    }

    private int getDigitSum(int i) {
        int res = 0;
        while (i > 0) {
            res += i % 10;
            i /= 10;
        }
        return res;
    }
}
