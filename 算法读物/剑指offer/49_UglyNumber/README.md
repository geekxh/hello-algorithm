## 丑数

### 题目描述
把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

### 解法1
由题目可以得知，丑数必定可以整除2、3或者5（除了丑数1之外），也就是说，如果一个数能够被2整除，就连续除以2；能够被3整除，就连续除以3；能够被5整除，就连续除以5；如果最后得到1，那么这个数便是丑数。因此我们可以使用暴力的方式遍历到第N个丑数。

该解法的time complexity为O(count)，比如第1500个丑数为859963392，那么就需要枚举1到859963392

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/23
 * @description
 */
public class Solution {
    
    private boolean isUgly(int number){
        if(number % 2 == 0)
            number /= 2;
        if(number % 3 == 0)
            number /= 3;
        if(number % 5 == 0)
            number /= 5;
        return number == 1;
    }
    
    public int GetUglyNumber_Solution(int index){
        if(index <= 0)
            return 0;
        
        int number = 0;
        int count = 0;
        while(count < index){
            number++;
            if(isUgly(number)){
                count++;
            }
        }
        
        return number;
    }
}
```

### 解法2

把15以内的丑数列出来：`1、2、3、4、5、6、8、9、10、12、15` ，你会发现新丑数必定是旧丑数乘以因子2、3或者5得来的。所以可以使用一个list来存储已经出现的丑数以此来计算出新的丑数，从而避免对非丑数的计算。

通过维护3个下标i2，i3，i5和它们对应的值m2，m3，m5，每次向list中添加的为m2，m3，m5中的最小值，以此来维护list的有序性。

该解法的time complexity为O(n)，space complexity为O(n)，属于典型的用空间换时间的解决方法。

```java
/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/23
 * @description
 */
public class Solution {

    public int GetUglyNumber_Solution(int index) {

        if (index <= 0)
            return 0;

        List<Integer> reList = new ArrayList<>();
        // 第一个丑数为1
        reList.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (reList.size() < index) {

            int m2 = reList.get(i2) * 2;
            int m3 = reList.get(i3) * 3;
            int m5 = reList.get(i5) * 5;

            // 求出m2、m3、m5中的最小值，该值为加入list的丑数
            int min = Math.min(m2, Math.min(m3, m5));

            if (m2 == min) {
                i2++;
            }
            if (m3 == min) {
                i3++;
            }
            if (m5 == min) {
                i5++;
            }

            reList.add(min);
        }

        // O(1)
        return reList.get(reList.size() - 1);
    }
}
```

### 测试用例

1. 功能测试（输入2、3、4、5、6等）。
2. 特殊输入测试（边界值1；无效输入0）。
3. 性能测试（输入较大的数字，比如1500）。