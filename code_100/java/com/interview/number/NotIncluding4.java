package com.interview.number;

/**
 * http://saikatd.wordpress.com/author/saikatd/page/4/
 * In a number system where 4 is not there, how do you convert such a number system to decimal
 * Here basically we have base 9 and convert it to base 10. Just be careful when you convert
 * anything from 5 to 9 because they are basically 4 to 8 in base 9 system. So subtract them 
 * by 1 when doing multiplications.
 */
public class NotIncluding4 {

    public int number(int chinaNumber){
        
        int result = 0;
        int mul = 1;
        while(chinaNumber > 0){
            int r = chinaNumber % 10;
            chinaNumber /= 10;
            if(r == 4){
                throw new IllegalArgumentException();
            }
            if(r >=5){
                r--;
            }
            result += r*mul;
            mul = mul*9;
        }
        return result;
    }
    
    public static void main(String args[]){
        NotIncluding4 ni = new NotIncluding4();
        System.out.print(ni.number(16));
    }
}
