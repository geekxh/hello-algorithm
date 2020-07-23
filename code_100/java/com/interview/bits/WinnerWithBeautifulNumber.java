package com.interview.bits;

/**
 * http://www.careercup.com/question?id=5399897561890816
 * Idea is simple. Count the number of times it takes to swap 0s such that all
 * 1's are at least significant position.
 * e.g 1010 -> 1001 -> 101 -> 11
 *     1010  -> 110 -> 101 -> 11
 *     
 * No matter which route you take it leads to same result so just looking at swaps you can say
 * which player will win
 *     
 */
public class WinnerWithBeautifulNumber {

    public int winner(int n){
        int sum = 0;
        int i =1;
        int result = 0;
        while( i <= n){
            i = i*2;
        }
        i = i/2;
        while(i > 0){
            if((n & i) != 0){
                sum++;
            }else{
                result += sum;
            }
            i = i/2;
        }
        if(result % 2 == 0){
            return 2;
        }else{
            return 1;
        }
    }
    
    public static void main(String args[]){
        WinnerWithBeautifulNumber wwb = new WinnerWithBeautifulNumber();
        System.out.println(wwb.winner(37));
        System.out.println(wwb.winner(10));
    }
}
