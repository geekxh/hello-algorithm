package com.interview.tree;

/**
 * Date 04/11/2015
 * @author tusroy
 * 
 * Youtube link - https://youtu.be/zm83jPHZ-jA
 * 
 * Given a binary search tree and a key, return node which has data as this key or return
 * null if no node has data as key.
 * 
 * Solution 
 * Since its BST for every node check if root.data is key and if not go either left or
 * right depending on if root.data is greater or less than key
 * 
 * Time complexity is O(n) for non balanced BST
 * Time complexity is O(logn) for balanced BST
 * 
 * Test cases:
 * 1) null tree
 * 2) Tree with one node and key is that node
 * 3) Tree with many nodes and key does not exist
 * 4) Tree with many nodes and key exists
 */
public class BSTSearch {
    
    public Node search(Node root, int key){
        if(root == null){
            return null;
        }
        if(root.data == key){
            return root;
        }else if(root.data < key){
            return search(root.right, key);
        }else{
            return search(root.left, key);
        }
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(15, root);
        root = bt.addNode(0, root);
        root = bt.addNode(21, root);
        root = bt.addNode(-1, root);
        BSTSearch bstSearch = new BSTSearch();
        Node result = bstSearch.search(root, 21);
        assert result.data == 21;
        
        result = bstSearch.search(root, -1);
        assert result.data == 21;
        
        result = bstSearch.search(root, 11);
        assert result == null;
    }
}
