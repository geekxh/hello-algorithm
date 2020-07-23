package com.interview.linklist;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {

    public void reorderList(Node head) {
        Node back = frontBackSplit(head);
        back = reverse(back);
        alternateMerge(head, back);

    }

    private Node alternateMerge(Node head1, Node head2) {
        Node dummyHead = new Node();
        Node current = dummyHead;
        while (head1 != null && head2 != null) {
            current.next= head1;
            head1 = head1.next;
            current = current.next;
            current.next = head2;
            head2 = head2.next;
            current = current.next;
        }
        current.next = head1;
        return dummyHead.next;
    }

    private Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        Node front = null;
        Node mid = head;
        Node next = null;
        while (mid != null) {
            next = mid.next;
            mid.next = front;
            front = mid;
            mid = next;
        }
        return front;
    }

    private Node frontBackSplit(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        head = head.next;
        while (head != null && head.next != null) {
            slow = slow.next;
            head = head.next.next;
        }
        Node next = slow.next;
        slow.next = null;
        return next;
    }
}
