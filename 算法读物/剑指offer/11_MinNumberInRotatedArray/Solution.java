
/**
 * @author Anonymous
 * @since 2019/10/30
 */

public class Solution {
    /**
     * 获取旋转数组的最小元素
     * @param array 旋转数组
     * @return 数组中的最小值
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int p = 0;
        // mid初始为p，为了兼容当数组是递增数组(即不满足 array[p] >= array[q])时，返回 array[p]
        int mid = p;
        int q = array.length - 1;
        while (array[p] >= array[q]) {
            if (q - p == 1) {
                // 当p,q相邻时(距离为1)，那么q指向的元素就是最小值
                mid = q;
                break;
            }
            mid = p + ((q - p) >> 1);

            // 当p,q,mid指向的值相等时，此时只能通过遍历查找最小值
            if (array[p] == array[q] && array[mid] == array[p]) {
                mid = getMinIndex(array, p, q);
                break;
            }

            if (array[mid] >= array[p]) {
                p = mid;
            } else if (array[mid] <= array[q]) {
                q = mid;
            }
        }

        return array[mid];


    }

    private int getMinIndex(int[] array, int p, int q) {
        int minIndex = p;
        for (int i = p + 1; i <= q; ++i) {
            minIndex = array[i] < array[minIndex] ? i : minIndex;
        }
        return minIndex;
    }
}