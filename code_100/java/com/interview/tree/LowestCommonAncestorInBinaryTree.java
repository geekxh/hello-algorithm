package com.interview.tree;

/**
 * Date 04/27/2016
 * @author Tushar Roy
 *
 * Find lowest common ancestor in binary tree.
 *
 * Time complexity O(n)
 * Space complexity O(h)
 */
public class LowestCommonAncestorInBinaryTree {

    public Node lca(Node root, Node n1, Node n2){
        if(root == null){
            return null;
        }
        if(root == n1 || root == n2){
            return root;
        }
        
        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);

        if(left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }
}
