/**
 * @author Anonymous
 * @since 2019/11/21
 */

public class Solution {
    /**
     * 判断是否是数字
     * 
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        return str != null && str.length != 0 && new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}