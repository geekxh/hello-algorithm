package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
 * Test cases:
 * Length of pre and val is not same
 * val contains values other than N or L
 */
class PreIndex{
    int index;
}
public class ConstructTreeFromPreOrderTraversalWith0or2Child {

    public Node createTree(int pre[],char val[]){
        PreIndex pi = new PreIndex();
        pi.index = 0;
        return createTree(pre, val,pi);
    }
    
    private Node createTree(int pre[],char val[], PreIndex ind){
        if(ind.index >= pre.length){
            return null;
        }
        Node root = Node.newNode(pre[ind.index]);
        
        if(val[ind.index] == 'L'){
            ind.index++;
        }else{
            ind.index++;
            root.left = createTree(pre,val,ind);
            root.right = createTree(pre,val,ind);
        }
        return root;
    }
    
    public static void main(String args[]){
        int pre[] = {10,20,30,40,50,60,70,80,90};
        char val[] = {'N','N','N','L','L','L','N','L','L'};
        ConstructTreeFromPreOrderTraversalWith0or2Child tfp = new ConstructTreeFromPreOrderTraversalWith0or2Child();
        Node root = tfp.createTree(pre, val);
        TreeTraversals tt = new TreeTraversals();
        tt.preOrder(root);
        System.out.println();
        tt.inOrder(root);
    }
}
