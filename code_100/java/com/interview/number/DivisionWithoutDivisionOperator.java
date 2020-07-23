package com.interview.number;

public class DivisionWithoutDivisionOperator {

    public static class Pair {
        int remainder;
        int quotient;
    }

    public Pair divide(int number, int divisor) {
        int divident = 0;
        while (number >= divisor) {
            number -= divisor;
            divident++;
        }
        Pair p = new Pair();
        p.quotient = divident;
        p.remainder = number;
        return p;
    }
    
    public int divideRec(int number, int divisor){
        if(number < divisor){
            return 0;
        }
        
        return divideRec(number-divisor, divisor) + 1;
    }

    public Pair efficientDivide(int divident, int divisor) {
        int quotient = 1;
        int currentDivisor = divisor;
        int currentDivident = divident;
        int q = 0;
        while (divisor < divident) {
            currentDivisor = divisor;
            currentDivident = divident;
            quotient = 1;
            while (currentDivisor <= currentDivident) {
                currentDivisor *= 2;
                quotient *= 2;
            }
            currentDivisor = currentDivisor >> 1;
            quotient = quotient >> 1;
            divident = divident - currentDivisor;
            q += quotient;
        }
        Pair p = new Pair();
        p.quotient = q;
        p.remainder = divident;
        return p;
    }

    public int efficientDivideRec(int divident, int divisor){
        if(divisor > divident){
            return 0;
        }
        int tempDivisor = divisor;
        int quotient = 1;
        while(divisor <= divident){
            divisor = divisor*2;
            quotient *= 2;
        }
        divisor = divisor >> 1;
        quotient = quotient >> 1;
        return quotient + efficientDivideRec(divident - divisor, tempDivisor);
    }
    
    public static void main(String args[]) {
        DivisionWithoutDivisionOperator dd = new DivisionWithoutDivisionOperator();
        Pair p = dd.efficientDivide(95,4);
        System.out.println(p.quotient + " " + p.remainder);
        
        System.out.print(dd.efficientDivideRec(135,12));
    }
}
