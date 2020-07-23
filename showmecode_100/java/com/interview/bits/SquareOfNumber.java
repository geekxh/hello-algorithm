package com.interview.bits;

/**
 * http://www.careercup.com/question?id=5748016728244224
 * Square of number without using * or ^ operation.
 * Test cases
 * How to deal with negative number
 * 0
 * Power of 2 number
 * Regular numbers
 */
public class SquareOfNumber {

    public int square(int n){
        if(n < 0){
            n = -n;
        }
        int result = 0;
        int k = n;
        //so we doing 7*7. First lets get rid of 3 from that 7 by below operation.
        //Once we are left with 4 then it all about using >> and <<
        while((k & (k-1))!= 0){
            result += n;
            k--;
        }
        
        while(k > 1){
            n = n<<1;
            k = k>>1;
        }
        return result + n;
    }
    
    public int fastSquare(int n){
        return fastSquareRec(n, n);
    }
    /**
     * Start with 9,9. Then take 1 and keep left shifting 1 till you find number
     * less than 9 but power of 2. Then shift 9 by that many powers and repeat
     * the process with whatever is left between that number and 9.
    */
    private int fastSquareRec(int n, int leftToMultiply){
        if(leftToMultiply <= 0){
            return 0;
        }
        int k = 1; 
        int count=0;
        while(k <= leftToMultiply){
            k = k<<1;
            count++;
        }
        k = k>>1;
        count--;
        return (n<<count) + fastSquareRec(n, leftToMultiply- k);
    }
    
    public static void main(String args[]){
        SquareOfNumber sn = new SquareOfNumber();
        System.out.println(sn.square(9) + " " + sn.fastSquare(9));
        System.out.println(sn.square(27) + " " + sn.fastSquare(27));
        System.out.println(sn.square(18) + " " + sn.fastSquare(18));
        System.out.println(sn.square(199) + " " + sn.fastSquare(199));
    }
}