## 获取数组中最小的k个数

### 题目描述
输入 n 个整数，找出其中最小的 K 个数。例如输入 `4,5,1,6,2,7,3,8` 这 8 个数字，则最小的 4 个数字是 `1,2,3,4`。

### 解法
#### 解法一
利用快排中的 partition 思想。

数组中有一个数字出现次数超过了数组长度的一半，那么排序后，数组中间的数字一定就是我们要找的数字。我们随机选一个数字，利用 partition() 函数，使得比选中数字小的数字都排在它左边，比选中数字大的数字都排在它的右边。

判断选中数字的下标 `index`：

- 如果 `index = k-1`，结束循环，返回前 k 个数。
- 如果 `index > k-1`，那么接着在 index 的左边进行 partition。
- 如果 `index < k-1`，则在 index 的右边继续进行 partition。

**注意**，这种方法会修改输入的数组。时间复杂度为 `O(n)`。

```java
import java.util.ArrayList;

/**
 * @author Anonymous
 * @since 2019/12/6
 */

public class Solution {

    /**
     * 获取数组中最小的k个数
     *
     * @param input 输入的数组
     * @param k 元素个数
     * @return 最小的k的数列表
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || input.length < k || k < 1) {
            return res;
        }
        int n = input.length;
        int start = 0, end = n - 1;
        int index = partition(input, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(input, start, end);
        }
        for (int i = 0; i < k; ++i) {
            res.add(input[i]);
        }
        return res;
    }

    private int partition(int[] input, int start, int end) {
        int index = start - 1;
        for (int i = start; i < end; ++i) {
            if (input[i] < input[end]) {
                swap(input, i, ++index);
            }
        }
        ++index;
        swap(input, index, end);
        return index;
    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
```

#### 解法二
利用大根堆，存储最小的 k 个数，最后返回即可。

此方法时间复杂度为 `O(nlogk)`。虽然慢一点，但是它不会改变输入的数组，并且它**适合海量数据的输入**。

假设题目要求从海量的数据中找出最小的 k 个数，由于内存的大小是有限的，有可能不能把这些海量的数据一次性全部载入内存。这个时候，用这种方法是最合适的。就是说它适合 n 很大并且 k 较小的问题。

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Anonymous
 * @since 2019/12/6
 */

public class Solution {

    /**
     * 获取数组中最小的k个数
     *
     * @param input 输入的数组
     * @param k 元素个数
     * @return 最小的k的数列表
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || input.length < k || k < 1) {
            return res;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        System.out.println(maxHeap.size());
        for (int e : input) {
            if (maxHeap.size() < k) {
                maxHeap.add(e);
            } else {
                if (maxHeap.peek() > e) {
                    maxHeap.poll();
                    maxHeap.add(e);
                }

            }
        }
        res.addAll(maxHeap);
        return res;
    }
}
```

### 测试用例
1. 功能测试（输入的数组中存在/不存在一个出现次数超过数组长度一半的数字）；
2. 特殊输入测试（输入的数组只有一个数字；输入空指针）。