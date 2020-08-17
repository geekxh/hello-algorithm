## [ 翻转单词顺序](https://www.acwing.com/problem/content/73/)

### 题目描述
输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。

为简单起见，标点符号和普通字母一样处理。

例如输入字符串 `"I am a student."`，则输出 `"student. a am I"`。

**样例**
```
输入："I am a student."

输出："student. a am I"
```

### 解法
先对字符串按空格切割成数组，再逆序数组后，最后将元素拼接并返回。

```java
/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {
    /**
     * 翻转单词
     * 
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0 || s.trim().equals("")) {
            return s;
        }

        String[] arr = s.split(" ");
        int p = 0, q = arr.length - 1;
        while (p < q) {
            swap(arr, p++, q--);
        }
        return String.join(" ", arr);
    }
    private void swap(String[] arr, int p, int q) {
        String t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }
}
```

### 测试用例
1. 功能测试（句子中有多个单词；句子中只有一个单词）；
2. 特殊输入测试（字符串指针为空指针；字符串的内容为空；字符串中只有空格）。