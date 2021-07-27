## 矩形覆盖

### 题目描述
我们可以用`2*1`的小矩形横着或者竖着去覆盖更大的矩形。请问用`n`个`2*1`的小矩形无重叠地覆盖一个`2*n`的大矩形，总共有多少种方法？

### 解法
覆盖 `2*n` 的矩形：
- 可以先覆盖 `2*n-1` 的矩形，再覆盖一个 `2*1` 的矩形；
- 也可以先覆盖 `2*(n-2)` 的矩形，再覆盖两个 `1*2` 的矩形。

#### 解法一：利用数组存放结果
```java
/**
 * @author Anonymous
 * @since 2019/11/23
 */

public class Solution {
    /**
     * 矩形覆盖
     * @param target 2*target大小的矩形
     * @return 多少种覆盖方法
     */
    public int RectCover(int target) {
        if (target < 3) {
            return target;
        }
        int[] res = new int[target + 1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= target; ++i) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[target];
    }
}
```

#### 解法二：直接用变量存储结果
```java
/**
 * @author Anonymous
 * @since 2019/11/23
 */

public class Solution {
    /**
     * 矩形覆盖
     * @param target 2*target大小的矩形
     * @return 多少种覆盖方法
     */
    public int RectCover(int target) {
        if (target < 3) {
            return target;
        }
        int res1 = 1;
        int res2 = 2;
        int res = 0;
        for (int i = 3; i <= target; ++i) {
            res = res1 + res2;
            res1 = res2;
            res2 = res;
        }
        return res;
    }
}
```


### 测试用例
1. 功能测试（如输入 3、5、10 等）；
2. 边界值测试（如输入 0、1、2）；
3. 性能测试（输入较大的数字，如 40、50、100 等）。