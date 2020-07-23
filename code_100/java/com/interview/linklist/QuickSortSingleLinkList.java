package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/quicksort-on-singly-linked-list/
 * Test cases
 * 0 1 or more nodes
 * sorted reverse sorted nodes
 */

//keep head and tail of each result since caller function needs it to 
//set complete linklist. If we do not keep tail in each recursion we will
//have to traverse to tail of left side which can be costly operation
class HeadTail{
  Node head;
  Node tail;
}

public class QuickSortSingleLinkList {

    public Node quickSort(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node smaller = null;
        Node larger = null;
        Node pivot = head;
        Node temp = head.next;
        pivot.next = null;
        LinkList ll = new LinkList();
        while(temp != null){
            Node next = temp.next;
            temp.next = null;
            if(temp.data < pivot.data){
                smaller = ll.addAtFront(temp, smaller);
            }else{
                larger = ll.addAtFront(temp, larger);
            }
            temp = next;
        }
        
        smaller = quickSort(smaller);
        larger = quickSort(larger);
        
        Node tail1 = smaller;
        
        //this is costly operation which can be prevented by keeping tail.
        while(tail1 != null && tail1.next != null){
            tail1 = tail1.next;
        }
        
        if(smaller != null){
            tail1.next = pivot;
            pivot.next = larger;
            return smaller;
        }else{
            pivot.next = larger;
            return pivot;
        }
    }
    
    public Node quickSortFaster(Node head){
        HeadTail result = quickSortUtil(head);
        return result.head;
    }
  
    /**
     * This version keeps tail of each recursion which helps us avoid going to tail in each recursion.
     * @param head
     * @return
     */
    private HeadTail quickSortUtil(Node head){
        if(head == null || head.next == null){
            HeadTail headTail = new HeadTail();
            headTail.head = head;
            headTail.tail = head;
            return headTail;
        }
        LinkList ll = new LinkList();
        Node leftHead = null;
        Node rightHead = null;
        
        Node curr = head.next;
        head.next = null;
        Node next = null;
        while(curr != null){
            next = curr.next;
            curr.next = null;
            if(curr.data < head.data){
                leftHead = ll.addAtFront(curr, leftHead);
            }else{
                rightHead = ll.addAtFront(curr, rightHead);
            }
            curr = next;
        }
        HeadTail leftSide = quickSortUtil(leftHead);
        HeadTail rightSide = quickSortUtil(rightHead);
        head.next= rightSide.head;
        HeadTail result = new HeadTail();
        result.head = head;
        result.tail = head;
        if(leftSide.tail != null){
            leftSide.tail.next = head;
            result.head = leftSide.head;
        }
        if(rightSide.head != null){
            head.next = rightSide.head;
            result.tail = rightSide.tail;
        }
        return result;
    }

    
    public static void main(String args[]){
        QuickSortSingleLinkList qss = new QuickSortSingleLinkList();
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(11, head);
        head = ll.addNode(2, head);
        head = ll.addNode(-1, head);
        head = ll.addNode(50, head);
        head = ll.addNode(13, head);
        head = ll.addNode(-5, head);
        head = ll.addNode(10, head);
        head = ll.addNode(8, head);
    
        head = qss.quickSortFaster(head);
        ll.printList(head);
    }
}
