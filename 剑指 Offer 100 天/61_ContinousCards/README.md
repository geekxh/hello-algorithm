## [扑克牌的顺子](https://www.acwing.com/problem/content/77/)

### 题目描述
从扑克牌中随机抽 `5` 张牌，判断是不是一个顺子，即这5张牌是不是连续的。

`2～10` 为数字本身，`A` 为`1`，`J` 为 `11`，`Q` 为 `12`，`K` 为 `13`，大小王可以看做任意数字。

为了方便，大小王均以 `0` 来表示，并且假设这副牌中大小王均有两张。

**样例1**
```
输入：[8,9,10,11,12]

输出：true
```

**样例2**
```
输入：[0,8,9,11,12]

输出：true
```

### 解法
- 对数组排序；
- 计算出 0 的个数 `zeroCount`；
- 从第一个不是 0 的数字开始遍历，与后一个数字比较，如果相等，直接返回 `false`；否则累计 `gap`；
- 判断 `zeroCount` 是否大于等于 `gap`。


```java
import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {

    /**
     * 判断是否是连续的数字
     *
     * @param numbers 数组
     * @return 是否是顺子
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        int zeroCount = 0;
        Arrays.sort(numbers);
        for (int e : numbers) {
            if (e > 0) {
                break;
            }
            ++zeroCount;
        }

        int p = zeroCount, q = p + 1, n = numbers.length;
        int gap = 0;
        while (q < n) {
            if (numbers[p] == numbers[q]) {
                return false;
            }
            gap += (numbers[q] - numbers[p] - 1);
            p = q;
            ++q;
        }
        return gap <= zeroCount;

    }
}
```

### 测试用例
1. 功能测试（抽出的牌中有一个或者多个大、小王；抽出的牌中没有大、小王；抽出的牌中有对子）；
2. 特殊输入测试（输入空指针）。