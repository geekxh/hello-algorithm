package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/
 * Test case
 * k is even odd
 * number of nodes are even odd
 * k is less than or equal to 1.
 */
public class ReverseAlternateKNodes {

    public Node reverse(Node head,int k,boolean reverse){
        if(k <= 1){
            return head;
        }
        if(head == null){
            return null;
        }
        if(reverse){
            int i =0;
            Node front = null;
            Node middle = head;
            Node end = null;
            while(middle != null && i < k){
                end = middle.next;
                middle.next = front;
                front = middle;
                middle = end;
                i++;
            }
            head.next = reverse(middle,k, !reverse);
            head = front;
        }else{
            int i=0;
            Node temp = head;
            while(i < k-1 && head != null){
                head = head.next;
                i++;
            }
            if(head != null){
                head.next = reverse(head.next,k, !reverse);
            }
            head = temp;
        }
        return head;
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
        
        ReverseAlternateKNodes ra = new ReverseAlternateKNodes();
        head = ra.reverse(head, 3, false);
        ll.printList(head);
    }
}
