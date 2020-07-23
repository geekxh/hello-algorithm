package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-binary-tree-from-inorder-traversal/
 * Given inorder traversal of binary tree where every node is greater than 
 * its left and right child.
 * Test cases:
 * One two or more nodes in the tree
 */
public class ContructTreeFromInOrderTraversalRootGreaterThanChild {

    public Node createTree(int inorder[]){
        return createTree(inorder,0,inorder.length-1);
    }
    private Node createTree(int inorder[],int low,int high)
    {
        if(low > high){
            return null;
        }
        int i;
        int maxIndex = low;
        for(i=low ; i <= high; i++){
            if(inorder[maxIndex] > inorder[i]){
                maxIndex = i;
            }
        }
        Node root = Node.newNode(inorder[maxIndex]);
        root.left = createTree(inorder,low,maxIndex-1);
        root.right = createTree(inorder,maxIndex+1,high);
        return root;
    }
    
    public static void main(String args[]){
        int inorder[] = {9,15,25,6,18,-1,3,-3};
        ContructTreeFromInOrderTraversalRootGreaterThanChild tf = new ContructTreeFromInOrderTraversalRootGreaterThanChild();
        Node root = tf.createTree(inorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
    }
}
