package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 * Test case
 * 0 node in the list
 * 1 node in the list
 * All nodes with child
 * No nodes with child
 */
public class FlattenLinkList {

    public void flatten(Node head) {
        Node tail = getTail(head);
        while (head != null) {
            if (head.child != null) {
                tail.next = head.child;
                tail = getTail(tail.next);
                head.child = null;
            }
            head = head.next;
        }
    }

    private Node getTail(Node head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static void main(String args[]) {
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(10, head);
        head = ll.addNode(5, head);
        head = ll.addNode(12, head);
        head = ll.addNode(7, head);
        head = ll.addNode(11, head);

        Node head1 = null;
        head1 = ll.addNode(4, head1);
        head1 = ll.addNode(20, head1);
        head1 = ll.addNode(13, head1);

        Node head2 = null;
        head2 = ll.addNode(2, head2);
        head2 = ll.addNode(8, head2);

        Node head4 = null;
        head4 = ll.addNode(17, head4);
        head4 = ll.addNode(6, head4);

        Node head5 = null;
        head5 = ll.addNode(9, head5);
        head5 = ll.addNode(8, head5);
        head5 = ll.addNode(15, head5);

        Node f1 = ll.find(head, 10);
        f1.child = head1;

        f1 = ll.find(head, 7);
        f1.child = head4;

        f1 = ll.find(head4, 17);
        f1.child = head5;

        f1 = ll.find(head1, 20);
        f1.child = head2;
        
        FlattenLinkList fll = new FlattenLinkList();
        fll.flatten(head);
        ll.printList(head);
    }
}
