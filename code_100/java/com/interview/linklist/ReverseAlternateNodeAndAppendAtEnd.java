package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/given-linked-list-reverse-alternate-nodes-append-end/
 * Test case
 * Even and odd number of nodes
 */
public class ReverseAlternateNodeAndAppendAtEnd {

    public void act(Node head){
        
        Node result = null;
        LinkList ll = new LinkList();
        while(head != null && head.next != null){
            Node temp = head.next;
            head.next = head.next.next;
            temp.next = null;
            result = ll.addAtFront(temp,result);
            if(head.next == null){
                break;
            }
            head = head.next;
        }
        head.next = result;
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
        ReverseAlternateNodeAndAppendAtEnd ran = new ReverseAlternateNodeAndAppendAtEnd();
        ran.act(head);
        ll.printList(head);
    }
}
