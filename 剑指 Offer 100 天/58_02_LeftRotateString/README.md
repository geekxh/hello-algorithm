## [左旋转字符串](https://www.acwing.com/problem/content/74/)

### 题目描述
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。

请定义一个函数实现字符串左旋转操作的功能。

比如输入字符串 `"abcdefg"` 和数字 2，该函数将返回左旋转 2 位得到的结果 `"cdefgab"`。

**注意：**

- 数据保证 n 小于等于输入字符串的长度。

**样例**
```
输入："abcdefg" , n=2

输出："cdefgab"
```

### 解法
先翻转前 n 个字符，再翻转后面的字符，最后整体翻转。

```java
/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {

    /**
     * 左旋转字符串
     * 
     * @param str 字符串
     * @param n 左旋的位数
     * @return 旋转后的字符串
     */
    public String leftRotateString(String str, int n) {
        if (str == null || n < 1 || n > str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        reverse(chars, 0, n - 1);
        reverse(chars, n, len - 1);
        reverse(chars, 0, len - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int p, int q) {
        while (p < q) {
            swap(chars, p++, q--);
        }
    }

    private void swap(char[] chars, int p, int q) {
        char t = chars[p];
        chars[p] = chars[q];
        chars[q] = t;
    }
}
```

### 测试用例
1. 功能测试（把长度为 n 的字符串左旋转 0/1/2/n-1/n/n+1 个字符）；
2. 特殊输入测试（字符串指针为空指针）。