package com.interview.linklist;

/**
 * Date 04/17/2016
 * @author tusroy
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LoopInLinkList {

    public boolean hasCycle(Node head){
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null) {
            if (slow == fast || fast.next == slow) {
                return true;
            }
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
        }
        return false;
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
        Node node1 = ll.find(head, 8);
        Node node2 = ll.find(head, 4);
        node1.next = node2;
        LoopInLinkList lll = new LoopInLinkList();
        System.out.println(lll.hasCycle(head));
        
        node2.next = null;
        System.out.println(lll.hasCycle(head));
    
        node1 = ll.find(head, 3);
        node2.next = node1;
        System.out.println(lll.hasCycle(head));
    }
}
