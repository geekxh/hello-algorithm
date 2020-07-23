package com.interview.misc;

/**
 * Convert a roman number to decimal number
 * Test cases: smaller number appearing before bigger number unless it is representing one less. 
 * VL is incorrect since XL should be used to represent 90
 * chars not used in roman representation is used
 * http://www.onlineconversion.com/roman_numerals_advanced.htm
 *
 */
public class RomanNumberToDecimal {

    public String converToRoman(int decimal){
        StringBuffer buffer = new StringBuffer();
        while(decimal > 0){
            decimal = literal(decimal,buffer);
        }
        return buffer.toString();
    }
    
    public int convertToDecimal(char[] roman){
        int decimal = 0;
        for(int i=0; i < roman.length; ){
            if(i < roman.length-1 && literal(roman[i]) < literal(roman[i+1])){
                decimal += literal(roman[i+1]) - literal(roman[i]);
                i += 2;
            }else{
                decimal += literal(roman[i]);
                i++;
            }
        }
        return decimal;
    }
    
    private int literal(int decimal,StringBuffer buffer){
        if(decimal >= 1000){
            buffer.append("M");
            decimal -= 1000;
            return decimal;
        }
        else if(decimal >= 900){
            buffer.append("CM");
            decimal -= 900;
            return decimal;
        }
        else if(decimal >= 500){
            buffer.append("D");
            decimal -= 500;
            return decimal;
        }
        else if(decimal >= 400){
            buffer.append("CD");
            decimal -= 400;
            return decimal;
        }
        else if(decimal >= 100){
            buffer.append("C");
            decimal -= 100;
            return decimal;
        }
        else if(decimal >= 90){
            buffer.append("XC");
            decimal -= 90;
            return decimal;
        }
        else if(decimal >= 50){
            buffer.append("L");
            decimal -= 50;
            return decimal;
        }
        else if(decimal >= 40){
            buffer.append("XL");
            decimal -= 40;
            return decimal;
        }
        else if(decimal >= 10){
            buffer.append("X");
            decimal -= 10;
            return decimal;
        }else if(decimal >= 9){
            buffer.append("IX");
            decimal -= 9;
            return decimal;
        }
        else if(decimal >=5){
            buffer.append("V");
            decimal -= 5;
            return decimal;
        }else if(decimal >= 4){
            buffer.append("IV");
            decimal -= 4;
            return decimal;
        }else {
            buffer.append("I");
            decimal -= 1;
            return decimal;
        }
    }
    
    private int literal(char ch){
        switch(ch){
        case 'I' :  
            return 1;
        case 'V' :
            return 5;
        case 'X' :
            return 10;
        case 'L' :
            return 50;
        case 'C' :
            return 100;
        case 'D' :
            return 500;
        case 'M' :
            return 1000;
        default :
            throw new IllegalArgumentException();
        }
    }
    
    public static void main(String args[]){
        RomanNumberToDecimal rnd = new RomanNumberToDecimal();
        System.out.println(rnd.convertToDecimal("XX".toCharArray()));
        System.out.println(rnd.convertToDecimal("XCIX".toCharArray()));
        System.out.println(rnd.convertToDecimal("MLXIX".toCharArray()));
        System.out.println(rnd.convertToDecimal("MMDXLIII".toCharArray()));
        
        System.out.println(rnd.converToRoman(20));
        System.out.println(rnd.converToRoman(99));
        System.out.println(rnd.converToRoman(1069));
        System.out.println(rnd.converToRoman(2543));
    }
}
