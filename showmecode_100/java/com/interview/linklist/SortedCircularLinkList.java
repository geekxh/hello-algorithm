package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * Test cases
 * Insert 2nd element smaller than head
 * Insert 2nd element larger than head
 * Insert element larger than tail
 * Insert element just before tail
 * Insert element somewhere between head and tail
 */
public class SortedCircularLinkList {

    public Node add(Node head,int data){
        if(head == null){
            head = Node.newNode(data);
            head.next = head;
            return head;
        }
        Node node = Node.newNode(data);
        Node tail = getTail(head);
        if(node.data < head.data){
            node.next = head;
            tail.next = node;
            return node;
        }
        Node current = head;
        Node pre = null;
        while(current != tail && node.data >= current.data){
            pre = current;
            current = current.next;
        }
        if(node.data < current.data){
            node.next = current;
            pre.next = node;
        }
        else{
            node.next = tail.next;
            tail.next = node;
        }
        return head;
    }
    
    private Node getTail(Node head){
        Node temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        return temp;
    }
    
    public void printList(Node head){
        if(head == null){
            return;
        }
        Node current = head.next;
        System.out.println(head.data);
        while(current != head){
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    public static void main(String args[]){
        SortedCircularLinkList scll = new SortedCircularLinkList();
        Node head = null;
        head = scll.add(head, 10);
        head = scll.add(head, 12);
        head = scll.add(head, -1);
        head = scll.add(head, -5);
        head = scll.add(head, 11);
        head = scll.add(head, 7);
        
        scll.printList(head);
    }
}
