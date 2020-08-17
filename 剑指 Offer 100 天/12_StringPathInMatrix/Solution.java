/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 判断矩阵中是否包含某条路径
     * @param matrix 矩阵
     * @param rows 行数
     * @param cols 列数
     * @param str 路径
     * @return bool
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) {
            return false;
        }
        boolean[] visited = new boolean[matrix.length];
        int pathLength = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (hasPath(matrix, rows, cols, str, i, j, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[] matrix, int rows, int cols, char[] str, int i, int j, int pathLength, boolean[] visited) {
        if (pathLength == str.length) {
            return true;
        }
        boolean hasPath = false;
        if (i >= 0 && i < rows && j >= 0 && j < cols && matrix[i * cols + j] == str[pathLength] && !visited[i * cols + j]) {
            ++pathLength;
            visited[i * cols + j] = true;
            hasPath = hasPath(matrix, rows, cols, str, i - 1, j, pathLength, visited)
                    || hasPath(matrix, rows, cols, str, i + 1, j, pathLength, visited)
                    || hasPath(matrix, rows, cols, str, i, j - 1, pathLength, visited)
                    || hasPath(matrix, rows, cols, str, i, j + 1, pathLength, visited);
            if (!hasPath) {
                --pathLength;
                visited[i * cols + j] = false;
            }
        }
        return hasPath;
    }


}
