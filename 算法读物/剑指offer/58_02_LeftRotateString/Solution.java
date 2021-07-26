/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {

    /**
     * 左旋转字符串
     * 
     * @param str 字符串
     * @param n   左旋的位数
     * @return 旋转后的字符串
     */
    public String leftRotateString(String str, int n) {
        if (str == null || n < 1 || n > str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        reverse(chars, 0, n - 1);
        reverse(chars, n, len - 1);
        reverse(chars, 0, len - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int p, int q) {
        while (p < q) {
            swap(chars, p++, q--);
        }
    }

    private void swap(char[] chars, int p, int q) {
        char t = chars[p];
        chars[p] = chars[q];
        chars[q] = t;
    }
}