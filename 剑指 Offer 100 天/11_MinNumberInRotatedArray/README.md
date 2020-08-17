## 旋转数组的最小数字

### 题目描述
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组 `{3,4,5,1,2}` 为 `{1,2,3,4,5}` 的一个旋转，该数组的最小值为 `1`。 

**NOTE：**给出的所有元素都大于 `0`，若数组大小为 `0`，请返回 `0`。


### 解法
#### 解法一
直接遍历数组找最小值，时间复杂度 `O(n)`，不推荐。

```java

/**
 * @author Anonymous
 * @since 2019/10/30
 */

public class Solution {
    /**
     * 获取旋转数组的最小元素
     * @param array 旋转数组
     * @return 数组中的最小值
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int n = array.length;
        if (n == 1 || array[0] < array[n - 1]) {
            return array[0];
        }

        int min = array[0];
        for (int i = 1; i < n; ++i) {
            min = array[i] < min ? array[i] : min;
        }

        return min;
    }

}
```

#### 解法二
利用指针 `p`,`q` 指向数组的首尾，如果 `array[p] < array[q]`，说明数组是递增数组，直接返回 `array[p]`。否则进行如下讨论。

计算中间指针 `mid`：
- 如果此时 `array[p]`, `array[q]`, `array[mid]` 两两相等，此时无法采用二分方式，只能通过遍历区间 `[p,q]` 获取最小值；
- 如果此时 `p`,`q` 相邻，说明此时 `q` 指向的元素是最小值，返回 `array[q]`；
- 如果此时 `array[mid] >= array[p]`，说明 `mid` 位于左边的递增数组中，最小值在右边，因此，把 `p` 指向 `mid`，此时保持了 `p` 指向左边递增子数组；
- 如果此时 `array[mid] <= array[q]`，说明 `mid` 位于右边的递增数组中，最小值在左边，因此，把 `q` 指向 `mid`，此时保持了 `q` 指向右边递增子数组。

```java


/**
 * @author Anonymous
 * @since 2019/10/30
 */

public class Solution {
    /**
     * 获取旋转数组的最小元素
     * @param array 旋转数组
     * @return 数组中的最小值
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int p = 0;
        // mid初始为p，为了兼容当数组是递增数组(即不满足 array[p] >= array[q])时，返回 array[p]
        int mid = p;
        int q = array.length - 1;
        while (array[p] >= array[q]) {
            if (q - p == 1) {
                // 当p,q相邻时(距离为1)，那么q指向的元素就是最小值
                mid = q;
                break;
            }
            mid = p + ((q - p) >> 1);

            // 当p,q,mid指向的值相等时，此时只能通过遍历查找最小值
            if (array[p] == array[q] && array[mid] == array[p]) {
                mid = getMinIndex(array, p, q);
                break;
            }

            if (array[mid] >= array[p]) {
                p = mid;
            } else if (array[mid] <= array[q]) {
                q = mid;
            }
        }

        return array[mid];


    }

    private int getMinIndex(int[] array, int p, int q) {
        int minIndex = p;
        for (int i = p + 1; i <= q; ++i) {
            minIndex = array[i] < array[minIndex] ? i : minIndex;
        }
        return minIndex;
    }
}
```


### 测试用例
1. 功能测试（输入的数组是升序排序数组的一个旋转，数组中有重复数字或者没有重复数字）；
2. 边界值测试（输入的数组是一个升序排序的数组，只包含一个数字的数组）；
3. 特殊输入测试（输入空指针）。