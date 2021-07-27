/**
 * @author Anonymous
 * @since 2019/11/23
 */

public class Solution {
    /**
     * 判断数组是否是某个二叉搜索树的后序遍历序列
     *
     * @param sequence 数组
     * @return 是否属于某二叉搜索树的后序遍历序列
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length < 1) {
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int val = sequence[end];
        int i = start;
        for (; i <= end; ++i) {
            if (sequence[i] >= val) {
                break;
            }
        }

        for (int j = i; j < end; ++j) {
            if (sequence[j] < val) {
                return false;
            }
        }

        return verify(sequence, start, i - 1) && verify(sequence, i, end - 1);

    }
}