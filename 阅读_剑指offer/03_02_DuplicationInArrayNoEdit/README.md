## 不修改数组找出重复的数字

### 题目描述
在一个长度为 `n+1` 的数组里的所有数字都在 `1` 到 `n` 的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的数组。例如，如果输入长度为 `8` 的数组 `{2, 3, 5, 4, 3, 2, 6, 7}`，那么对应的输出是重复的数字 `2` 或者 `3`。


### 解法
#### 解法一
创建长度为 `n+1` 的辅助数组，把原数组的元素复制到辅助数组中。如果原数组被复制的数是 `m`，则放到辅助数组第 `m` 个位置。这样很容易找出重复元素。空间复杂度为 `O(n)`。

#### 解法二
数组元素的取值范围是 `[1, n]`，对该范围对半划分，分成 `[1, middle]`, `[middle+1, n]`。计算数组中有多少个(count)元素落在 `[1, middle]` 区间内，如果 count 大于 middle-1+1，那么说明这个范围内有重复元素，否则在另一个范围内。继续对这个范围对半划分，继续统计区间内元素数量。

时间复杂度 `O(n * log n)`，空间复杂度 `O(1)`。

注意，此方法无法找出所有重复的元素。

```java
/**
 * @author Anonymous
 * @since 2019/10/27
 */

public class Solution {
    /**
     * 不修改数组查找重复的元素，没有则返回-1
     * @param numbers 数组
     * @return 重复的元素
     */
    public int getDuplication(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }

        int start = 1;
        int end = numbers.length - 1;
        while (end >= start) {
            int middle = start + ((end - start) >> 1);

            // 调用 log n 次
            int count = countRange(numbers, start, middle);
            if (start == end) {
                if (count > 1) {
                    return start;
                }
                break;
            } else {
                // 无法找出所有重复的数
                if (count > (middle - start) + 1) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 计算整个数组中有多少个数的取值在[start, end] 之间
     * 时间复杂度 O(n)
     * @param numbers 数组
     * @param start 左边界
     * @param end 右边界
     * @return 数量
     */
    private int countRange(int[] numbers, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for(int e : numbers) {
            if (e >= start && e <= end) {
                ++count;
            }
        }
        return count;
    }
}
```

### 测试用例
1. 长度为 n 的数组中包含一个或多个重复的数字；
2. 数组中不包含重复的数字；
3. 无效测试输入用例（输入空指针）。