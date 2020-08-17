## 数值的整数次方

### 题目描述
给定一个 `double` 类型的浮点数 `base` 和 `int` 类型的整数 `exponent`。求 `base`的 `exponent` 次方。

### 解法
注意判断值数是否小于 0。另外 0 的 0 次方没有意义，也需要考虑一下，看具体题目要求。

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 计算数值的整数次方
     * @param base 底数
     * @param exponent 指数
     * @return 数值的整数次方
     */
    public double Power(double base, int exponent) {
        double result = 1.0;
        int n = Math.abs(exponent);
        for (int i = 0; i < n; ++i) {
            result *= base;
        }

        return exponent < 0 ? 1.0 / result : result;
    }
}

```

### 测试用例
1. 把底数和指数分别设为正数、负数和零。