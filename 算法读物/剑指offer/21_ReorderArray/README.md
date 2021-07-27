## 调整数组顺序使奇数位于偶数前面

### 题目描述
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

### 解法
#### 解法一
计算出奇数的个数，就很容易写出来了。

```java
import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/11/21
 */

public class Solution {
    /**
     * 调整数组元素顺序，使得奇数元素位于偶数元素前面，且保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array 数组
     */
    public void reOrderArray(int [] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int numsOfOdd = 0;
        for (int val : array) {
            if (val % 2 == 1) {
                ++numsOfOdd;
            }
        }
        int[] bak = Arrays.copyOf(array, array.length);
        int i = 0, j = numsOfOdd;
        for (int val : bak) {
            if (val % 2 == 1) {
                array[i++] = val;
            } else {
                array[j++] = val;
            }
        }
    }

}
```

#### 解法二
```java
import java.util.Arrays;

public class Solution {
    public void reOrderArray(int [] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Integer[] bak = new Integer[array.length];
        Arrays.setAll(bak, i -> array[i]);
        Arrays.sort(bak, (x, y) -> (y & 1) - (x & 1));
        Arrays.setAll(array, i -> bak[i]);
    }
    
}
```

### 测试用例
1. 功能测试（输入数组中的奇数、偶数交替出现；输入的数组中所有偶数都出现在奇数的前面；输入的数组中所有偶数都出现在奇数的前面；输入的数组中所有奇数都出现在偶数的前面）；
2. 特殊输入测试（输入空指针；输入的数组只包含一个数字）。