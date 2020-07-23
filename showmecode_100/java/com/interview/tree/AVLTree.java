package com.interview.tree;

/**
 * Date 07/04/2014
 * @author tusroy
 *
 * Video link - https://youtu.be/rbg7Qf8GkQ4
 *
 * Write a program to insert into an AVL tree.
 * 
 * AVL tree is self balancing binary tree. Difference of height of left or right subtree
 * cannot be greater than one.
 * 
 * There are four different use cases to insert into AVL tree
 * left left - needs ones right rotation
 * left right - needs one left and one right rotation
 * right left - needs one right and one left rotation
 * right right - needs one left rotation
 * 
 * Follow rotation rules to keep tree balanced.
 * 
 * At every node we will also keep height of the tree so that we don't
 * have to recalculate values again.
 * 
 * Runtime complexity to insert into AVL tree is O(logn).
 * 
 * References 
 * http://en.wikipedia.org/wiki/AVL_tree
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * 
 */
public class AVLTree {

    private Node leftRotate(Node root){
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }
    
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }

    private int setHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }
    
    private int height(Node root){
        if(root == null){
            return 0;
        }else {
            return root.height;
        }
    }
    
    private int setSize(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.size : 0), (root.right != null ? root.right.size : 0));
    }
    
    public Node insert(Node root, int data){
        if(root == null){
            return Node.newNode(data);
        }
        if(root.data <= data){
            root.right = insert(root.right,data);
        }
        else{
            root.left = insert(root.left,data);
        }
        int balance = balance(root.left, root.right);
        if(balance > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
            }else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }else if(balance < -1){
            if(height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            }else{
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        else{
            root.height = setHeight(root);
            root.size = setSize(root);
        }
        return root;
    }
    
    private int balance(Node rootLeft, Node rootRight){
        return height(rootLeft) - height(rootRight);
    }
    
    public static void main(String args[]){
        AVLTree avlTree = new AVLTree();
        Node root = null;
        root = avlTree.insert(root, -10);
        root = avlTree.insert(root, 2);
        root = avlTree.insert(root, 13);
        root = avlTree.insert(root, -13);
        root = avlTree.insert(root, -15);
        root = avlTree.insert(root, 15);
        root = avlTree.insert(root, 17);
        root = avlTree.insert(root, 20);
        
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
    }
}
