/**
 * @author Anonymous
 * @since 2019/10/27
 */

public class Solution {
    /**
     * 将字符串中的所有空格替换为%20
     * @param str 字符串
     * @return 替换后的字符串
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) {
            return str.toString();
        }
        
        int len = str.length();
        for (int i = 0; i < len; ++i) {
            if (str.charAt(i) == ' ') {
                // append 两个空格
                str.append("  ");
            }
        }

        // p 指向原字符串末尾
        int p = len - 1;

        // q 指向现字符串末尾
        int q = str.length() - 1;

        while (p >= 0) {
            char ch = str.charAt(p--);
            if (ch == ' ') {
                str.setCharAt(q--, '0');
                str.setCharAt(q--, '2');
                str.setCharAt(q--, '%');
            } else {
                str.setCharAt(q--, ch);
            }
        }

        return str.toString();

    }
}