import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/11/24
 */

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
public class Solution {
    /**
     * 将二叉搜索树转换为双向链表
     * 
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = pRootOfTree;
        TreeNode res = null;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre == null) {
                    pre = cur;
                    res = pre;
                } else {
                    pre.right = cur;
                    cur.left = pre;
                    pre = cur;
                }
                cur = cur.right;

            }
        }
        return res;
    }
}