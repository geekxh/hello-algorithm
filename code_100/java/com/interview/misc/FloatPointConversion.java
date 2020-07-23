package com.interview.misc;

import java.util.StringTokenizer;

/**
 * http://www.careercup.com/question?id=4901629824335872
 * e.g
 * -99.01E-2 -> -.9901
 * 845.67E2  -> 84567.0 
 */
public class FloatPointConversion {
    
    public double convert(String input){
        boolean isNeg = false;
        if(input.startsWith("-")){
            isNeg = true;
            input = input.substring(1);
        }
        StringTokenizer strtk = new StringTokenizer(input,"E");
        String number = strtk.nextToken();
        String power = strtk.nextToken();
        double dNumber = toDouble(number);
        double dPower = toDouble(power);
        double pow = Math.pow(10, dPower);
        double result = dNumber * pow;
        return isNeg ? result * -1 : result;
    }
    
    private double toDouble(String number){
        boolean isNeg = false;
        if(number.startsWith("-")){
            isNeg = true;
            number = number.substring(1);
        }
        int result = 0;
        int pow = 1;
        boolean foundDot = false;
        for(char ch : number.toCharArray()){
            if(ch == '.'){
                foundDot = true;
                continue;
            }
            if(foundDot){
                pow = pow*10;
            }
            result *= 10;
            result += (ch - '0');
        }
        
        double doubleResult = (result*1.0)/pow;
        
        return isNeg ? doubleResult*-1 : doubleResult;
    }
    
    public static void main(String args[]){
        FloatPointConversion fpc = new FloatPointConversion();
        System.out.println(fpc.convert("-99.01E-2"));
        System.out.println(fpc.convert("845.67E2"));
        System.out.println(fpc.convert("99.01E-1"));
    }
    
}
