package com.interview.number;

/**
 * Babylonian method for calculating square root
 */
public class SquareRoot {

    double findRoot(int num){
        double start =0;
        double end = num;
        while(Math.abs(start - end) > 0.01){
            end = (start + end)/2;
            start = num/end;
        }
        
        return end;
    }
    
    public static void main(String args[]){
        SquareRoot sr = new SquareRoot();
        System.out.println(sr.findRoot(144));
    }
}
