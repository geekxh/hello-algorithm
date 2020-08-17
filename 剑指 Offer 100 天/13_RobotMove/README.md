## 机器人的移动范围

### 题目描述
地上有一个`m`行和`n`列的方格。一个机器人从坐标`0,0`的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于`k`的格子。 例如，当`k`为`18`时，机器人能够进入方格`（35,37）`，因为`3+5+3+7 = 18`。但是，它不能进入方格`（35,38）`，因为`3+5+3+8 = 19`。请问该机器人能够达到多少个格子？

### 解法
从坐标(0, 0) 开始移动，当它准备进入坐标(i, j)，判断是否能进入，如果能，再判断它能否进入 4 个相邻的格子 (i-1, j), (i+1, j), (i, j-1), (i, j+1)。

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 计算能到达的格子数
     * @param threshold 限定的数字
     * @param rows 行数
     * @param cols 列数
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
            return 1
                    + getCount(threshold, i - 1, j, rows, cols, visited)
                    + getCount(threshold, i + 1, j, rows, cols, visited)
                    + getCount(threshold, i, j - 1, rows, cols, visited)
                    + getCount(threshold, i, j + 1, rows, cols, visited);
        }
        return 0;
    }

    private boolean check(int threshold, int i, int j, int rows, int cols, boolean[] visited) {
        return i >= 0
                && i < rows
                && j >= 0
                && j < cols
                && !visited[i * cols + j]
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
```

### 测试用例
1. 功能测试（方格为多行多列；k 为正数）；
2. 边界值测试（方格只有一行或者一列；k = 0）；
3. 特殊输入测试（k < 0）。