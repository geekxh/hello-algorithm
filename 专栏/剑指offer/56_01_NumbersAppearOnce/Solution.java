/**
 * @author Anonymous
 * @since 2019/12/10
 */

class Solution {
    /**
     * 求数组中只出现一次的两个数字
     * 
     * @param nums 数字
     * @return 两个数字组成的数组
     */
    public int[] findNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int xorRes = 0;
        for (int e : nums) {
            xorRes ^= e;
        }
        int[] res = new int[2];
        int index = indexOf1(xorRes);
        for (int e : nums) {
            if (isBit1(e, index)) {
                res[0] ^= e;
            } else {
                res[1] ^= e;
            }
        }
        return res;

    }

    private int indexOf1(int val) {
        int index = 0;
        while ((val & 1) == 0) {
            val = val >> 1;
            ++index;
        }
        return index;
    }

    private boolean isBit1(int val, int index) {
        val = val >> index;
        return (val & 1) == 1;
    }
}