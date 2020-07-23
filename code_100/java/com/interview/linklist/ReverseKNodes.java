package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list/
 * Test case
 * odd or even number of k
 * odd or even number of nodes in the list
 */
public class ReverseKNodes {

    public Node reverse(Node head,int k){
        if(head == null){
            return null;
        }
        Node front = null;
        Node middle = head;
        Node end = null;
        int i=0;
        while(middle != null && i < k){
            end = middle.next;
            middle.next = front;
            front = middle;
            middle = end;
            i++;
        }
        head.next = reverse(middle,k);
        return front;
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
        head = ll.addNode(8, head);
        ReverseKNodes rn = new ReverseKNodes();
        head = rn.reverse(head, 3);
        ll.printList(head);
    }
}
