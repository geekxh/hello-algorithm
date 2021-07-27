## 二维数组中的查找

### 题目描述
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。


### 解法
从二维数组的右上方开始查找：
- 若元素值等于 `target`，返回 `true`；
- 若元素值大于 `target`，砍掉这一列，即 `--j`；
- 若元素值小于 `target`，砍掉这一行，即 `++i`。

也可以从二维数组的左下方开始查找，以下代码使用左下方作为查找的起点。

注意，不能选择左上方或者右下方的数字，因为这样无法缩小查找的范围。

```java
/**
 * @author Anonymous
 * @since 2019/10/27
 */

public class Solution {
    /**
     * 二维数组中的查找
     * @param target 目标值
     * @param array 二维数组
     * @return boolean
     */
    public boolean find(int target, int[][] array) {
        if (array == null) {
            return false;
        }
        int rows = array.length;
        int columns = array[0].length;
        
        int i = rows - 1;
        int j = 0;
        while (i >= 0 && j < columns) {
            if (array[i][j] == target) {
                return true;
            }
            if (array[i][j] < target) {
                ++j;
            } else {
                --i;
            }
        }
        return false;
    }
}
```

### 测试用例
1. 二维数组中包含查找的数字（查找的数字是数组中的最大值和最小值；查找的数字介于数组中的最大值和最小值之间）；
2. 二维数组中没有查找的数字（查找的数字大于/小于数组中的最大值；查找的数字在数组的最大值和最小值之间但数组中没有这个数字）；
3. 特殊输入测试（输入空指针）。