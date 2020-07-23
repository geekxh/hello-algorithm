package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Test cases:
 * Empty tree
 * One node tree
 * All left side tree
 * All right side tree
 * Mixed tree
 * Full tree
 * complete tree
 */
public class ConstructTreeFromInOrderPreOrder {
    
    private int index = 0;
    public Node createTree(int inorder[],int preorder[]){
        Node result =  createTree(inorder,preorder,0,inorder.length-1);
        index = 0;
        return result;
    }
    
    private Node createTree(int inorder[],int preorder[], int start, int end){
        if(start > end){
            return null;
        }
        int i;
        for(i=start; i <= end; i++){
            if(preorder[index] == inorder[i]){
                break;
            }
        }
        Node node = Node.newNode(preorder[index]);
        index++;
        node.left = createTree(inorder,preorder,start,i-1);
        node.right = createTree(inorder,preorder,i+1,end);
        return node;
    }
}
