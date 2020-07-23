package com.interview.tree;

/**
 * http://www.careercup.com/question?id=5344154741637120
 */
public class SinkNegativeToBottom {

    public void sinkZero(Node root) {
        if (root == null) {
            return;
        }

        sinkZero(root.left);
        sinkZero(root.right);

        if (root.data < 0) {
            pushDown(root);
        }
    }

    private void pushDown(Node root) {
        if(root == null){
            return;
        }
        // find a child with non zero node value
        if (root.left == null && root.right == null) {
            // already leaf node. nothing to do. just return
            return;
        }

        //if root left is not null and root left data is not 0 pick it to swap
        if (root.left != null && root.left.data >= 0) {
            int temp = root.data;
            root.data = root.left.data;
            root.left.data = temp;
            pushDown(root.left);
        }
        else if(root.right != null && root.right.data >= 0){
            int temp = root.data;
            root.data = root.right.data;
            root.right.data = temp;
            pushDown(root.right);
        }
    }
    
    public static void main(String args[]){
        int preorder[] = {-1,1,6,-2,11,3,2,-3,31,22,17};
        int inorder[]  = {-2,6,11,1,3,-1,31,-3,22,2,17};
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        Node root = ctf.createTree(inorder, preorder);
        SinkNegativeToBottom szb = new SinkNegativeToBottom();
        szb.sinkZero(root);
        LevelOrderTraversal lot = new LevelOrderTraversal();
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        lot.levelOrder(root);
    }
}
