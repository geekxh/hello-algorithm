package com.interview.linklist;

/**
 * Swap two nodes in double link list. If it swaps first node its callers responsibility to fix the head 
 * Test cases
 * A right neighbor of B
 * B right neighbor of A
 * A and B not neighbors of each other
 * A or B are start or end nodes
 */
public class SwapTwoNodesInDoubleLL {

    public void swap(Node nodeA, Node nodeB){
        if(nodeA == null || nodeB == null){
            throw new IllegalArgumentException();
        }
        //if B is right neighbor of A
        if(nodeA.next == nodeB){
            if(nodeA.before != null){
                nodeA.before.next = nodeB;
                nodeB.before = nodeA.before;
            }else{
                nodeB.before = null;
            }
            if(nodeB.next != null){
                nodeB.next.before = nodeA;
                nodeA.next = nodeB.next;
            }else{
                nodeA.next = null;
            }
            nodeB.next = nodeA;
            nodeA.before = nodeB;
        }//else if A is right neighbor of B
        else if(nodeB.next == nodeA){
            if(nodeB.before != null){
                nodeB.before.next = nodeA;
                nodeA.before = nodeB.before;
            }else{
                nodeA.before = null;
            }
            if(nodeA.next != null){
                nodeA.next.before = nodeB;
                nodeB.next = nodeA.next;
            }else{
                nodeB.next = null;
            }
            nodeA.next = nodeB;
            nodeB.before = nodeA;
        }//if A and B are not neighbors of each other
        else{
            if(nodeA.before != null){
                nodeA.before.next = nodeB;
            }
            if(nodeA.next != null){
                nodeA.next.before = nodeB;
            }
            Node next = nodeB.next;
            Node before = nodeB.before;
            nodeB.before = nodeA.before;
            nodeB.next = nodeA.next;
            
            if(next != null){
                next.before = nodeA;
            }
            if(before != null){
                before.next = nodeA;
            }
            nodeA.before = before;
            nodeA.next = next;
        }
    }
    
    public static void main(String args[]){
        DoubleLinkList dll = new DoubleLinkList();
        Node head = null;
        head = dll.addNode(head,1);
        head = dll.addNode(head,2);
        head = dll.addNode(head,3);
        head = dll.addNode(head,4);
        head = dll.addNode(head,5);
        SwapTwoNodesInDoubleLL snt = new SwapTwoNodesInDoubleLL();
        Node nodeA = dll.find(head, 3);
        Node nodeB = dll.find(head, 5);
        snt.swap(nodeA, nodeB);
        dll.printFrontBack(head);
    }
}
