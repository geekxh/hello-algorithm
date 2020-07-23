package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Youtube link - https://youtu.be/9PHkM0Jri_4
 * 
 * http://www.geeksforgeeks.org/level-order-tree-traversal/ 
 * Test cases
 * Empty tree
 * Tree with only left side
 * Tree with only right side
 * Mixed size tree
 * Full tree
 * complete tree
 */
public class LevelOrderTraversal {

    public void levelOrder(Node root){
        if(root == null){
            System.out.println("Please enter a valid tree!");
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        System.out.println();
        while(queue.size() > 0){
            root = queue.poll();
            System.out.print(root.data + " ");
            if(root.left != null){
                queue.add(root.left);
            }
            if(root.right != null){
                queue.add(root.right);
            }
        }
    }

    public static void main(String args[]){
        LevelOrderTraversal loi = new LevelOrderTraversal();
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        loi.levelOrder(head);
    }
}
