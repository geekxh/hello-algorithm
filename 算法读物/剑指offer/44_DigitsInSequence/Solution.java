/**
 * @author Anonymous
 * @since 2019/12/7
 */

public class Solution {
    /**
     * 求数字序列中某一位的数字
     *
     * @param n 第n位
     * @return 第n位的数字
     */
    public int digitAtIndex(int n) {
        if (n < 0) {
            return -1;
        }
        int digits = 1;
        while (true) {
            long numbers = countOfIntegers(digits);
            if (n < digits * numbers) {
                break;
            }
            n -= numbers * digits;
            ++digits;
        }
        return digitAtIndex(digits, n);

    }

    private long countOfIntegers(int digits) {
        return digits == 1 ? 10 : (int) (9 * Math.pow(10, digits - 1));
    }

    private int digitAtIndex(int digits, int n) {
        int beginNumber = getBeginNumber(digits);
        int val = beginNumber + n / digits;
        int indexFromRight = digits - n % digits;
        for (int i = 1; i < indexFromRight; ++i) {
            val /= 10;
        }
        return val % 10;
    }

    private int getBeginNumber(int digits) {
        return digits == 1 ? 0 : (int) Math.pow(10, digits - 1);
    }
}