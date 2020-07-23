package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
 * Only operation you can do is increase data on the node. No decrement of data
 * Test case
 * Root greater than children
 * Root less than children
 * Root equal to children
 */
public class ArbitaryTreeToChildSumTree {

    public void childSumTree(Node root){
        toChildSumTree(root);
    }
    
    private void incrementChild(Node root,int increment){
        if(root == null || (root.left ==null && root.right == null)){
            return;
        }
        if(root.left != null){
            root.left.data = root.left.data + increment;
            incrementChild(root.left,increment);
        }else{
            root.right.data = root.right.data + increment;
            incrementChild(root.right,increment);
        }
    }
    
    private int toChildSumTree(Node root){
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return root.data;
        }
        
        int sum1 = toChildSumTree(root.left);
        int sum2 = toChildSumTree(root.right);
        if(root.data < sum1 + sum2){
            root.data = sum1 + sum2;
        }else if(root.data > sum1 + sum2){
            incrementChild(root,root.data - sum1 - sum2);
        }
        return root.data;
    }
    
    public static void main(String args[]){
        ArbitaryTreeToChildSumTree att = new ArbitaryTreeToChildSumTree();
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        att.childSumTree(head);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(head);
    }
    
}
