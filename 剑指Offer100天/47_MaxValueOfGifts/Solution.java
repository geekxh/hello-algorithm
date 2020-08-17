/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {
    /**
     * 获取礼物的最大价值
     * 
     * @param grid 数组
     * @return 最大价值
     */
    public int getMaxValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        res[0][0] = grid[0][0];
        for (int j = 1; j < n; ++j) {
            res[0][j] = res[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]) + grid[i][j];
            }
        }
        return res[m - 1][n - 1];
    }
}