import java.util.ArrayList;
import java.util.Stack;

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
     * 按之字形打印二叉树
     * 
     * @param pRoot 二叉树的根节点
     * @return 结果list
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        int i = 1;
        Stack<TreeNode> stack = stack1;
        while (!stack.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if (i % 2 == 1) {
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        stack1.push(node.right);
                    }
                    if (node.left != null) {
                        stack1.push(node.left);
                    }
                }
            }
            res.add(list);
            ++i;
            stack = stack1.isEmpty() ? stack2 : stack1;
        }

        return res;
    }

}