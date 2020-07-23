package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 * Full tree is a tree with all nodes with either 0 or 2 child. Never has 1 child.
 * Test cases
 * Empty tree
 * Tree with big on left side
 * Tree with big on right side
 */
public class ConstructFullTreeFromPreOrderPostOrder {

    public Node constructTree(int preorder[],int postorder[]){
    
        return constructTree(preorder, postorder, 0, postorder.length-2, 0);
        
    }
    
    private Node constructTree(int preorder[],int postorder[],int low,int high,int index){

        if(low > high || index >= preorder.length-1){
            Node node = new Node();
            node.data = preorder[index];
            return node;
        }
        
        Node node = new Node();
        node.data = preorder[index];
        int i=0;
        for(i=low; i <= high; i++){
            if(preorder[index+1] == postorder[i]){
                break;
            }
        }
        node.left = constructTree(preorder, postorder, low, i-1, index+1);
        node.right = constructTree(preorder, postorder, i+1, high-1, index + i-low+2);
        return node;
    }
    
    public static void main(String args[]){
        ConstructFullTreeFromPreOrderPostOrder cft = new ConstructFullTreeFromPreOrderPostOrder();
        int preorder[] = {1,2,3,6,7,8,9};
        int postorder[] = {2,6,8,9,7,3,1};
        Node root = cft.constructTree(preorder, postorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        tt.preOrder(root);
        tt.postOrder(root);
    }
}
