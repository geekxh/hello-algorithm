package com.interview.tree;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Time complexity O(log(n) ^ 2)
 *
 * Reference
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountNodesCompleteTree {

    public int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = leftHeight(root.left);
        int rh1 = rightHeight(root.left);
        int rh = rightHeight(root.right);

        if (lh == rh) {
            return (1<<lh + 1) - 1;
        } else {
            if (lh == rh1) {
                return 1 + countNodes(root.right) + (1<<lh) - 1;
            } else {
                return 1 + countNodes(root.left) + (1<<rh) - 1;
            }
        }
    }

    int leftHeight(Node root) {
        int h = 0;
        while (root != null) {
            root = root.left;
            h++;
        }
        return h;
    }
    int rightHeight(Node root) {
        int h = 0;
        while (root != null) {
            root = root.right;
            h++;
        }
        return h;
    }
}
