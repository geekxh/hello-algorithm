## 斐波那契数列

### 题目描述
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第 `n` 项（从 `0` 开始，第 `0` 项为 `0`）。`n<=39`


### 解法
#### 解法一
采用递归方式，简洁明了，但效率很低，存在大量的重复计算。
```
                  f(10)
               /        \
            f(9)         f(8)
          /     \       /    \
       f(8)     f(7)  f(7)   f(6)
      /   \     /   \ 
   f(7)  f(6)  f(6) f(5)
```

```java

/**
 * @author Anonymous
 * @since 2019/10/29
 */

public class Solution {
    /**
     * 求斐波那契数列的第n项，n从0开始
     * @param n 第n项
     * @return 第n项的值
     */
    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        // 递归调用
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
```

#### 解法二
从下往上计算，递推，时间复杂度 `O(n)`。

```java

/**
 * @author Anonymous
 * @since 2019/10/29
 */

public class Solution {
    /**
     * 求斐波那契数列的第n项，n从0开始
     * @param n 第n项
     * @return 第n项的值
     */
    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= n; ++i) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];

    }
}
```


### 测试用例
1. 功能测试（如输入 3、5、10 等）；
2. 边界值测试（如输入 0、1、2）；
3. 性能测试（输入较大的数字，如 40、50、100 等）。