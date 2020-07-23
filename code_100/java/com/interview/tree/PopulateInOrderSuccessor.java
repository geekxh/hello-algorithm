package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/populate-inorder-successor-for-all-nodes/
 */
public class PopulateInOrderSuccessor {

    private void populate(Node root, NodeRef nodeRef){
        if(root == null){
            return;
        }
        populate(root.right,nodeRef);
        root.next = nodeRef.node;
        nodeRef.node = root;
        populate(root.left,nodeRef);
    }
    
    public void populate(Node root){
        NodeRef nodeRef = new NodeRef();
        populate(root,nodeRef);
    }
    
    public void print(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        print(root.next);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        PopulateInOrderSuccessor pio = new PopulateInOrderSuccessor();
        pio.populate(head);
        while(head.left != null){
            head = head.left;
        }
        pio.print(head);
    }
}
