/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {
    /**
     * 最长不含重复字符的子字符串
     *
     * @param s 字符串
     * @return 最长不重复字符子串
     */
    public int longestSubstringWithoutDuplication(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] t = new int[26];
        for (int i = 0; i < 26; ++i) {
            t[i] = -1;
        }
        t[chars[0] - 'a'] = 0;
        int n = chars.length;
        int[] res = new int[n];
        res[0] = 1;
        int max = res[0];
        for (int i = 1; i < n; ++i) {
            int index = t[chars[i] - 'a'];
            int d = i - index;
            res[i] = (index == -1 || d > res[i - 1]) ? res[i - 1] + 1 : d;

            t[chars[i] - 'a'] = i;
            max = Math.max(max, res[i]);
        }
        return max;
    }
}