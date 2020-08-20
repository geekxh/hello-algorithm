/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {
    /**
     * 求数字k在排序数组中出现的次数
     *
     * @param nums 数组
     * @param k    数字k
     * @return k在数组中出现的次数
     */
    public int getNumberOfK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        int first = getFirstK(nums, start, end, k);
        int last = getLastK(nums, start, end, k);
        if (first > -1 && last > -1) {
            return last - first + 1;
        }
        return 0;
    }

    private int getFirstK(int[] nums, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int m = start + ((end - start) >> 1);
        if (nums[m] == k) {
            if (m == 0 || (m > 0 && nums[m - 1] != k)) {
                return m;
            } else {
                end = m - 1;
            }
        } else {
            if (nums[m] > k) {
                end = m - 1;
            } else {
                start = m + 1;
            }
        }
        return getFirstK(nums, start, end, k);
    }

    private int getLastK(int[] nums, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int m = start + ((end - start) >> 1);
        if (nums[m] == k) {
            if (m == nums.length - 1 || (m < nums.length - 1 && nums[m + 1] != k)) {
                return m;
            } else {
                start = m + 1;
            }
        } else {
            if (nums[m] > k) {
                end = m - 1;
            } else {
                start = m + 1;
            }
        }
        return getLastK(nums, start, end, k);

    }
}