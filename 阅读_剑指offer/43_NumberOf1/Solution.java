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
