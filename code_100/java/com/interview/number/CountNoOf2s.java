package com.interview.number;

/**
 * 150 qs 18.4
 */
public class CountNoOf2s {

    public int count2s(int n){
        if(n < 2){
            return 0;
        }else if(n <= 9){
            return 1;
        }
        int pow = 1;
        while(pow <= n){
            pow *= 10;
        }
        pow = pow/10;
        
        if(n/pow == 2){
            //e.g 2105 becomes 1 + 105 + count2s(105) + count2s(1999)
            return 1 + n%pow + count2s(n%pow) + count2s((n/pow)*pow -1);
        }else{
            //e.g 3856 becomes 1000*count2s(3) + count2s(856) + 3*counts(999)
            return pow*count2s(n/pow) + count2s(n%pow) + (n/pow)*count2s(pow-1);
        }
    }
    
    public static void main(String args[]){
        CountNoOf2s cn2 = new CountNoOf2s();
        System.out.println(cn2.count2s(255));
    }
    
}
