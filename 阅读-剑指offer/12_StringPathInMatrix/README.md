## 矩阵中的路径

### 题目描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 `a b c e s f c s a d e e` 这样的 `3 X 4` 矩阵中包含一条字符串`"bcced"`的路径，但是矩阵中不包含`"abcb"`路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

### 解法
回溯法。首先，任选一个格子作为路径起点。假设格子对应的字符为 ch，并且对应路径上的第 i 个字符。若相等，到相邻格子寻找路径上的第 i+1 个字符。重复这一过程。

```java
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

```

### 测试用例
1. 功能测试（在多行多列的矩阵中存在或者不存在路径）；
2. 边界值测试（矩阵只有一行或者一列；矩阵和路径中的所有字母都是相同的）；
3. 特殊输入测试（输入空指针）。