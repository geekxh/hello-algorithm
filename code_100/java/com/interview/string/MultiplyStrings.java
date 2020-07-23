package com.interview.string;

/**
 * https://leetcode.com/discuss/questions/oj/multiply-strings
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        String output = multiply(num1, num2, 0, num1.length() - 1, 0, num2.length() - 1);
        return output;
    }

    private String multiply(String num1, String num2, int start1, int end1, int start2, int end2) {
        if (end1 - start1 == 0 || end2 - start2 == 0) {
            return simpleMultiply(num1.substring(start1, end1 + 1), num2.substring(start2, end2 + 1));
        }

        int mid1 = (start1 + end1)/2;
        int mid2 = (start2 + end2)/2;

        int count1 = end1 - mid1;
        int count2 = end2 - mid2;

        String v1 = multiply(num1, num2, start1, mid1, start2, mid2);
        String v2 = multiply(num1, num2, start1, mid1, mid2 + 1, end2);
        String v3 = multiply(num1, num2, mid1 + 1, end1, start2, mid2);
        String v4 = multiply(num1, num2, mid1 + 1, end1, mid2 + 1, end2);

        v1 = append0s(v1, count1 + count2);
        v2 = append0s(v2, count1);
        v3 = append0s(v3, count2);

        v1 = add(v1.toCharArray(), v2.toCharArray());
        v3 = add(v3.toCharArray(), v4.toCharArray());
        return add(v1.toCharArray(), v3.toCharArray());
    }

    private String simpleMultiply(String num1, String num2) {
        String smaller;
        String larger;
        if (num1.length() == 1) {
            smaller = num1;
            larger = num2;
        } else {
            smaller = num2;
            larger = num1;
        }
        int r2 = smaller.charAt(0) - '0';
        if (r2 == 0) {
            return "0";
        }
        int carry = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = larger.length() - 1; i >= 0; i--) {
            int r1 = larger.charAt(i) - '0';
            int r = r1 * r2 + carry;
            stringBuffer.append(r%10);
            carry = r / 10;
        }
        if (carry != 0) {
            stringBuffer.append(carry);
        }
        return stringBuffer.reverse().toString();
    }

    private String append0s(String v1, int count ) {
        StringBuffer buff = new StringBuffer(v1);
        for (int i = 0; i < count; i++) {
            buff.append("0");
        }
        return buff.toString();
    }

    public String add(char[] num1,char[] num2){
        int index1 = num1.length -1;
        int index2 = num2.length -1;
        int carry = 0;
        StringBuffer buffer = new StringBuffer();
        while(index1 >= 0 && index2 >= 0){
            int r1 = num1[index1] - '0';
            int r2 = num2[index2] - '0';
            int r = r1 + r2 + carry;
            buffer.append(r%10);
            carry = r/10;
            index1--;
            index2--;
        }
        while(index1 >= 0){
            int r1 = num1[index1] - '0';
            int r = r1 + carry;
            buffer.append(r%10);
            carry = r/10;
            index1--;
        }
        while(index2 >= 0){
            int r2 = num2[index2] - '0';
            int r = r2 + carry;
            buffer.append(r%10);
            carry = r/10;
            index2--;
        }
        if (carry != 0) {
            buffer.append(carry);
        }
        return buffer.reverse().toString();
    }

    public static void main(String args[]) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.print(ms.multiply("6752716719037375654442652725945722915786612669126862029212","2840271321219335147"));
    }
}
