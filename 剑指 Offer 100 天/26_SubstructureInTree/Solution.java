
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
*/

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/01
 * @description
 */
public class Solution {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {

        if (root1 == null || root2 == null) {
            return false;
        }

        return isSame(root1, root2) || 
                HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSame(TreeNode root1, TreeNode root2) {

        if (root2 == null) {
            return true;
        }

        // 在root2，root1遍历完成后，仍未找到符合的结构，返回false
        if (root1 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
    }

}