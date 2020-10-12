/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/

/**
 * @author Anonymous
 * @since 2019/10/28
 */

public class Solution {
    /**
     * 获取中序遍历结点的下一个结点
     * 
     * @param pNode 某个结点
     * @return pNode的下一个结点
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }

        if (pNode.right != null) {
            TreeLinkNode t = pNode.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        }

        // 须保证 pNode.next 不为空，否则会出现 NPE
        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }

        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }

        return null;

    }
}