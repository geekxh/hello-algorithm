package com.interview.linklist;

class NodeRef{
    Node node;
    public void next(){
        node = node.next;
    }
}

class Node{
    int data;
    Node next;
    Node before;
    Node child;
    Object obj;
    
    public static Node newNode(int data, Object... obj){
        Node n = new Node();
        n.data = data;
        n.next = null;
        n.before = null;
        if(obj.length > 0)
        {    
            n.obj = obj[0];
        }
        return n;
    }
}

public class LinkList {
    
    public Node addNode(int data, Node head, Object... obj){
        Node temp = head;
        Node n = null;
        if(obj.length > 0){
            n = Node.newNode(data, obj[0]);
        }else{
            n = Node.newNode(data);
        }
        if(head == null){
            return n;
        }
        
        while(head.next != null){
            head = head.next;
        }
        
        head.next = n;
        return temp;
    }
    
    public Node addAtFront(Node node, Node head){
        if(head == null){
            return node;
        }
        node.next = head;
        return node;
    }
    
    public Node reverse(Node head){
        Node front = null;
        Node middle = head;
        Node end = null;
        while(middle != null){
            end = middle.next;
            middle.next = front;
            front = middle;
            middle = end;
        }
        return front;
    }

    public Node reverseRecursiveEasy(Node head){
        if(head == null || head.next == null){
            return head;
        }
        
        Node reversedList = reverseRecursiveEasy(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }
    
    public void reverseRecursive(NodeRef headRef){
        if(headRef.node == null){
            return;
        }
        Node first = headRef.node;
        Node last = headRef.node.next;
        if(last == null){
            return;
        }
        NodeRef lastHeadRef = new NodeRef();
        lastHeadRef.node = last;
        reverseRecursive(lastHeadRef);
        first.next.next = first;
        first.next = null;
        headRef.node = lastHeadRef.node;
    }
    
    public Node addAtFront(int data, Node head){
        Node node = Node.newNode(data);
        return addAtFront(node,head);
    }
    
    public void printList(Node head){
        while(head != null){
            System.out.println(head.data);
            head = head.next;
        }
    }
    
    public Node find(Node head, int data){
        while(head != null){
            if(head.data == data){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    
    
    public int size(Node head){
        int size =0;
        while(head != null){
            size++;
            head = head.next;
        }
        return size;
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
        ll.printList(head);
        
    //  NodeRef headRef = new NodeRef();
    //  headRef.node = head;
    //  ll.reverseRecursive(headRef);
    //  head = headRef.node;
    //  ll.printList(head);
        System.out.println();
        head = ll.reverseRecursiveEasy(head);
        ll.printList(head);
    }
}
