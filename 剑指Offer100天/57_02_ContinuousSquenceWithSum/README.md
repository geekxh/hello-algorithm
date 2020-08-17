## 和为S的连续正数序列

### 题目描述
输入一个正数 s，打印出所有和为 s 的连续正数序列（至少含有两个数）。

例如输入 15，由于 `1+2+3+4+5=4+5+6=7+8=15`，所以结果打印出 3 个连续序列 1～5、4～6 和 7～8。

**样例**
```
输入：15

输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

### 解法
这道题同样利用两个指针left和right，将(1,2)作为初始序列。当序列和大于所求值，则left向前走，把最小的数排除了；当序列和小于所求值，则right向前走，把一个更大的数包进序列中；如果序列和等于所求值，则求值区间[left,right]中的所有数并加入到列表中，并且right向前走，把一个更大的值包入序列中。循环直到 `left < (sum + 1)/2`  。

这道题的time complexity为O(n^2)，space complexity为O(1)

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/02/03
 * @description
 */
public class Solution {
    
    public List<List<Integer>> findContinuousSequence(int sum) {

        List<List<Integer>> reList = new ArrayList<>();

        if (sum < 3) {
            return reList;
        }

        int left = 1;
        int right = 2;
        int mid = (sum + 1) / 2;
        int curSum = left + right;

        // left小于sum一半即可(1/2n)
        while (left < mid) {

            // 等与sum则加入列表中(2~1/2n)
            if (curSum == sum) {
                reList.add(getListFromleftToright(left, right));
                // right增加并重新寻找序列
                right++;
                curSum += right;
            } else if (curSum > sum) {
                curSum -= left;
                left++;
            } else {
                right++;
                curSum += right;
            }
        }

        return reList;
    }

    private List<Integer> getListFromleftToright(int left, int right) {

        List<Integer> tempList = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            tempList.add(i);
        }

        return tempList;
    }
}
```

### 测试用例
1. 功能测试（存在和为 s 的连续序列，如 9、100 等；不存在和为 s 的连续序列，如 4、0 等）；
2. 边界值测试（连续序列的最小和 3）。