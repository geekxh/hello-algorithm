/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/28
 */
class Solution {
    
    private int count = 0;

    public TreeNode KthNode(TreeNode pRoot, int k) {

        if (pRoot == null || k == 0) {
            return null;
        }

        // 左递归
        TreeNode retNode = KthNode(pRoot.left, k);

        if (retNode != null) {
            return retNode;
        }

        // 符合条件则返回
        count++;
        if (count == k) {
            return pRoot;
        }

        // 右递归
        retNode = KthNode(pRoot.right, k);
        if (retNode != null) {
            return retNode;
        }

        return null;
    }
}