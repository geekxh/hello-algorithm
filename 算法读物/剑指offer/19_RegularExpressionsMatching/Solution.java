/**
 * @author Anonymous
 * @since 2019/11/21
 */

public class Solution {
    /**
     * 判断字符串是否与模式串匹配
     * 
     * @param str     字符串
     * @param pattern 模式串
     * @return 是否匹配
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str, 0, str.length, pattern, 0, pattern.length);
    }

    private boolean match(char[] str, int i, int len1, char[] pattern, int j, int len2) {
        if (i == len1 && j == len2) {
            return true;
        }

        // "",".*"
        if (i != len1 && j == len2) {
            return false;
        }

        if (j + 1 < len2 && pattern[j + 1] == '*') {
            if (i < len1 && (str[i] == pattern[j] || pattern[j] == '.')) {
                return match(str, i, len1, pattern, j + 2, len2) || match(str, i + 1, len1, pattern, j, len2)
                        || match(str, i + 1, len1, pattern, j + 2, len2);
            }

            // "",".*"
            return match(str, i, len1, pattern, j + 2, len2);

        }
        if (i < len1 && (str[i] == pattern[j] || pattern[j] == '.')) {
            return match(str, i + 1, len1, pattern, j + 1, len2);
        }
        return false;

    }
}