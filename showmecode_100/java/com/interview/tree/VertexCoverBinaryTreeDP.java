package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming-solution-tree/
 * http://en.wikipedia.org/wiki/Vertex_cover
 * Using lis to store the cover data
 * Test cases:
 * null root
 * Only left child
 * Only right child
 * Tree with only one child at every node
 */
public class VertexCoverBinaryTreeDP {

    public int cover(Node root){
        if(root == null){
            return 0;
        }
        //no need to include leaf node ever
        if(root.left == null && root.right == null){
            return 0;
        }
        //store result
        if(root.lis != -1){
            return root.lis;
        }
        //if root is included
        int inclRoot = 1 + cover(root.left) + cover(root.right);
        int exclRoot = 0;
        //if root is not included
        if(root.left!=null){
            exclRoot += (1 + cover(root.left.left) + cover(root.left.right));
        }
        if(root.right!=null){
            exclRoot += (1 + cover(root.right.left) + cover(root.right.right));
        }
        root.lis = Math.min(inclRoot, exclRoot);
        return root.lis;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(0, root);
        root = bt.addNode(-5, root);
        root = bt.addNode(5, root);
        root = bt.addNode(7, root);
        root = bt.addNode(3, root);
        root = bt.addNode(30, root);
        root = bt.addNode(40, root);
        root = bt.addNode(25, root);
        root = bt.addNode(46, root);
        root = bt.addNode(-8, root);
        root = bt.addNode(-2, root);
        root = bt.addNode(-1, root);
        root = bt.addNode(28, root);
        VertexCoverBinaryTreeDP vc = new VertexCoverBinaryTreeDP();
        System.out.println(vc.cover(root));
    }
}
