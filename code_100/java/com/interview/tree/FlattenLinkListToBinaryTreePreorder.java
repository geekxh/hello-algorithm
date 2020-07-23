package com.interview.tree;

import java.util.Stack;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given a binary tree, flatten it to a linked list in-place in preorder traversal.
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenLinkListToBinaryTreePreorder {
    public void flatten(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node prev = null;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (prev != null) {
                prev.right = node;
            }
            prev = node;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            node.left = null;
            node.right = null;
        }
    }

    public void flatten1(Node root) {
        if(root==null)
            return;
        flatten(root.left);
        flatten(root.right);
        Node left  = root.left;
        Node right = root.right;
        root.left  = null;
        root.right = left;
        while(root.right!=null)
            root = root.right;
        root.right = right;
    }
}
