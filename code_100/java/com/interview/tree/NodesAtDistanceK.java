package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
 * Test case 
 * k should not be negative
 * k could be very big number which means nothing was found
 * dest might/might not exists in the tree
 * root could be null
 */
public class NodesAtDistanceK {

    private void findInChild(Node root, int k){
        if(root == null){
            return;
        }
        if(k == 0){
            System.out.println(root.data);
        }
        findInChild(root.left, k-1);
        findInChild(root.right, k-1);
    }
    
    public int printNodes(Node root,int dest, int k){
        if(root == null){
            return -1;
        }
        
        if(root.data == dest){
            findInChild(root, k);
            return k-1;
        }
        
        int found = printNodes(root.left,dest,k);
        if(found != -1){
            if(found == 0){
                System.out.println(root.data);
            }else{
                findInChild(root.right, found-1);
            }
            return found -1;
        }
        
        found = printNodes(root.right,dest,k);
        if(found != -1){
            if(found == 0){
                System.out.println(root.data);
            }else{
                findInChild(root.left, found-1);
            }
            return found-1;
        }
        return -1;
    }
    
    public static void main(String args[]){
        NodesAtDistanceK nad = new NodesAtDistanceK();
        Node root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addNode(10, root);
        root = bt.addNode(3, root);
        root = bt.addNode(-1, root);
        root = bt.addNode(8, root);
        root = bt.addNode(-6, root);
        root = bt.addNode(-11, root);
        root = bt.addNode(18, root);
        root = bt.addNode(11, root);
        root = bt.addNode(13, root);
        root = bt.addNode(26, root);
        root = bt.addNode(27, root);
        nad.printNodes(root, 11, 2);
    }
}
