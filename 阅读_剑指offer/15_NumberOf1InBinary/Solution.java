/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 计算整数的二进制表示里1的个数
     * 
     * @param n 整数
     * @return 1的个数
     */
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            n = (n - 1) & n;
            ++cnt;
        }
        return cnt;
    }
}