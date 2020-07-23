package com.interview.array;

/**
 * Date 12/29/2015
 * @author Tushar Roy
 *
 * Given input array of 0s and 1s and number of flips allowed from 0 to 1, what is maximum consecutive 1s we can have
 * in array
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/find-zeroes-to-be-flipped-so-that-number-of-consecutive-1s-is-maximized/
 */
public class Flip0sMaximum1s {

    public int flip0sToMaximizeConsecutive1s(int input[], int flipsAllowed) {

        int windowStart = 0;
        int countZero = 0;
        int result = 0;
        for (int i = 0 ; i < input.length; i++) {
            if (input[i] == 1) {
                result = Math.max(result, i - windowStart + 1);
            } else {
                if (countZero < flipsAllowed) {
                    countZero++;
                    result = Math.max(result, i - windowStart + 1);
                } else {
                    while(true) {
                        if (input[windowStart] == 0) {
                            windowStart++;
                            break;
                        }
                        windowStart++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int input[] = {0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
        Flip0sMaximum1s fm = new Flip0sMaximum1s();
        System.out.print(fm.flip0sToMaximizeConsecutive1s(input, 1));
    }
}
