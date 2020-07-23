package com.interview.bits;

/**
 * http://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
 * http://www.geeksforgeeks.org/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
 */
public class NumberOccuringOddTimes {

    public int oneNumberOccuringOddTimes(int arr[]){
        int r = 0;
        for(int i=0; i < arr.length; i++){
            r = r^arr[i];
        }
        return r;
    }
    
    class Pair{
        int a;
        int b;
        
    }
    
    public Pair twoNumbersOccuringOddTimes(int arr[]){
        int r = 0;
        for(int i=0; i < arr.length; i++){
            r = r^arr[i];
        }
    
        r = r & ~(r-1);
        int r1 = 0;
        int r2 = 0;
        for(int i=0; i < arr.length; i++){
            if((r&arr[i]) == 0){
                r1 = r1^arr[i];
            }else{
                r2 = r2^arr[i];
            }
        }
        Pair p = new Pair();
        p.a = r1;
        p.b = r2;
        return p;
    }
    
    public static void main(String args[]){
        NumberOccuringOddTimes noot = new NumberOccuringOddTimes();
        int arr[] = {1,2,9,9,2,1,9,7,2,1,9,1};
        Pair p = noot.twoNumbersOccuringOddTimes(arr);
        System.out.print(p.a + " " + p.b);
    }
}

