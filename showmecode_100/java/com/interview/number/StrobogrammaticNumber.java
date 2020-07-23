package com.interview.number;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * https://leetcode.com/problems/strobogrammatic-number/
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {

        for (int i = 0; i <= num.length()/2; i++) {
            char ch1 = num.charAt(i);
            char ch2 = num.charAt(num.length() - i - 1);

            if (ch1 != ch2) {
                if ((ch1 != '9' || ch2 != '6') && (ch1 != '6' || ch2 != '9')) {
                    return false;
                }
            } else {
                if (ch1 != '0' && ch1 != '1' && ch1 != '8') {
                    return false;
                }
            }
        }
        return true;
    }
}
