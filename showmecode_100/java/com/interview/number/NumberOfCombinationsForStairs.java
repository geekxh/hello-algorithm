package com.interview.number;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * A man is walking up a set of stairs. He can either take 1 or 2 steps at a time. 
 * Given n number of steps,
 * find out how many combinations of steps he can take to reach the top of the stairs.
 * Its like building a fibonaaci series but instead of looking back 2 you have to 
 * look back k elements
 */
public class NumberOfCombinationsForStairs {

    /**
     * Assumption is k is always greater than or equal to 2
     */
    public int numberOfWays(int n,int k){
        if(k < 2){
            throw new IllegalArgumentException();
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        int count=0;
        for(int i=2; i < k ; i++){
            Iterator<Integer> itr = queue.iterator();
            count = 0;
            while(itr.hasNext()){
                count += itr.next();
            }
            queue.offerLast(count);
        }
        for(int i = k; i < n ; i++){
            Iterator<Integer> itr = queue.iterator();
            count = 0;
            while(itr.hasNext()){
                count += itr.next();
            }
            queue.pollFirst();
            queue.offerLast(count);
        }
        return queue.pollLast();
    }
    
    public static void main(String args[]){
        NumberOfCombinationsForStairs noc = new NumberOfCombinationsForStairs();
        System.out.println(noc.numberOfWays(7, 4));
    }
}
