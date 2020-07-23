package com.interview.linklist;

/**
 * Date 04/17/2016
 * @author Tushar Roy
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
 * numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class DeleteDuplicateNodes {
    public Node deleteDuplicates(Node head) {
        Node dummyNode = new Node();
        dummyNode.next = head;
        Node current = head;
        Node prev = dummyNode;
        while (current != null) {
            while(current.next != null && current.data == current.next.data) {
                current = current.next;
            }
            if (prev.next == current) {
                prev = current;
            } else {
                prev.next = current.next;
            }
            current = current.next;
        }
        return dummyNode.next;
    }
}
