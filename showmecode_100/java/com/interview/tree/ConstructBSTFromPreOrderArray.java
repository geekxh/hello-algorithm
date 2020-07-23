package com.interview.tree;

class Index{
    int index;
}
/**
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 * Test case
 * empty array
 * 1,2 or more elements in the array
 */
public class ConstructBSTFromPreOrderArray {

    public Node toBST(int preorder[]){
        Index index = new Index();
        return toBST(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE,index);
    }
    
    //stack based version
    public Node toBSTIterative(int preorder[]){
        return null;
    }
    
    private Node toBST(int preorder[],int min, int max,Index index){
        if(index.index >= preorder.length){
            return null;
        }
        if(preorder[index.index] < min || preorder[index.index] >= max){
            return null;
        }
        
        Node node = new Node();
        node.data = preorder[index.index];
        index.index++;
        node.left = toBST(preorder,min,node.data, index);
        node.right = toBST(preorder,node.data,max,index);
        return node;
    }
    
    public static void main(String args[]){
        int preorder[] = {10,5,1,7,40,50};
        ConstructBSTFromPreOrderArray poa = new ConstructBSTFromPreOrderArray();
        Node root = poa.toBST(preorder);
        TreeTraversals tt = new TreeTraversals();
        tt.preOrder(root);
        System.out.println();
        tt.inOrder(root);
    }
}
