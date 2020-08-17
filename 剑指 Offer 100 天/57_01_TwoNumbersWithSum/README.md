## 和为S的两个数字

### 题目描述
输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。ps：对应每个测试案例，输出两个数，小的先输出。

### 解法
定义两个指针，start指向数组头，end指向数组末尾。如果：

- `sum == array[start] + array[end]`，则返回结果
- `sum > array[start] + array[end]`，则start++，因为数组是递增的，所以从小数右边找一个大数与 `array[end]` 求和再次判断
- 否则 end--

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/02/02
 * @description
 */
public class Solution {
    
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

        ArrayList<Integer> reList = new ArrayList<>();

        if (array == null || array.length < 2 || sum <= array[0]) {
            return reList;
        }

        int start = 0;
        int end = array.length - 1;

        while (start < end) {

            int curSum = array[start] + array[end];
            if (curSum == sum) {
                reList.add(array[start]);
                reList.add(array[end]);
                return reList;
            } else if (curSum < sum) {
                start++;
            } else {
                end--;
            }
        }

        // 查无
        return reList;
    }
}
```

### 测试用例
1. 功能测试（数组中存在和为 s 的两个数；数组中不存在和为 s 的两个数）；
2. 特殊输入测试（表示数组的指针为空指针）。