package com.interview.stackqueue;

import java.util.Deque;
import java.util.LinkedList;

public class RealTimeCounterUsingCircularQueue {

    class Node {
        long time;
        int count;
    }

    private static int MAX_SIZE = 0;
    private static int BOUND = 100;
    private static int MAX_BOUND = 1000;
    int count = 0;
    private int currentSize = 0;

    Deque<Node> queue = new LinkedList<Node>();

    public void add(long time) {
        Node last = queue.peekLast();
        if(last != null){
            if (time - last.time < BOUND) {
                last.count++;
                count++;
                return;
            } else if (time - last.time > MAX_BOUND) {
                queue.clear();
                Node n = new Node();
                n.time = time/BOUND * BOUND;
                n.count = 1;
                queue.add(n);
                count++;
                return;
            }
        }
        while(queue.size() > 0){
            Node t1 = queue.peekFirst();
            if(time - t1.time > MAX_BOUND){
                count = count - t1.count;
                queue.pollFirst();
            }else{
                break;
            }
        }
    
        Node n = new Node();
        n.time = time/BOUND * BOUND;
        n.count = 1;
        queue.add(n);
        count++;
    }

    public int getCount(int time){
        while(queue.size() > 0){
            Node t1 = queue.peekFirst();
            if(time - t1.time > MAX_BOUND){
                count = count - t1.count;
                queue.pollFirst();
            }else{
                break;
            }
        }
        return count;
    }
    
    public static void main(String args[]){
        RealTimeCounterUsingCircularQueue src = new RealTimeCounterUsingCircularQueue();
        src.add(10);
        src.add(70);
        src.add(80);
        src.add(120);
        src.add(150);
        src.add(450);
        src.add(750);
        src.add(799);
        src.add(1001);
        src.add(1010);
        src.add(1210);
        System.out.print(src.getCount(1515));
    }
}
