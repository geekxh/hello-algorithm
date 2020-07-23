package com.interview.bits;

/**
 * Exercise 5.3 150 qs
 */
public class NextHigherAndNextLowerWithSameNumberBits {

    public int nextHigher(int n){
        int i = 1;
        int first1 = 0;
        //go till you find first 1
        while((n & i) == 0){
            i = i << 1;
            first1++;
        }
        //count number of 1s before first 0
        int count1s = 0;
        while((n & i) > 0){
            i = i <<1;
            count1s++;
        }
        count1s--;
        //change this first 0 after 1 to 1
        n = n^i;
        n = n ^ (i>>1);
        int mask = ~(1<<(first1 + count1s) -1);
        n = mask & n;
        n = n | ((1<<count1s) -1);
        return n;
    }
    
    public int nextLower(int n){
        if(n ==0){
            return 0;
        }
        int i = 1;
        int first0 = 0;
        while((n & i) > 0){
            i = i << 1;
            first0++;
        }
        int count0s = 0;
        while((n & i) == 0){
            i = i <<1;
            count0s++;
        }
        count0s--;
        //change this first 0 after 1 to 1
        n = n^i;
        n = n ^ (i>>1);
        int mask = (1<<first0) -1;
        n = n ^ mask;
        n = n | (mask<<count0s);
        return n;
        
    }
    public static void main(String args[]){
        NextHigherAndNextLowerWithSameNumberBits nhn = new NextHigherAndNextLowerWithSameNumberBits();
        System.out.println(nhn.nextHigher(94));
        System.out.println(nhn.nextLower(10));
    }
}
