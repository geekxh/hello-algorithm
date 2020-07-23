package com.interview.bits;

/**
 * Exercise 5.2 150 qs
 */
public class RealNumberToBinary {

    public void print(double num){
        if(num > 1 || num < 0){
            System.out.println("ERROR");
            return;
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0.");
        while(num > 0){
            num = num*2;
            int r = (int)num ;
            stringBuilder.append(r);
            num = num -r;
            if(stringBuilder.length() > 32){
                System.out.println("ERROR");
                return;
            }
        }
        System.out.println(stringBuilder);
    }
    public static void main(String args[]){
        RealNumberToBinary rnb = new RealNumberToBinary();
        rnb.print(0.8125);
        rnb.print(0.72);
    }
}
