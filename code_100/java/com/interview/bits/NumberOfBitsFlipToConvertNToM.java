package com.interview.bits;

/**
 * Exercise 5.5 150 qs
 */
public class NumberOfBitsFlipToConvertNToM {

    public int number(int m, int n){
        int r = n^m;
        int count = 0;
        while(r != 0){
            r = r & (r-1);
            count++;
        }
        return count;
    }
    public static void main(String args[]){
        NumberOfBitsFlipToConvertNToM nb = new NumberOfBitsFlipToConvertNToM();
        System.out.println(nb.number(31, 14));
    }
}
