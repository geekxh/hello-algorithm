## 把数组排成最小的数

### 题目描述
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

例如输入数组 `[3, 32, 321]`，则打印出这3个数字能排成的最小数字`321323`。

### 解法


```java
import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {

    /**
     * 打印数组元素组成的最小的数字
     *
     * @param nums 数组
     * @return 最小的数字
     */
    public String printMinNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int n = nums.length;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strNums, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String str : strNums) {
            sb.append(str);
        }
        return sb.toString();
    }
}
```

### 测试用例
1. 功能测试（输入的数组中有多个数字；输入的数组中的数字有重复的数位；输入的数组中只有一个数字）；
2. 特殊输入测试（表示数组的指针为空指针）。