/**
 * @author Anonymous
 * @since 2019/12/12
 */

class Solution {
    /**
     * 翻转单词
     * 
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0 || s.trim().equals("")) {
            return s;
        }

        String[] arr = s.split(" ");
        int p = 0, q = arr.length - 1;
        while (p < q) {
            swap(arr, p++, q--);
        }
        return String.join(" ", arr);
    }

    private void swap(String[] arr, int p, int q) {
        String t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }
}