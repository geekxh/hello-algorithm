package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
 * Test cases:
 * Delete middle with 1,2,3 element
 * Pop with 1,2,3 elements
 * Delete or pop when empty stack
 */
public class StackWithLinkListMiddleOperation {
    
    private Node head = null;
    private Node middle = null;
    private int size = 0;
    public void push(int data){
        if(head == null){
            head = Node.newNode(data);
            middle = head;
            size++;
            return;
        }
        size++;
        Node node = Node.newNode(data);
        node.next = head;
        head.before = node;
        head = node;
        if(size % 2 ==0){
            middle = middle.before;
        }
    }
    
    public boolean hasMore(){
        if(size > 0 ){
            return true;
        }else{
            return false;
        }
    }
    
    public int size(){
        return size;
    }
    
    public int pop(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        size--;
        if(size % 2 !=  0 || size == 0){
            middle = middle.next;
        }
        int data = head.data;
        head = head.next;
        head.before = null;
        return data;
    }
    public int top(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        return head.data;
    }
    public int middle(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        return middle.data;
    }
    public int deleteMiddle(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        size--;
        if(middle == head){
            int data = middle.data;
            middle = middle.next;
            head = head.next;
            if(head != null){
                head.before = null;
            }
            return data;
        }
        
        if(size % 2 == 0){
            int data = middle.data;
            Node next = middle.next;
            middle = middle.before;
            middle.next = next;
            if(next != null){
                next.before = middle;
            }
            return data;
        }
        else{
            int data = middle.data;
            Node before = middle.before;
            middle = middle.next;
            middle.before = before;
            if(before != null){
                before.next = middle;
            }
            return data;
        }
    }
    
    public static void main(String args[]){
        StackWithLinkListMiddleOperation swl = new StackWithLinkListMiddleOperation();
        swl.push(1);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(2);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(3);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(4);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(5);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(6);
        System.out.println(swl.top() + " " + swl.middle());
        System.out.println("\n\n");
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
        System.out.println(swl.top() + " " + swl.middle());
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
        System.out.println(swl.top() + " " + swl.middle());
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
    }
}
