package com.interview.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 04/11/2015
 * @author tusroy
 * 
 * Youtube link - https://youtu.be/MILxfAbIhrE
 * 
 * Given a binary tree, return true if it is binary search tree else return false.
 * 
 * Solution
 * Keep min, max for every recursion. Initial min and max is very small and very larger
 * number. Check if root.data is in this range. When you go left pass min and root.data and 
 * for right pass root.data and max.
 * 
 * Time complexity is O(n) since we are looking at all nodes.
 * 
 * Test cases:
 * Null tree
 * 1 or 2 nodes tree
 * Binary tree which is actually BST
 * Binary tree which is not a BST
 */
public class IsBST {

    public boolean isBST(Node root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isBST(Node root, int min, int max){
        if(root == null){
            return true;
        }
        if(root.data <= min || root.data > max){
            return false;
        }
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }


    public boolean isBSTIterative(Node root) {
        if (root == null) {
            return true;
        }

        Deque<Node> stack = new LinkedList<>();
        Node node = root;
        int prev = Integer.MIN_VALUE;
        int current;
        while ( true ) {
            if (node != null) {
                stack.addFirst(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pollFirst();
                current = node.data;
                if (current < prev) {
                    return false;
                }
                prev = current;
                node = node.right;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(15, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(17, root);
        root = bt.addNode(20, root);
        root = bt.addNode(0, root);
        
        IsBST isBST = new IsBST();
        assert isBST.isBST(root);
        assert isBST.isBSTIterative(root);
    }
}
