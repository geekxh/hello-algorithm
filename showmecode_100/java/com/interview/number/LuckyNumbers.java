package com.interview.number;

/**
 * http://www.geeksforgeeks.org/lucky-numbers/
 */
public class LuckyNumbers {

    public boolean isLuck(int n,int counter){
        if(n < counter){
            return true;
        }
        if(n % counter == 0){
            return false;
        }
        
        return isLuck( n - n/counter,counter+1);
    }
    
    public static void main(String args[]){
        LuckyNumbers ln = new LuckyNumbers();
        System.out.println(ln.isLuck(19, 2));
    }
    
}
