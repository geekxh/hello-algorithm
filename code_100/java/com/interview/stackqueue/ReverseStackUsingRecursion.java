package com.interview.stackqueue;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 */
public class ReverseStackUsingRecursion {

    public void reverse(Deque<Integer> stack){
        if(stack.size() == 0){
            return;
        }
        int temp = stack.pollFirst();
        reverse(stack);
        
        pushAtBottom(stack,temp);
    }
    
    private void pushAtBottom(Deque<Integer> stack,int data){
        if(stack.size() == 0){
            stack.offerFirst(data);
            return;
        }
        int temp = stack.pollFirst();
        pushAtBottom(stack, data);
        stack.offerFirst(temp);
    }
    
    public static void main(String args[]){
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        
        Iterator<Integer> itr =  stack.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        ReverseStackUsingRecursion rsu = new ReverseStackUsingRecursion();
        rsu.reverse(stack);
        
        itr =  stack.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
    }
    
}
