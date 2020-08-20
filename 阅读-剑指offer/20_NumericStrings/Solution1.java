/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/12/29
 * @description
 */
public class Solution1 {

    private int index = 0;

    /**
     * 判断是否是数值
     * @param  str 
     * @return 
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length < 1) {
            return false;
        }

        // 判断是否存在整数
        boolean flag = scanInteger(str);

        // 小数部分
        if (index < str.length && str[index] == '.') {
            index++;
            // 小数部分可以有整数或者没有整数
            // 所以使用 ||
            flag = scanUnsignedInteger(str) || flag;
        }

        if (index < str.length && (str[index] == 'e' || str[index] == 'E')) {
            index++;
            // e或E前面必须有数字
            // e或者E后面必须有整数
            // 所以使用 &&
            flag = scanInteger(str) && flag;
        }

        return flag && index == str.length;

    }

    private boolean scanInteger(char[] str) {
        // 去除符号
       if (index < str.length && (str[index] == '+' || str[index] == '-')) {
            index++;
        }

        return scanUnsignedInteger(str);
    }

    private boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9') {
            index++;
        }
        // 判断是否存在整数
        return index > start;
    }
}
