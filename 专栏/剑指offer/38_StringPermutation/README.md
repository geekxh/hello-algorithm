## 字符串的排列

### 题目描述
输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。(输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母)。ps：牛客上的测试用例对返回的list要排序。

### 解法
对整个字符串的排列可以看成两部分。第一步求所有可能出现在第一个位置的字符，即把第一个字符与后面所有非重复的字符进行交换。第二步固定第一个字符，求后面所有字符的排列；第二步中后面的所有字符又可以看成一个完整的字符，继续执行这两个步骤。

注意存在重复值得情况，比如输入字符串bab，将首字符b作为固定字符串，对于将第2个下标的b换到首位仍然是bab，所有这种情况无需输出。

**这道题的时间复杂度应该为O(n!)**

```java
/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/14
 */
public class Solution {

    public ArrayList<String> Permutation(String str) {

        ArrayList<String> reList = new ArrayList<>();

        if (str == null || str.length() == 0) {
            return reList;
        }

        char[] chars = str.toCharArray();

        // 递归输出字符串排列
        permutationHelper(chars, 0, reList);
        Collections.sort(reList);
        return reList;
    }

    private void permutationHelper(char[] chars, int index, ArrayList<String> list) {

        if (index == chars.length - 1) {
            list.add(new String(chars));
            return;
        }

        Set<Character> set = new HashSet<>();
        // 确定交换的字符，包括自己[index,length-1]
        for (int i = index; i < chars.length; i++) {

            // 排除出现重复字符
            // hash表，查询花费O(1)
            if (!set.contains(chars[i])) {
                set.add(chars[i]);
                // 固定字符index
                swap(chars, i, index);
                // 递归固定剩余字符[index+1,length-1]
                permutationHelper(chars, index + 1, list);
                // 恢复原数组
                swap(chars, index, i);
            }
        }
    }

    private void swap(char[] chars, int x, int y) {

        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}
```

### 测试用例
1. 功能测试（输入的字符串有一个或多个字符）；
2. 特殊输入测试（输入的字符串为nullptr指针或者内容为空）。