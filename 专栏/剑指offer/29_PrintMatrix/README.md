## 顺时针打印矩阵

### 题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下 `4 X 4` 矩阵： 
```
1   2   3   4
5   6   7   8
9   10  11  12
13  14  15  16
```

则依次打印出数字：
```
1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
```
### 解法
剑指offer上的思路有点复杂，需要考虑坐标变换太多，考虑用另一种思路来解决。

在矩阵中，使用左上角坐标(tR,tC)和右下角的坐标(dR,dC)就可以表示一个矩阵。比如题目中的矩阵，当(tR,tC) = (0,0)和(dR,dC) = (3,3)时，表示的子矩阵就是整个矩阵：

```java
1	2	3	4
5			8
9			12
13	14	15	16    
```

当外层循环遍历后，可以令tR和tC加1，dR和dC减1，执行内层循环。当左上角的坐标跑到右下角坐标的右方或者下方，则整个过程就终止。

```java
/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/2
 */
public class Solution {
    /**
     * 转圈打印矩阵
     * @param matrix 矩阵
     * @return 存放结果的list
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> reList = new ArrayList<>();
        if (matrix == null) {
            return reList;
        }

        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        while (tR <= dR && tC <= dC) {
            printMatrix(matrix, tR++, tC++, dR--, dC--, reList);
        }

        return reList;
    }

    public void printMatrix(int[][] matrix, int tR, int tC, int dR, int dC, ArrayList<Integer> reList) {
        // 只有一行
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                reList.add(matrix[tR][i]);
            }
        }
        // 只有一列
        else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                reList.add(matrix[i][tC]);
            }
        } else {
            int curR = tR;
            int curC = tC;
            // 从左到右
            while (curC != dC) {
                reList.add(matrix[tR][curC]);
                curC++;
            }
            // 从上到下
            while (curR != dR) {
                reList.add(matrix[curR][dC]);
                curR++;
            }
            // 从右到左
            while (curC != tC) {
                reList.add(matrix[dR][curC]);
                curC--;
            }
            // 从下到上
            while (curR != tR) {
                reList.add(matrix[curR][tC]);
                curR--;
            }
        }

    }
}
```

### 测试用例
1. 数组中有多行多列；数组中只有一行；数组中只有一列；数组中只有一行一列。