package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Date 04/20/2015
 * @author tusroy
 * 
 * Video link - https://youtu.be/D2bIbWGgvzI
 *
 * Given a binary tree print its level order traversal in reverse
 * e.g           1
 *          2         3
 *        4    5    6   7
 * 
 * Output should be 4 5 6 7 2 3 1
 * 
 * Solution
 * Maintain a stack and queue. Do regular level order traversal but
 * put right first in the queue. Instead of printing put the result
 * in stack. Finally print contents of the stack.
 * 
 * Time and space complexity is O(n)
 * 
 * References : http://www.geeksforgeeks.org/reverse-level-order-traversal/
 */
public class LevelOrderTraversalInReverse {

    public void reverseLevelOrderTraversal(Node root){
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        Stack<Node> s = new Stack<>();
        
        q.offer(root);
        while(!q.isEmpty()){
            root = q.poll();
            if(root.right != null){
                q.offer(root.right);
            }
            if(root.left != null){
                q.offer(root.left);
            }
            s.push(root);
        }
        while(!s.isEmpty()){
            System.out.print(s.pop().data + " ");
        }
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(30, root);
        root = bt.addNode(25, root);
        root = bt.addNode(35, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(0, root);
        root = bt.addNode(-20, root);
        root = bt.addNode(-15, root);
        root = bt.addNode(45, root);
        LevelOrderTraversalInReverse rlo = new LevelOrderTraversalInReverse();
        rlo.reverseLevelOrderTraversal(root);
    }
}
