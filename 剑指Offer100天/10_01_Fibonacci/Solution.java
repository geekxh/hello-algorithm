
/**
 * @author Anonymous
 * @since 2019/10/29
 */

public class Solution {
    /**
     * 求斐波那契数列的第n项，n从0开始
     * @param n 第n项
     * @return 第n项的值
     */
    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= n; ++i) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];

    }
}