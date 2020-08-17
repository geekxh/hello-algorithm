/**
 * @author Anonymous
 * @since 2019/10/27
 */

public class Solution {
    /**
     * 不修改数组查找重复的元素，没有则返回-1
     * @param numbers 数组
     * @return 重复的元素
     */
    public int getDuplication(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }

        int start = 1;
        int end = numbers.length - 1;
        while (end >= start) {
            int middle = start + ((end - start) >> 1);

            // 调用 log n 次
            int count = countRange(numbers, start, middle);
            if (start == end) {
                if (count > 1) {
                    return start;
                }
                break;
            } else {
                // 无法找出所有重复的数
                if (count > (middle - start) + 1) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 计算整个数组中有多少个数的取值在[start, end] 之间
     * 时间复杂度 O(n)
     * @param numbers 数组
     * @param start 左边界
     * @param end 右边界
     * @return 数量
     */
    private int countRange(int[] numbers, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for(int e : numbers) {
            if (e >= start && e <= end) {
                ++count;
            }
        }
        return count;
    }
}