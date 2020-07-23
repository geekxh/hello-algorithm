package com.interview.number;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/generate-unique-partitions-of-an-integer/
 * Test cases:
 * 0 or negative number
 */
public class UniquePartitionOfInteger {

    public void partition(int n){
        List<Integer> result = new ArrayList<Integer>();
        partition(n,n,result);
    }
    
    private void partition(int n, int max,List<Integer> result){
        if(n < 0){
            return ;
        }
        if(n == 0){
            result.forEach(i -> System.out.print(i + " "));
            System.out.println();
            return;
        }
        for(int i=Math.min(n, max); i > 0 && i <= max; i--){
            result.add(i);
            partition(n-i,i, result);
            result.remove(result.size()-1);
        }
   }
    
    public static void main(String args[]){
        UniquePartitionOfInteger upi = new UniquePartitionOfInteger();
        upi.partition(12);
    }
}
