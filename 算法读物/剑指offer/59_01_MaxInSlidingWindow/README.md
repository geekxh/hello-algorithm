## 滑动窗口的最大值

### 题目描述
给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。

例如，如果输入数组 `[2, 3, 4, 2, 6, 2, 5, 1]` 及滑动窗口的大小 3,那么一共存在 6 个滑动窗口，它们的最大值分别为 `[4, 4, 6, 6, 6, 5]`。

**注意：**

- 数据保证 k 大于 0，且 k 小于等于数组长度。

**样例**

```
输入：[2, 3, 4, 2, 6, 2, 5, 1] , k=3

输出: [4, 4, 6, 6, 6, 5]
```

### 解法
使用一个双端队列，保证队首存放的是窗口最大值的下标。遍历数组，

1. 队尾元素比要入队的元素小，则把其移除（因为不可能成为窗口最大值）。
2. 队首下标对应的元素不在窗口内（即窗口最大值），将其从队列中移除。
3. 把每次滑动值的下标加入队列中（经过步骤1、2，此时加入队列的下标要么是当前窗口最大值的下标，要么是小于窗口最大值的下标）。
4. 滑动窗口的首地址i大于size就写入窗口最大值。

time complexity:O(n)

space complexity:O(k) , k is the size

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/02/05
 * @description
 */
class Solution {
    
    public ArrayList<Integer> maxInWindows(int[] num, int size) {

        ArrayList<Integer> reList = new ArrayList<>();
        if (num == null || num.length < size || size < 1) {
            return reList;
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {

            // 队尾元素比要入队的元素小，则把其移除（因为不可能成为窗口最大值）
            while (!deque.isEmpty() && num[deque.getLast()] <= num[i]) {
                deque.pollLast();
            }
            // 队首下标对应的元素不在窗口内（即窗口最大值），将其从队列中移除
            while (!deque.isEmpty() && (i - deque.getFirst() + 1 > size)) {
                deque.pollFirst();
            }
            // 把每次滑动的值加入到队列中
            deque.add(i);
            // 滑动窗口的首地址i大于size就写入窗口最大值
            if (!deque.isEmpty() && i + 1 >= size) {
                reList.add(num[deque.getFirst()]);
            }
        }

        return reList;
    }
}
```

### 测试用例
1. 功能测试（输入数组的数字大小无序；输入数组的数字单调递增；输入数组的数字单调递减）；
2. 边界值测试（滑动窗口的大小为 0、1、等于输入数组的长度、大于输入数组的长度）；
3. 特殊输入测试（输入数组为空）。