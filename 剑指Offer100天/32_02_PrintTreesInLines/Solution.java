import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Anonymous
 * @since 2019/11/23
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
     * 把二叉树打印成多行
     * 
     * @param pRoot 二叉树根节点
     * @return 结果list
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int cnt = 1;
        while (cnt > 0) {
            int num = cnt;
            cnt = 0;
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < num; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    ++cnt;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    ++cnt;
                }
                res.add(node.val);
            }
            list.add(res);
        }
        return list;
    }

}