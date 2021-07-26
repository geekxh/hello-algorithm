## 数组中数值和下标相等的元素

### 题目描述
假设一个单调递增的数组里的每个元素都是整数并且是唯一的。

请编程实现一个函数找出数组中任意一个数值等于其下标的元素。

例如，在数组 `[-3, -1, 1, 3, 5]` 中，数字 3 和它的下标相等。

**样例**
```
输入：[-3, -1, 1, 3, 5]

输出：3
```

**注意**:如果不存在，则返回 -1。

### 解法
二分法查找。
- 当前元素等于对应的下标，直接返回该下标；
- 当前元素大于该下标，在左边查找；
- 当前元素小于该下标，在右边查找。


```java
/**
 * @author Anonymous
 * @since 2019/12/10
 */

class Solution {
    /**
     * 找出单调递增数组中数值和下标相等的元素
     *
     * @param nums 数组
     * @return 数值与下标相等的元素
     */
    public int getNumberSameAsIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == mid) {
                return mid;
            }
            if (nums[mid] < mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
```

### 测试用例
1. 功能测试（数组中包含或者不包含数值和下标相等的元素）；
2. 边界值测试（数组中只有一个数字；数值和下标相等的元素位于数组的开头或者结尾）；
3. 特殊输入测试（表示数组的指针为空指针）。