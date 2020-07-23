package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/
 * This does not print root node even though it has no sibling
 * Test cases:
 * Null tree
 * Only one node tree
 * All left side tree
 * All right side tree
 * Regular mix tree
 */
public class NodesWithNoSibling {

    public void printNodes(Node root){
        if(root == null){
            return;
        }
        if(root.left == null || root.right == null){
            if(root.left != null){
                System.out.print(root.left.data + " ");
            }
            if(root.right  != null){
                System.out.print(root.right.data + " ");
            }
        }
        printNodes(root.left);
        printNodes(root.right);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(5, root);
        root = bt.addNode(-1, root);
        root = bt.addNode(-5, root);
        root = bt.addNode(20, root);
        root = bt.addNode(25, root);
        NodesWithNoSibling nws = new NodesWithNoSibling();
        nws.printNodes(root);
    }
}
