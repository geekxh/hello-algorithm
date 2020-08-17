import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/11/21
 */

public class Solution {
    /**
     * 调整数组元素顺序，使得奇数元素位于偶数元素前面，且保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * 
     * @param array 数组
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int numsOfOdd = 0;
        for (int val : array) {
            if (val % 2 == 1) {
                ++numsOfOdd;
            }
        }
        int[] bak = Arrays.copyOf(array, array.length);
        int i = 0, j = numsOfOdd;
        for (int val : bak) {
            if (val % 2 == 1) {
                array[i++] = val;
            } else {
                array[j++] = val;
            }
        }
    }

}