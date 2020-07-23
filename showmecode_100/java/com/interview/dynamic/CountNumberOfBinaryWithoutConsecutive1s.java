package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
 * It is really a straight up fibonacci series with values
 * 1,2,3,5,8,13....
 * Look how we assign a[i] value of a[i-1] + b[i-1] and then b[i] becomes a[i]
 */
public class CountNumberOfBinaryWithoutConsecutive1s {

    public int count(int n){
        int a[] = new int[n];
        int b[] = new int[n];
        
        a[0] = 1;
        b[0] = 1;
        
        for(int i=1; i < n; i++){
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }
        
        return a[n-1] + b[n-1];
    }
    
    public int countSimple(int n){
        int a = 1;
        int b = 1;
        
        for(int i=1; i < n; i++){
            int tmp = a;
        	a = a + b;
            b = tmp;
        }
        
        return a + b;
    }
    
    public static void main(String args[]){
        CountNumberOfBinaryWithoutConsecutive1s cnb = new CountNumberOfBinaryWithoutConsecutive1s();
        System.out.println(cnb.count(5));
    }
}
