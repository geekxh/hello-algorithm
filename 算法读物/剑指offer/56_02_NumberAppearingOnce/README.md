## [数组中唯一只出现一次的数字](https://www.acwing.com/problem/content/70/)

### 题目描述
在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。

请找出那个只出现一次的数字。

你可以假设满足条件的数字一定存在。

**思考题：**

- 如果要求只使用 `O(n)` 的时间和额外 `O(1)` 的空间，该怎么做呢？

### 解法
分别累加数组中每个元素的二进制中出现的数字，那么出现三次的数字，二进制位上最后累加的结果一定能被 3 整除。不能被 3 整除的位，就属于只出现一次的数字。

```java
/**
 * @author Anonymous
 * @since 2019/12/10
 */

class Solution {
    /**
     * 找出数组中只出现一次的数字，其它数字都出现三次
     *
     * @param nums 数字
     * @return 只出现一次的数字
     */
    public int findNumberAppearingOnce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] bits = new int[32];
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int val = nums[i];
            for (int j = 0; j < 32; ++j) {
                bits[j] += (val & 1);
                val = val >> 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 != 0) {
                res += Math.pow(2, i);
            }
        }
        return res;
    }
}
```

### 测试用例
1. 功能测试（唯一只出现一次的数字分别是 0、正数、负数；重复出现三次的数字分别是 0、正数、负数）。