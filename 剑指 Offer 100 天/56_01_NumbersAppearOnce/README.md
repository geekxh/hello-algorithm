## [数组中只出现一次的两个数字](https://www.acwing.com/problem/content/69/)

### 题目描述
一个整型数组里除了两个数字之外，其他的数字都出现了两次。

请写程序找出这两个只出现一次的数字。

你可以假设这两个数字一定存在。

**样例**
```
输入：[1,2,3,3,4,4]

输出：[1,2]
```

### 解法
如果数组有一个数字出现一次，其它数字都出现两次。那么我们很容易通过异或 `^` 运算求出来。

而现在是有两个数字出现一次，那么我们考虑一下怎么将这两个数字隔开，之后我们对隔开的数组分别进行异或，不就求出来了？

我们先异或，求得的结果是两个不相同的数字异或的结果，结果一定不为 0。那么它的二进制表示中一定有 1。我们根据这个 1 在二进制中出现的位置。将数组划分，这样，两个只出现一次的数字就会被隔开，之后求异或即可。

```java
/**
 * @author Anonymous
 * @since 2019/12/10
 */

class Solution {
    /**
     * 求数组中只出现一次的两个数字
     * 
     * @param nums 数字
     * @return 两个数字组成的数组
     */
    public int[] findNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int xorRes = 0;
        for (int e : nums) {
            xorRes ^= e;
        }
        int[] res = new int[2];
        int index = indexOf1(xorRes);
        for (int e : nums) {
            if (isBit1(e, index)) {
                res[0] ^= e;
            } else {
                res[1] ^= e;
            }
        }
        return res;


    }

    private int indexOf1(int val) {
        int index = 0;
        while ((val & 1) == 0) {
            val = val >> 1;
            ++index;
        }
        return index;
    }

    private boolean isBit1(int val, int index) {
        val = val >> index;
        return (val & 1) == 1;
    }
}
```


### 测试用例
1. 功能测试（数组中有多对重复的数字；数组中没有重复的数字）。