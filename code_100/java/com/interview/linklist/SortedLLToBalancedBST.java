package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
 * Test cases
 * empty list
 * 0 1 or more node lists
 */
public class SortedLLToBalancedBST {

    public Node toBalancedBST(Node head){
        LinkList ll = new LinkList();
        int size = ll.size(head);
        NodeRef headRef = new NodeRef();
        headRef.node = head;
        return toBalancedBST(headRef, size);
    }
    
    private Node toBalancedBST(NodeRef headRef, int size){
        if(size <= 0){
            return null;
        }
        Node left = toBalancedBST(headRef,size/2);
        Node head = headRef.node;
        headRef.next();
        Node right = toBalancedBST(headRef,size - size/2 -1);
        head.before = left;
        head.next = right;
        return head;
    }
    
    public void printTreeInOrder(Node head){
        if(head == null){
            return;
        }
        printTreeInOrder(head.before);
        System.out.println(head.data);
        printTreeInOrder(head.next);
    }
    
    public void printTreePreOrder(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.data);
        printTreePreOrder(head.before);
        printTreePreOrder(head.next);
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(4, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        head = ll.addNode(7, head);
        SortedLLToBalancedBST sll = new SortedLLToBalancedBST();
        head = sll.toBalancedBST(head);
        sll.printTreeInOrder(head);
        sll.printTreePreOrder(head);
    }
}
