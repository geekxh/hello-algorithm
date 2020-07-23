package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way/
 * Test cases:
 * 0 nodes
 * 1 nodes 
 * 2 or more nodes
 * already sorted
 * reverse sorted
 * negative positive numbers 
 */
public class InsertionSortLinkList {

    private Node insert(Node head,Node curr){
        if(head == null){
            return curr;
        }
        Node prev = null;
        Node start = head;
        while(start != null && curr.data >= start.data){
            prev = start;
            start = start.next;
        }
        if(prev == null){
            curr.next = head;
            head = curr;
        }else{
            prev.next = curr;
            curr.next = start;
        }
        return head;
    }
    
    public Node sort(Node head){
        Node result = null;
        Node curr = head;
        Node prevCurr = null;
        while(curr != null){
            prevCurr = curr;
            curr = curr.next;
            prevCurr.next = null;
            result = insert(result,prevCurr);
        }
        return result;
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(11, head);
        head = ll.addNode(12, head);
        head = ll.addNode(-3, head);
        head = ll.addNode(45, head);
        head = ll.addNode(5, head);
        head = ll.addNode(101, head);
    
        InsertionSortLinkList isll = new InsertionSortLinkList();
        head = isll.sort(head);
        ll.printList(head);
    }
}
