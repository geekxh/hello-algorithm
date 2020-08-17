## 连续子数组的最大和

### 题目描述
输入一个**非空**整型数组，数组里的数可能为正，也可能为负。
数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为`O(n)`。

### 解法
动态规划法。

res[i] 表示以第 i 个数字结尾的子数组的最大和，那么求出 `max(res[i])` 即可。

- `res[i] = array[i]`, if `res[i - 1] < 0`
- `res[i] = res[i - 1] + array[i]`, if `res[i - 1] >= 0`

```java

/**
 * @author Anonymous
 * @since 2019/12/7
 */

public class Solution {
    /**
     * 求连续子数组的最大和
     *
     * @param array 数组
     * @return 最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int n = array.length;
        int[] res = new int[n];
        res[0] = array[0];
        int max = res[0];
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] > 0 ? res[i - 1] + array[i] : array[i];
            max = Math.max(max, res[i]);
        }
        return max;
    }
}


```

### 测试用例
1. 功能测试（输入的数组中有正数也有负数；输入的数组中全是正数；输入的数组中全是负数）；
2. 特殊输入测试（表示数组的指针位为空指针）。