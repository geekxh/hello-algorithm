package com.interview.linklist;

/**
 * Given a linklist which has individual sorted componenets sort the entire linst
 * e.g 1-3-6-8-4-5-10-7-9
 * Here 1,2,6,8 are sorted, 4,5,10 are sorted and 7,9 are sorted
 * Test case
 * null node
 * 1 node
 * 2 sorted nodes
 * 2 reverse sorted nodes
 * 3 reverse sorted nodes
 * 4 nodes 2 each sorted among themselves
 */
public class SortNearlySortedList {

    public Node sort(Node head){
        Node result = null;
        Node start = head;
        while(head != null && head.next != null){
            if(head.data < head.next.data){
                head = head.next;
            }else{
                Node temp = head.next;
                head.next = null;
                result = mergeSort(result,start);
                head = temp;
                start = temp;
            }
        }
        result = mergeSort(result,start);
        return result;
    }
    
    private Node mergeSort(Node head1,Node head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        if(head1.data <= head2.data){
            head1.next = mergeSort(head1.next,head2);
            return head1;
        }else{
            head2.next = mergeSort(head1,head2.next);
            return head2;
        }
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(7, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        head = ll.addNode(13, head);
        head = ll.addNode(11, head);
        head = ll.addNode(12, head);
        
        SortNearlySortedList sns = new SortNearlySortedList();
        head = sns.sort(head);
        ll.printList(head);
    }
}
