## 表示数值的字符串

### 题目描述
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

### 解法

#### 解法一

利用正则表达式匹配即可。
```
[]  ： 字符集合
()  ： 分组
?   ： 重复 0 ~ 1
+   ： 重复 1 ~ n
*   ： 重复 0 ~ n
.   ： 任意字符
\\. ： 转义后的 .
\\d ： 数字
```

```java
/**
 * @author Anonymous
 * @since 2019/11/21
 */

public class Solution {
    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        return str != null 
                && str.length != 0 
                && new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
```

#### 解法二【剑指offer解法】

表示数值的字符串遵循模式`A[.[B]][e|EC]`或者`.B[e|EC]`，其中A为数值的整数部分，B紧跟小数点为数值的小数部分，C紧跟着e或者E为数值的指数部分。上述A和C都有可能以 `+` 或者 `-` 开头的0~9的数位串，B也是0~9的数位串，但前面不能有正负号。

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/12/29
 * @description
 */
public class Solution {

    private int index = 0;

    /**
     * 判断是否是数值
     * @param  str 
     * @return 
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length < 1) {
            return false;
        }

        // 判断是否存在整数
        boolean flag = scanInteger(str);

        // 小数部分
        if (index < str.length && str[index] == '.') {
            index++;
            // 小数部分可以有整数或者没有整数
            // 所以使用 ||
            flag = scanUnsignedInteger(str) || flag;
        }

        if (index < str.length && (str[index] == 'e' || str[index] == 'E')) {
            index++;
            // e或E前面必须有数字
            // e或者E后面必须有整数
            // 所以使用 &&
            flag = scanInteger(str) && flag;
        }

        return flag && index == str.length;

    }

    private boolean scanInteger(char[] str) {
        // 去除符号
        while (index < str.length && (str[index] == '+' || str[index] == '-')) {
            index++;
        }

        return scanUnsignedInteger(str);
    }

    private boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9') {
            index++;
        }
        // 判断是否存在整数
        return index > start;
    }
}
```



### 测试用例

1. 功能测试（正数或者负数；包含或者不包含整数部分的数值；包含或者不包含效数部分的值；包含或者不包含指数部分的值；各种不能表达有效数值的字符串）；
2. 特殊输入测试（输入字符串和模式字符串是空指针、空字符串）。