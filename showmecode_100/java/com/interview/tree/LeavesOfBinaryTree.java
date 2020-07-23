package com.interview.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 10/08/2016
 * @author Tushar Roy
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 *
 * Time complexity O(n^2) for unbalanced tree.
 *
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 */
public class LeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(Node root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        while (stripLeaves(root, leaves) != null) {
            result.add(leaves);
            leaves = new ArrayList<>();
        }
        result.add(leaves);
        return result;
    }

    Node stripLeaves(Node root, List<Integer> leaves) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.data);
            return null;
        }
        root.left = stripLeaves(root.left, leaves);
        root.right = stripLeaves(root.right, leaves);
        return root;
    }
}
