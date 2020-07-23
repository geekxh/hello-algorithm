package com.interview.tree;

/**
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 * Test cases
 * Null tree
 * 
 */
public class BinaryTreeToCircularLinkList {

    public Node convert(Node root){
        if(root == null){
            return null;
        }
        
        if(root.left == null && root.right == null){
            root.left = root;
            root.right = root;
            return root;
        }
        
        Node left = convert(root.left);
        Node right = convert(root.right);
    
        root.left = root;
        root.right = root;
        
        left = join(left,root);
        left = join(left,right);
        return left;
    }
    
    private Node join(Node r1, Node r2){

        if(r1 == null){
            return r2;
        }
        if(r2 == null){
            return r1;
        }
        Node t1 = r2.left;
        
        r1.left.right = r2;
        r2.left = r1.left;
        r1.left = t1;
        t1.right = r1;
        return r1;
    }
    
    private void print(Node root){
        Node temp = root;
        do{
            System.out.println(temp.data);
            temp = temp.right;
        }while(temp != root);

        System.out.println();
        do{
            System.out.println(temp.data);
            temp = temp.left;
        }while(temp != root);
    }
    
    public static void main(String args[]){
        BinaryTreeToCircularLinkList btc = new BinaryTreeToCircularLinkList();
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(3, root);
        root = bt.addNode(-1, root);
        root = bt.addNode(8, root);
        root = bt.addNode(-6, root);
        root = bt.addNode(13, root);
        root = btc.convert(root);
        btc.print(root);
    }
}
