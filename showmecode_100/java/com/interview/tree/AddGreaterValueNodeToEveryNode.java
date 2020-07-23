package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/
 * Test cases:
 * Empty tree
 * One node tree
 * Two node tree
 */

class IntegerRef{
    int val;
}

public class AddGreaterValueNodeToEveryNode {

    public void add(Node root,IntegerRef ref){
        if(root == null){
            return ;
        }
        add(root.right,ref);
        root.data += ref.val;
        ref.val = root.data;
        add(root.left,ref);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(5, root);
        root = bt.addNode(20, root);
        root = bt.addNode(15, root);
        root = bt.addNode(25, root);
        AddGreaterValueNodeToEveryNode agv = new AddGreaterValueNodeToEveryNode();
        IntegerRef ir = new IntegerRef();
        ir.val = 0;
        agv.add(root, ir);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
    }
}
