import java.util.ArrayList;

/**
 * @author Anonymous
 * @since 2019/11/23
 */

/**
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

    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    /**
     * 找出二叉树中和为某一值的路径（必须从根节点到叶节点）
     * 
     * @param root   二叉树的根结点
     * @param target 目标值
     * @return 结果list
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        findPath(root, target, new ArrayList<>());
        return res;
    }

    private void findPath(TreeNode root, int target, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(list));
        } else {
            findPath(root.left, target, list);
            findPath(root.right, target, list);
        }
        list.remove(list.size() - 1);
    }
}