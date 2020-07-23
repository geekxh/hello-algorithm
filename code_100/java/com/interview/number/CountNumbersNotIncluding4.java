package com.interview.number;

/**
 * http://www.geeksforgeeks.org/count-numbers-that-dont-contain-3/
 */
public class CountNumbersNotIncluding4 {

    public int count(int n){
        if(n < 4){
            return n;
        }
        if( n >=4 && n <=10){
            return n-1;
        }
        
        int pow = 1;
        while(n/pow > 9){
            pow = pow*10;
        }
        
        int msd = n/pow;
        if(msd == 4){
            return count(msd*pow -1);
        }else{
            //suppose number is 276. So this becomes count(2)*count(99) +
            //count(2) + count(76)
            //reason we split this way rather than count(2)*count(100) is because
            //count(100) can go into infinite loop
            return count(msd)*count(pow-1) + count(msd) + count(n%pow);
        }
    }
    
    public static void main(String args[]){
        CountNumbersNotIncluding4 cn = new CountNumbersNotIncluding4();
        int c = cn.count(44);
        System.out.print(c);
    }
}
