package com.interview.dynamic;

/**
 * Date 03/28/2015
 * @author tusroy
 * 
 * Fibonacci series
 * Given a number find the fibonacci series value for that number
 * e.g n = 3 -> 3
 *     n = 4 -> 5
 *     n = 5 -> 8
 *  
 * Solution
 * Recursively it can calculated very easily by f(n) = f(n-1) + f(n-2)
 * For Dp version we do not recalculate f(n-1) and f(n-2) but keep it in a and b
 * 
 * Test cases
 * 1) Negative number
 * 2) 0 
 * 3) 1
 * 4) Very high number
 * 
 */
public class FibonacciSeries {

	 /**
     * DP version where we do not recalculate values but just keep last 2
     * calculate values
     */
    public int fibonacciSeries(int n){
        int n1 = 0, n2 = 1;
        int sum;

        if (n == n1 || n == n2) {
            return n;
        }

        for(int i=2; i <= n; i++){
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return n2;
    }
    
   
    /**
     * Recursive and slow version. Recalculates same value over and over again.
     * Chokes for n greater than 60
     */
    public int fibonacciSeriesRecursive(int n){
        if(n == 1 || n == 0){
            return n;
        }
        return fibonacciSeriesRecursive(n-1) + fibonacciSeriesRecursive(n-2);
    }
    
    public static void main(String args[]){
        FibonacciSeries fs = new FibonacciSeries();
        System.out.println(fs.fibonacciSeries(15));
        System.out.println(fs.fibonacciSeriesRecursive(15));
    }
    
}
