package com.interview.array;

import java.math.BigInteger;

/**
 * Date 04/24/2016
 * @author Tushar Roy
 *
 * Additive number is a string whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * https://leetcode.com/problems/additive-number/
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }
        for (int i = 0; i <= num.length()/2; i++) {
            if (num.charAt(0) == '0' && i > 0) {
                break;
            }
            BigInteger x1 = new BigInteger(num.substring(0, i + 1));
            //make sure remaining size is at least size of first and second integer.
            for (int j = i + 1; Math.max(i, j - (i + 1)) + 1 <= num.length() - j - 1 ; j++) {
                if (num.charAt(i + 1) == '0' && j > i + 1) {
                    break;
                }
                BigInteger x2 = new BigInteger(num.substring(i + 1, j + 1));
                if (isValid(num, j + 1, x1, x2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(String num, int start, BigInteger x1, BigInteger x2) {
        if (start == num.length()) {
            return true;
        }
        BigInteger x3 = x1.add(x2);
        //if num starts with x3 from offset start means x3 is found. So look for next number.
        return num.startsWith(x3.toString(), start) && isValid(num, start + x3.toString().length(), x2, x3);
    }
}
