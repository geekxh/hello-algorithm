import java.util.Arrays;

/**
 * @author Anonymous
 * @since 2019/12/8
 */

class Solution {

    /**
     * 打印数组元素组成的最小的数字
     *
     * @param nums 数组
     * @return 最小的数字
     */
    public String printMinNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int n = nums.length;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strNums, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String str : strNums) {
            sb.append(str);
        }
        return sb.toString();
    }
}