/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/14
 */
public class Solution {

    public ArrayList<String> Permutation(String str) {

        ArrayList<String> reList = new ArrayList<>();

        if (str == null || str.length() == 0) {
            return reList;
        }

        char[] chars = str.toCharArray();

        // 递归输出字符串排列
        permutationHelper(chars, 0, reList);
        Collections.sort(reList);
        return reList;
    }

    private void permutationHelper(char[] chars, int index, ArrayList<String> list) {

        if (index == chars.length - 1) {
            list.add(new String(chars));
            return;
        }

        Set<Character> set = new HashSet<>();
        // 确定交换的字符，包括自己[index,length-1]
        for (int i = index; i < chars.length; i++) {

            // 排除出现重复字符
            // hash表，查询花费O(1)
            if (!set.contains(chars[i])) {
                set.add(chars[i]);
                // 固定字符index
                swap(chars, i, index);
                // 递归固定剩余字符[index+1,length-1]
                permutationHelper(chars, index + 1, list);
                // 恢复原数组
                swap(chars, index, i);
            }
        }
    }

    private void swap(char[] chars, int x, int y) {

        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}