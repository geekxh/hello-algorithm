package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/delete-n-nodes-after-m-nodes-of-a-linked-list/
 * Test cases:
 * neg value of m and/or n - not allowed
 * 0 value of n and/or m - not allowed
 * even n and m
 * odd n and m
 * odd size of the list
 * even size of the list
 */
public class DeleteNAfterMNodes {

    public void deleteNAfterMNodes(Node head,int m, int n){
        if(head == null){
            return;
        }
        while(head != null){
            int i = 0;
            while(head != null && i < m-1){
                head = head.next;
                i++;
            }
            if(head == null){
                break;
            }
            Node temp = head.next;
            i=0;
            while(temp != null && i < n){
                temp = temp.next;
                i++;
            }
            head.next = temp;
            head = temp;
        }
    }
    public static void main(String args[]){
        DeleteNAfterMNodes daf = new DeleteNAfterMNodes();
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(4, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        daf.deleteNAfterMNodes(head, 3, 2);
        ll.printList(head);
    }
}
