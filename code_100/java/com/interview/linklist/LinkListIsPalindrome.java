package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/ 
 * Test cases:
 * odd number of nodes
 * even number of nodes
 * 0 1 or more nodes
 * palindrome list
 * non palindrom list
 */
public class LinkListIsPalindrome {

    public boolean isPalindrome(NodeRef head,Node end){
        if(end == null){
            return true;
        }
        boolean r = isPalindrome(head,end.next);
        r = r && head.node.data == end.data;
        head.next();
        return r;
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(4, head);
        head = ll.addNode(3, head);
        head = ll.addNode(2, head);
        head = ll.addNode(1, head);
        NodeRef nodeRef = new NodeRef();
        nodeRef.node = head;
        LinkListIsPalindrome llp = new LinkListIsPalindrome();
        System.out.println(llp.isPalindrome(nodeRef, head));
    }
}
