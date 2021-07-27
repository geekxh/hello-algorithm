## 数字在排序数组中出现的次数

### 题目描述
统计一个数字在排序数组中出现的次数。

例如输入排序数组 `[1, 2, 3, 3, 3, 3, 4, 5]` 和数字 3，由于 3 在这个数组中出现了 4 次，因此输出 4。

**样例**

```
输入：[1, 2, 3, 3, 3, 3, 4, 5] ,  3

输出：4
```

### 解法
找出第一个 k 和最后一个 k 出现的位置。

找第一个 k 时，利用二分法，如果 `nums[m] == k`，判断它的前一个位置是不是也是 k，如果不是，说明这是第一个 k，直接返回。如果是，那么递归在左边查找第一个 k。

找最后一个 k 也同理。


```java
/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {
    /**
     * 求数字k在排序数组中出现的次数
     *
     * @param nums 数组
     * @param k 数字k
     * @return k在数组中出现的次数
     */
    public int getNumberOfK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        int first = getFirstK(nums, start, end, k);
        int last = getLastK(nums, start, end, k);
        if (first > -1 && last > -1) {
            return last - first + 1;
        }
        return 0;
    }

    private int getFirstK(int[] nums, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int m = start + ((end - start) >> 1);
        if (nums[m] == k) {
            if (m == 0 || (m > 0 && nums[m - 1] != k)) {
                return m;
            } else {
                end = m - 1;
            }
        } else {
            if (nums[m] > k) {
                end = m - 1;
            } else {
                start = m + 1;
            }
        }
        return getFirstK(nums, start, end, k);
    }

    private int getLastK(int[] nums, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int m = start + ((end - start) >> 1);
        if (nums[m] == k) {
            if (m == nums.length - 1 || (m < nums.length - 1 && nums[m + 1] != k)) {
                return m;
            } else {
                start = m + 1;
            }
        } else {
            if (nums[m] > k) {
                end = m - 1;
            } else {
                start = m + 1;
            }
        }
        return getLastK(nums, start, end, k);

    }
}
```

### 测试用例
1. 功能测试（数组中包含要查找的数字；数组中没有要查找的数字；要查找的数字在数组中出现一次/多次）；
2. 边界值测试（查找数组中的最大值、最小值；数组中只有一个数字）；
3. 特殊输入测试（表示数组的指针为空指针）。