
/**
 * @author Anonymous
 * @since 2019/11/22
 */

/*
 * public class TreeNode { int val = 0; TreeNode left = null; TreeNode right =
 * null;
 * 
 * public TreeNode(int val) { this.val = val;
 * 
 * }
 * 
 * }
 */
public class Solution {
    /**
     * 将二叉树转换为它的镜像
     * 
     * @param root 二叉树的根结点
     */
    public void Mirror(TreeNode root) {
        if (root == null || !hasChild(root)) {
            return;
        }

        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        Mirror(root.left);
        Mirror(root.right);
    }

    private boolean hasChild(TreeNode root) {
        return root.left != null || root.right != null;
    }
}