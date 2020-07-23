package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/tree-isomorphism-problem/
 * Test cases:
 * Same tree
 * Exact mirror
 * Some nodes flipped
 */
public class TreeIsomorphism {

    public boolean areIsomorphicTrees(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        
        if(root1 == null || root2 == null){
            return false;
        }

        return root1.data == root2.data
                && ((areIsomorphicTrees(root1.left, root2.left) && areIsomorphicTrees(
                        root1.right, root2.right)) || (areIsomorphicTrees(
                        root1.left, root2.right) && areIsomorphicTrees(
                        root1.right, root2.left)));

    }
    
    public static void main(String args[]){
        int in1[] = {8,5,6,10,11,9,12};
        int pre1[] = {10,5,8,6,9,11,12};
        int in2[] = {11,9,12,10,6,5,15};
        int pre2[] = {10,9,11,12,5,6,15};
        ConstructTreeFromInOrderPreOrder ct = new ConstructTreeFromInOrderPreOrder();
        Node root1 = ct.createTree(in1, pre1);
        Node root2 = ct.createTree(in2, pre2);
        TreeIsomorphism ti = new TreeIsomorphism();
        System.out.println(ti.areIsomorphicTrees(root1, root2));
    }
}
