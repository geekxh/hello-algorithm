/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * @author Anonymous
 * @since 2019/10/28
 */

public class Solution {
    /**
     * 重建二叉树
     * 
     * @param pre 先序序列
     * @param in  中序序列
     * @return 二叉树根结点
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int n = pre.length;
        return constructBinaryTree(pre, 0, n - 1, in, 0, n - 1);
    }

    private TreeNode constructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        TreeNode node = new TreeNode(pre[startPre]);
        if (startPre == endPre) {
            if (startIn == endIn) {
                return node;
            }
            throw new IllegalArgumentException("Invalid input!");
        }

        int inOrder = startIn;
        while (in[inOrder] != pre[startPre]) {
            ++inOrder;
            if (inOrder > endIn) {
                new IllegalArgumentException("Invalid input!");
            }
        }
        int len = inOrder - startIn;
        if (len > 0) {
            // 递归构建左子树
            node.left = constructBinaryTree(pre, startPre + 1, startPre + len, in, startIn, inOrder - 1);
        }

        if (inOrder < endIn) {
            // 递归构建右子树
            node.right = constructBinaryTree(pre, startPre + len + 1, endPre, in, inOrder + 1, endIn);
        }
        return node;

    }
}