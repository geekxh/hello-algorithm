package com.interview.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class RealTimeCounter {

    private Queue<Long> secQueue = new LinkedList<Long>();
    private long secCount;
    
    public void add(long currentTimeInMills){
        while(secQueue.size() > 0 && currentTimeInMills - 1000 > secQueue.peek()){
            secCount--;
            secQueue.poll();
        }
        
        secCount++;
        secQueue.offer(currentTimeInMills);
    }
    
    public long getCallsInLastSec(long currentTimeInMills){
        while(secQueue.size() > 0 && currentTimeInMills - 1000 > secQueue.peek()){
            secCount--;
            secQueue.poll();
        }
        return secCount;
    }
    
    public static void main(String args[]){
        RealTimeCounter rtc = new RealTimeCounter();
        rtc.add(100);
        rtc.add(300);
        rtc.add(550);
        System.out.println(rtc.getCallsInLastSec(780));
        System.out.println(rtc.getCallsInLastSec(1280));
        rtc.add(1540);
        System.out.println(rtc.getCallsInLastSec(1551));
        rtc.add(1570);
        System.out.println(rtc.getCallsInLastSec(2651));
    }
}
