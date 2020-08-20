import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {

    /**
     * 判断是否是连续的数字
     *
     * @param numbers 数组
     * @return 是否是顺子
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        int zeroCount = 0;
        Arrays.sort(numbers);
        for (int e : numbers) {
            if (e > 0) {
                break;
            }
            ++zeroCount;
        }

        int p = zeroCount, q = p + 1, n = numbers.length;
        int gap = 0;
        while (q < n) {
            if (numbers[p] == numbers[q]) {
                return false;
            }
            gap += (numbers[q] - numbers[p] - 1);
            p = q;
            ++q;
        }
        return gap <= zeroCount;

    }
}