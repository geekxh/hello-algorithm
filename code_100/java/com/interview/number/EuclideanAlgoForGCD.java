package com.interview.number;

/**
 * GCD greatest common divisor
 * Co prime numbers if GCD of both the numbers is 1
 */
public class EuclideanAlgoForGCD {

    public int gcd(int num1, int num2){
        if(num1 > num2){
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        while(num1 != 0){
            int temp = num1;
            num1 = num2 % num1;
            num2 = temp;
        }
        return num2;
    }
    
    /**
     * assumption that num1 is less than num2 as initial parameter
     */
    public int gcdRec(int num1, int num2){
        if(num1 == 0){
            return num2;
        }
        return gcdRec(num2%num1, num1);
    }
    
    public static void main(String args[]){
        EuclideanAlgoForGCD ea = new EuclideanAlgoForGCD();
        int gcd = ea.gcd(956,1044);
        if(gcd == 1){
            System.out.println("Co prime numbers");
        }else{
            System.out.println("No coprime numbers " + gcd);
        }
    }
}
