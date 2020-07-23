package com.interview.number;

/**
 * Date 03/14/2015
 * @author tusroy

 * Find factorial of very large number like 100.
 * 
 * Solution 
 * Since result will be very large it cannot fit in long. We need to store result in array
 * and do regular multiplication of result in array with next number of factorial.
 * Result is randomly initialized with size 500. Better would be to use list which grows
 * dynamically.
 * 
 * Test cases:
 * Negative number - should throw exception for negative number
 * 0 
 * large number
 * 
 * Reference
 * http://www.geeksforgeeks.org/factorial-large-number/
 */
public class FactorialOfLargeNumber {

    public int calculate(int result[], int n){
        assert n >= 0;
        result[0] = 1;
        int size = 1;
        for(int i=2; i <= n; i++){
            size = multiply(result, i, size);
        }
        return size;
    }
    
    private int multiply(int result[], int x, int size){
        
        int carry = 0;
        int prod = 0;
        int i=0;
        for(; i < size; i++){
            prod = result[i]*x + carry;
            result[i] = prod%10;
            carry = prod/10;
        }
        
        while(carry != 0){
            result[i] = carry%10;
            carry = carry/10;
            i++;
        }
        
        return i;
    }
    
    public static void main(String args[]){
        FactorialOfLargeNumber fol = new FactorialOfLargeNumber();
        int result[] = new int[500];
        int size = fol.calculate(result, 126);
        for(int i=size-1; i >=0; i--){
            System.out.print(result[i]);
        }
    }
}
