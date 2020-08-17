## 礼物的最大价值

### 题目描述
在一个 `m×n` 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。

你可以从棋盘的左上角开始拿格子里的礼物，并每次向左或者向下移动一格直到到达棋盘的右下角。

给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？

### 解法
写出递推式，res 表示获得的最大礼物。

```java
res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]) + grid[i][j];
```


```java
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
```

### 测试用例
1. 功能测试（多行多列的矩阵；一行或者一列的矩阵；只有一个数字的矩阵）；
2. 特殊输入测试（指向矩阵数组的指针为空指针）。