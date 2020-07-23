package com.interview.tree;

class Height{
    int height;
}

/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * Test cases
 * All left nodes
 * All right nodes
 */
public class DiameterOfTree {

    public int diameter(Node root){
        Height height = new Height();
        return diameter(root,height);
    }
    
    private int diameter(Node root, Height height){
    
        if(root == null){
            return 0;
        }
        
        Height leftHeight = new Height();
        Height rightHeight = new Height();
        int dial = diameter(root.left,leftHeight);
        int diar = diameter(root.right,rightHeight);
        height.height = Math.max(leftHeight.height, rightHeight.height) + 1;
        return Math.max(Math.max(dial, diar),(1 + leftHeight.height + rightHeight.height));
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        head = bt.addNode(11, head);
        head = bt.addNode(12, head);
        head = bt.addNode(13, head);
        head = bt.addNode(14, head);
        DiameterOfTree dt = new DiameterOfTree();
        System.out.println(dt.diameter(head));
    }
}
