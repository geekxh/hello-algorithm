## 整数中1出现的次数

### 题目描述
求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

### 解法
- 编程之美上给出的规律：

  1. 如果第i位（自右至左，从1开始标号）上的数字为0，则第i位可能出现1的次数由更高位决定（若没有高位，视高位为0），等于更高位数字X当前位数的权重10^(i-1)。
  2. 如果第i位上的数字为1，则第i位上可能出现1的次数不仅受更高位影响，还受低位影响（若没有低位，视低位为0），等于更高位数字X当前位数的权重10^(i-1)+（低位数字+1）。
  3. 如果第i位上的数字大于1，则第i位上可能出现1的次数仅由更高位决定（若没有高位，视高位为0），等于（更高位数字+1）X当前位数的权重10^(i-1)。

  总结一下以上的算法，可以看到，当计算右数第 i 位包含的 X 的个数时：

  1. 取第 i 位左边（高位）的数字，乘以 10i−1，得到**基础值** a。
  2. 取第 i 位数字，计算**修正值**：
     1. 如果大于 X，则结果为 a+10i−1。
     2. 如果小于 X，则结果为 a。
     3. 如果等 X，则取第 i 位右边（低位）数字，设为 b，最后结果为 a+b+1。

  相应的代码非常简单，效率也非常高，时间复杂度只有 O(logn)。

```java

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/17
 */
public class Solution {
    
    public int NumberOf1Between1AndN_Solution(int n) {

        if (n < 1) {
            return 0;
        }

        int high, low, curr, tmp, i = 1;
        high = n;
        int number = 0;
        while (high != 0) {
            // 获取第i位的高位
            high = n / (int) Math.pow(10, i);
            tmp = n % (int) Math.pow(10, i);
            // 获取第i位
            curr = tmp / (int) Math.pow(10, i - 1);
            // 获取第i位的低位
            low = tmp % (int) Math.pow(10, i - 1);
            if (curr == 1) {
                number += high * (int) Math.pow(10, i - 1) + low + 1;
            } else if (curr < 1) {
                number += high * (int) Math.pow(10, i - 1);
            } else {
                number += (high + 1) * (int) Math.pow(10, i - 1);
            }
            i++;
        }
        return number;
    }
}
```

### 测试用例
1. 功能测试（输入1~n的数字）；
2. 特殊输入测试（输入的数字小于0）。