## 数组中出现次数超过一半的数字

### 题目描述
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为 9 的数组 `{1,2,3,2,2,2,5,4,2}`。由于数字 2 在数组中出现了 5 次，超过数组长度的一半，因此输出 2。如果不存在则输出 0。

### 解法
#### 解法一
利用快排中的 partition 思想。

数组中有一个数字出现次数超过了数组长度的一半，那么排序后，数组中间的数字一定就是我们要找的数字。我们随机选一个数字，利用 partition() 函数，使得比选中数字小的数字都排在它左边，比选中数字大的数字都排在它的右边。

判断选中数字的下标 `index`：

- 如果 `index = n/2`，那么这个数字就是中位数。
- 如果 `index > n/2`，那么接着在 index 的左边进行 partition。
- 如果 `index < n/2`，则在 index 的右边继续进行 partition。

**注意：**这种方法会修改输入的数组。时间复杂度为 `O(n)`。

```java
/**
 * @author Anonymous
 * @since 2019/12/6
 */

public class Solution {
    /**
     * 查找数组中出现次数超过一次的数字
     *
     * @param array 数组
     * @return 返回该数，不存在则返回0
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int start = 0, end = n - 1;
        int mid = n >> 1;
        int index = partition(array, start, end);
        while (index != mid) {
            if (index > mid) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(array, start, end);
        }

        return isMoreThanHalf(array, array[index]) ? array[index] : 0;
    }

    /**
     * 快排中的 partition 方法
     *
     * @param array 数组
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    private int partition(int[] array, int start, int end) {
        int small = start - 1;
        for (int i =  start; i < end; ++i) {
            if (array[i] < array[end]) {
                swap(array, i, ++small);
            }
        }
        ++small;
        swap(array, small, end);
        return small;

    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 判断val元素是否真的超过数组元素个数的一半
     *
     * @param array 数组
     * @param val 某元素
     * @return boolean
     */
    private boolean isMoreThanHalf(int[] array, int val) {
        int cnt = 0;
        for (int e : array) {
            if (e == val) {
                ++cnt;
            }
        }
        
        return cnt * 2 > array.length;
    }
}
```

#### 解法二
利用多数投票算法，从头到尾遍历数组，遇到两个不一样的数就把这两个数同时除去。除去的两个数可能都不是 majority，也可能一个是 majority 另一个不是，但是因为 majority 总数大于一半，所以这么删完最后剩下的肯定是 majority。

此方法时间复杂度为 `O(n)`，且不会改变数组。

```java
/**
 * @author Anonymous
 * @since 2019/12/6
 */

public class Solution {
    /**
     * 查找数组中出现次数超过一次的数字
     *
     * @param array 数组
     * @return 返回该数，不存在则返回0
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int res = array[0];
        int times = 1;
        for (int i = 1; i < array.length; ++i) {
            if (times == 0) {
                res = array[i];
                times = 1;
            } else if (array[i] == res) {
                ++times;
            } else {
                --times;
            }
        }

        return isMoreThanHalf(array, res) ? res : 0;
    }


    /**
     * 判断val元素是否真的超过数组元素个数的一半
     *
     * @param array 数组
     * @param val 某元素
     * @return boolean
     */
    private boolean isMoreThanHalf(int[] array, int val) {
        int cnt = 0;
        for (int e : array) {
            if (e == val) {
                ++cnt;
            }
        }

        return cnt * 2 > array.length;
    }
}
```

### 测试用例
1. 功能测试（输入的数组中存在/不存在一个出现次数超过数组长度一半的数字）；
2. 特殊输入测试（输入的数组只有一个数字；输入空指针）。