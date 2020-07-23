package com.interview.number;

/**
 * Number such that 11 + 11 = 22, 11 +22 = 33, 22 +33 = 55
 * so 1111223355 is aggregate number
 */
public class AggregateNumber {

    //assumption is that numbers in array are single digit non-negative number(0..9)
    public boolean isAggregate(int[] number){
        if(number.length < 3){
            return false;
        }
        int prev = 0;
        for(int i=1; i <= number.length/2; i++){
            for(int j=0; j <i; j++){
                int num1 = getNumber(number, prev, j);
                int num2 = getNumber(number,j+1,i);
                int start = i+1;
                boolean flag = false;
                int k = start;
                for(; k < number.length; k++){
                    int num3 = getNumber(number, start, k);
                    if(num1 + num2 < num3){
                        break;
                    }
                    if(num1 + num2 == num3){
                        num1 = num2;
                        num2 = num3;
                        flag = true;
                        start = k+1;
                    }else{
                        flag = false;
                    }
                }
                if(k == number.length && flag){
                    return true;
                }
                flag = false;
            }
        }
        return false;
    }
    
    private int getNumber(int[] number, int start, int end){
        int total = 0;
        for(int i = start; i <= end; i++){
            total *= 10;
            total += number[i];
        }
        return total;
    }
    
    public static void main(String args[]){
        AggregateNumber an = new AggregateNumber();
        int number[] = {1,1,2,2,3,3,5,5,8,8,1,4,3}; //true 11 + 22 == 33(+22) = 55(+33) = 88(+55) = 143
        int number1[] = {1,1,2,2,3,3,5,5,8,8,1,4,4};//false 11 + 22 == 33(+55) = 88(+55) != 144 
        int number2[] = {1,1,2,2,3,2,3,4}; //true 11 + 223 = 234
        int number3[] = {1,1,2,2,3,2,3,4,4,5,7}; //true 11 + 223 = 234(+223)=457
        System.out.println(an.isAggregate(number));
        System.out.println(an.isAggregate(number1));
        System.out.println(an.isAggregate(number2));
        System.out.println(an.isAggregate(number3));
    }
}
