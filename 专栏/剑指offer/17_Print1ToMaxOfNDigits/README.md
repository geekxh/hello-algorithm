## 打印从 1 到最大的 n 位数

### 题目描述
输入数字 `n`，按顺序打印出从 `1` 最大的 `n` 位十进制数。比如输入 `3`，则打印出 `1、2、3` 一直到最大的 3 位数即 999。

### 解法
此题需要注意 n 位数构成的数字可能超出最大的 int 或者 long long 能表示的范围。因此，采用字符数组来存储数字。

关键是：
- 对字符数组表示的数进行递增操作
- 输出数字（0开头的需要把0去除）

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {

    /**
     * 打印从1到最大的n位数
     * @param n n位
     */
    public void print1ToMaxOfNDigits(int n) {
        if (n < 1) {
            return;
        }

        char[] chars = new char[n];
        for (int i = 0; i < n; ++i) {
            chars[i] = '0';
        }

        while (!increment(chars)) {
            printNumber(chars);
        }
    }

    /**
     * 打印数字（去除前面的0）
     * @param chars 数字数组
     */
    private void printNumber(char[] chars) {
        int index = 0;
        int n = chars.length;
        for (char ch : chars) {
            if (ch != '0') {
                break;
            }
            ++index;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < n; ++i) {
            sb.append(chars[i]);
        }
        System.out.println(sb.toString());
    }

    /**
     * 数字加1
     * @param chars 数字数组
     * @return 是否溢出
     */
    private boolean increment(char[] chars) {
        boolean flag = false;
        int n = chars.length;
        int carry = 1;
        for (int i = n - 1; i >= 0; --i) {

            int num = chars[i] - '0' + carry;
            if (num > 9) {
                if (i == 0) {
                    flag = true;
                    break;
                }
                chars[i] = '0';
            } else {
                ++chars[i];
                break;
            }
        }
        return flag;
    }
}
```

### 测试用例
1. 功能测试（输入 1、2、3......）；
2. 特殊输入测试（输入 -1、0）。