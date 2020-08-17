## 第一个只出现一次的字符

### 题目描述
在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.

### 解法1
使用HashMap来统计字符出现的次数，因为字符的多少是固定的（大小写字母一共52个），所以可以认为使用HashMap的空间复杂度为O(1)。该解法时间复杂度为O(n)。

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/24
 * @description
 */
public class Solution {
    
    public int FirstNotRepeatingChar(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }

        Map<Character, Integer> characterMap = new HashMap<>();
        // hashMap is HashTable，search cost O(1)
        for (int i = 0; i < str.length(); i++) {
            characterMap.put(str.charAt(i), characterMap.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (characterMap.get(str.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
```
### 测试用例

1. 功能测试（字符串中仅存在只出现一次的字符；字符串中不存在只出现一次的字符；字符串中所有字符都只出现一次）。
2. 特殊输入测试（字符串为null）。