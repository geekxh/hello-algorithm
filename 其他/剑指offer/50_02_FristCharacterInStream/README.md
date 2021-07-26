## 字符流中第一个不重复的字符

### 题目描述

请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。如果当前字符流没有存在出现一次的字符，返回#字符。


### 解法1
与上一道题的思路是一致的。

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/25
 * @description
 */
public class Solution {
    
    private StringBuilder res = new StringBuilder();
    private Map<Character, Integer> characterMap = new HashMap<>();

    // Insert one char from stringstream
    public void Insert(char ch) {
        res.append(ch);
        characterMap.put(ch, characterMap.getOrDefault(ch, 0) + 1);
    }

    // return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {

        for (char c : res.toString().toCharArray()) {
            if (characterMap.get(c) == 1) {
                return c;
            }
        }

        return '#';
    }
}
```
### 测试用例

1. 功能测试（读入一个字符；读入多个字符；读入的所有字符都是唯一的；读入的所有字符都是重复出现的）。
2. 特殊输入测试（读入0个字符）。