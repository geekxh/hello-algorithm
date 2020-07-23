package com.interview.misc;

/**
 * Convert a number into words
 *
 * Reference
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class NumberToWord {

    private static int BILLION = 1000000000;
    private static int MILLION = 1000000;
    private static int THOUSAND = 1000;
    
    public String numberToWords(int number){
        StringBuffer buffer = new StringBuffer();
        if(number ==0){
            return toString(number).trim();
        }
        if(number < 0){
            buffer.append("Minus");
            number = number *-1;
        }
        if (number >= BILLION) {
            int first = number / BILLION;
            number = number % BILLION;
            buffer.append(hundredsPart(first)).append(toString(BILLION));
        }
        if(number >= MILLION){
            int first = number /MILLION;
            number = number % MILLION;
            buffer.append(hundredsPart(first)).append(toString(MILLION));
        }
        if(number >= THOUSAND){
            int first = number /THOUSAND;
            number = number % THOUSAND;
            buffer.append(hundredsPart(first)).append(toString(THOUSAND));
        }
        buffer.append(hundredsPart(number));
        return buffer.toString().trim();
    }
    
    private String hundredsPart(int number){
        StringBuffer buffer = new StringBuffer();
        if(number != 0){
            if(number <= 99){
                buffer.append(tenthPart(number));
            }else{
                int first = (number /100);
                int second = number % 100;
                buffer.append(toString(first) + toString(100));
                if (second != 0){
                    buffer.append(tenthPart(second));
                }
            }
            return buffer.toString();
        }else{
            return "";
        }
    }
        
    private String tenthPart(int number){
        StringBuffer buffer = new StringBuffer();
        if(number != 0){
            if(number <= 19){
                buffer.append(toString(number));
            }else{
                int first = (number/10)*10;
                int second = number%10;
                buffer.append(toString(first));
                if (second != 0){
                    buffer.append(toString(second));
                }
            }
            return buffer.toString();
        }else{
            return "";
        }
    }
    
    private String toString(int number){
        
        switch(number){
            case 0:
                return " Zero";
            case 1:
                return " One";
            case 2:
                return " Two";
            case 3:
                return " Three";
            case 4:
                return " Four";
            case 5:
                return " Five";
            case 6:
                return " Six";
            case 7:
                return " Seven";
            case 8:
                return " Eight";
            case 9:
                return " Nine";
            case 10:
                return " Ten";
            case 11:
                return " Eleven";
            case 12:
                return " Twelve";
            case 13:
                return " Thrirteen";
            case 14:
                return " Fourteen";
            case 15:
                return " Fifteen";
            case 16:
                return " Sixteen";
            case 17:
                return " Seventeen";
            case 18:
                return " Eighteen";
            case 19:
                return " Nineteen";
            case 20:
                return " Twenty";
            case 30:
                return " Thirty";
            case 40:
                return " Forty";
            case 50:
                return " Fifty";
            case 60:
                return " Sixty";
            case 70:
                return " Seventy";
            case 80:
                return " Eighty";
            case 90:
                return " Ninety";
            case 100:
                return " Hundred";
            case 1000:
                return " Thousand";
            case 1000000:
                return " Million";
            case 1000000000:
                return " Billion";
            default:
                throw new IllegalArgumentException();
            }
    }
    
    public static void main(String args[]){
        NumberToWord ntw = new NumberToWord();
        System.out.println(ntw.numberToWords(8000));
        System.out.println(ntw.numberToWords(8192));
        System.out.println(ntw.numberToWords(8112));
        System.out.println(ntw.numberToWords(504));
        System.out.println(ntw.numberToWords(565100));
        System.out.println(ntw.numberToWords(6721157));
        System.out.println(ntw.numberToWords(-106721157));
        System.out.println(ntw.numberToWords(106070571));
        System.out.println(ntw.numberToWords(-92));
        System.out.println(ntw.numberToWords(0));
        System.out.println(ntw.numberToWords(1212121451));

    }
}
