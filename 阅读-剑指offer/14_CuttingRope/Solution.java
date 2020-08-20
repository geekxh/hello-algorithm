/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {

    /**
     * 剪绳子求最大乘积(动态规划)
     * 
     * @param length 绳子长度
     * @return 乘积最大值
     */
    public int maxProductAfterCutting1(int length) {
        if (length < 2) {
            return 0;
        }
        if (length < 4) {
            return length - 1;
        }

        int[] res = new int[length + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        for (int i = 4; i <= length; ++i) {
            int max = 0;
            for (int j = 1; j <= i / 2; ++j) {
                max = Math.max(max, res[j] * res[i - j]);
            }
            res[i] = max;
        }
        return res[length];
    }

    /**
     * 剪绳子求最大乘积(贪心算法)
     * 
     * @param length 绳子长度
     * @return 乘积最大值
     */
    public int maxProductAfterCutting2(int length) {
        if (length < 2) {
            return 0;
        }
        if (length < 4) {
            return length - 1;
        }

        int timesOf3 = length / 3;
        if (length % 3 == 1) {
            --timesOf3;
        }
        int timesOf2 = (length - timesOf3 * 3) >> 1;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }
}
