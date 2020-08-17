/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {
    /**
     * 计算数值的整数次方
     * 
     * @param base     底数
     * @param exponent 指数
     * @return 数值的整数次方
     */
    public double Power(double base, int exponent) {
        double result = 1.0;
        int n = Math.abs(exponent);
        for (int i = 0; i < n; ++i) {
            result *= base;
        }

        return exponent < 0 ? 1.0 / result : result;
    }
}
