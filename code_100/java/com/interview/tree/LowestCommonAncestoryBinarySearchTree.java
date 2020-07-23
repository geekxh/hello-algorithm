package com.interview.tree;

/**
 * Date 05/04/2016
 * @author Tushar Roy
 *
 * Lowest common ancestor in binary search tree.
 *
 * Time complexity O(height of tree)
 * Space complexity O(height of tree)
 * 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestoryBinarySearchTree {

    public Node lowestCommonAncestor(Node root, int p, int q) {
        if (root.data > Math.max(p, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.data < Math.min(p, q)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
