package com.interview.tree;

/**
 * Date 03/16/2015
 * @author tusroy
 * 
 * Given a binary tree(not BST) convert it into sorted linklist.
 * e.g          10
 *          11        15
 *        8    9    16   13
 *                           7
 *                           
 * should become 7->8->9->10->11->13->15->16
 * 
 * Solution
 * Idea is that at every root, get the sorted linklist from left and right and merge them 
 * along with root and return left most element in this merged linklist to upper level call.
 * 
 * Test cases
 * 1) Null root
 * 2) One node root
 * 3) BST 
 * 4) Reverse BST
 * 5) All on left side of root
 * 
 * Reference
 * http://www.careercup.com/question?id=6241652616200192
 *
 */
public class DegenerateBinaryTreeToSortedLL {
   
    public Node degenerate(Node root){
        if(root == null){
            return null;
        }
        
        Node left = root.left;
        Node right = root.right;
        root.left = null;
        root.right = null;
        left = degenerate(left);
        right = degenerate(right);
        left = merge(left, root);
        return merge(left, right);
    }
    
    private Node merge(Node root1, Node root2){
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        
        if(root1.data <= root2.data){
            root1.right = merge(root1.right, root2);
            return root1;
        }else{
            root2.right = merge(root1, root2.right);
            return root2;
        }
    }
    
    private void printList(Node root){
        while(root != null){
            System.out.print(root.data + " ");
            root = root.right;
        }
    }
    
    public static void main(String args[]){
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        int inorder[] = {8, 11, 9, 10, 16, 15, 13, 7};
        int preorder[] = {10, 11, 8, 9, 15, 16, 13, 7};
        Node root = ctf.createTree(inorder, preorder);
        DegenerateBinaryTreeToSortedLL dbt = new DegenerateBinaryTreeToSortedLL();
        root = dbt.degenerate(root);
        dbt.printList(root);
    }
    
}
