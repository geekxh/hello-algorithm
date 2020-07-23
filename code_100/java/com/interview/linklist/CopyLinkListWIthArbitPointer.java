package com.interview.linklist;

/**
 * Date 03/24/2016
 * @author Tushar Roy
 *
 * A linked list is given such that each node contains an additional random pointer which could point
 * to any node in the list or null. Return a deep copy of the list.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyLinkListWIthArbitPointer {

    static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode current = head;
        while (current != null) {
            RandomListNode newNode = new RandomListNode(current.label);
            newNode.next = current.next;
            newNode.random = current.random;
            current.next = newNode;
            current = newNode.next;
        }

        current = head;
        while (current != null) {
            RandomListNode next = current.next;
            if (next.random != null) {
                next.random = next.random.next;
            }
            current = current.next.next;
        }

        current = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newCurrent = dummy;
        while (current != null) {
            newCurrent.next = current.next;
            newCurrent = newCurrent.next;
            current.next = current.next.next;
            current = current.next;
        }

        return dummy.next;
    }

    public static void main(String args[]){

        CopyLinkListWIthArbitPointer cll = new CopyLinkListWIthArbitPointer();

        RandomListNode randomListNode = new RandomListNode(-1);
        RandomListNode randomListNode1 = new RandomListNode(4);
        RandomListNode randomListNode2 = new RandomListNode(8);
        RandomListNode randomListNode3 = new RandomListNode(-3);
        RandomListNode randomListNode4 = new RandomListNode(7);
        randomListNode.next = randomListNode1;
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;

        randomListNode.random = randomListNode1;
        randomListNode2.random = randomListNode3;
        randomListNode1.random = randomListNode;
        cll.copyRandomList(randomListNode);
    }
}
