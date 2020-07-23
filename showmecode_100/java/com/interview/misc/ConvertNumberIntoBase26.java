package com.interview.misc;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * https://leetcode.com/problems/excel-sheet-column-title/
 */
public class ConvertNumberIntoBase26 {

    public String numberToBase26(int n) {
        StringBuffer buff = new StringBuffer();
        while (n > 0) {
            n--;
            buff.append((char)(n % 26 + 'A'));
            n = n / 26;
        }
        return buff.reverse().toString();
    }
    
    public int base26ToNumber(String str) {
        int result = 0;
        int pow = 1;
        for(int i = str.length() - 1; i>=0; i--) {
            char s = str.charAt(i);
            s = (char)(s - 'A'+1);
            result += s*pow;
            pow *= 26;
        }
        return result;
    }

    public static void main(String args[]) {
        ConvertNumberIntoBase26 cni = new ConvertNumberIntoBase26();
        System.out.println(cni.numberToBase26(704));
        System.out.println(cni.base26ToNumber("AAB"));

        System.out.println(cni.numberToBase26(52));
        System.out.println(cni.base26ToNumber("AZ"));

        System.out.println(cni.numberToBase26(13));
        System.out.println(cni.base26ToNumber("M"));

        System.out.println(cni.numberToBase26(126));
        System.out.println(cni.base26ToNumber("DV"));
    }
}
