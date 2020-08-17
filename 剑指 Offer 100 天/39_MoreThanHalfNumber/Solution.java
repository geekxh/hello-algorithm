/**
 * @author Anonymous
 * @since 2019/12/6
 */

public class Solution {
    /**
     * 查找数组中出现次数超过一次的数字
     *
     * @param array 数组
     * @return 返回该数，不存在则返回0
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int res = array[0];
        int times = 1;
        for (int i = 1; i < array.length; ++i) {
            if (times == 0) {
                res = array[i];
                times = 1;
            } else if (array[i] == res) {
                ++times;
            } else {
                --times;
            }
        }

        return isMoreThanHalf(array, res) ? res : 0;
    }

    /**
     * 判断val元素是否真的超过数组元素个数的一半
     *
     * @param array 数组
     * @param val   某元素
     * @return boolean
     */
    private boolean isMoreThanHalf(int[] array, int val) {
        int cnt = 0;
        for (int e : array) {
            if (e == val) {
                ++cnt;
            }
        }

        return cnt * 2 > array.length;
    }
}