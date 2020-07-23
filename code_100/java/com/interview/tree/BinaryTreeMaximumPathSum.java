package com.interview.tree;

/**
 * Date 03/22/2016
 * @author Tushar Roy
 *
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes
 * from some starting node to any node in the tree along the parent-child connections.
 * 
 * Time complexity O(n)
 * Space complexity depends on depth of tree.
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {
    int max = 0;

    public int maxPathSum(Node root) {
        max = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return max;
    }

    private int maxPathSumUtil(Node root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumUtil(root.left);
        int right = maxPathSumUtil(root.right);
        if (left < 0) {
            left = 0;
        }
        if (right < 0) {
            right = 0;
        }
        max = Math.max(max, root.data + left + right);
        return root.data + Math.max(left, right);
    }
}
