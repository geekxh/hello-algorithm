package com.interview.tree;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestValueBinaryTree {
    public int closestValue(Node root, double target) {
        int r = target > 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        return closestValueUtil(root, target, r);
    }

    private int closestValueUtil(Node root, double target, int result) {
        if (root == null) {
            return (int)result;
        }
        if (target == root.data) {
            return root.data;
        }
        if (Math.abs(root.data - target) < Math.abs(result - target)) {
            result = root.data;
        }
        if (target < root.data) {
            return closestValueUtil(root.left, target, result);
        } else {
            return closestValueUtil(root.right, target, result);
        }
    }
}
