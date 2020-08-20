/**
 * @author Anonymous
 * @since 2019/11/23
 */

public class Solution {
    /**
     * 矩形覆盖
     * 
     * @param target 2*target大小的矩形
     * @return 多少种覆盖方法
     */
    public int RectCover(int target) {
        if (target < 3) {
            return target;
        }
        int[] res = new int[target + 1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= target; ++i) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[target];
    }
}