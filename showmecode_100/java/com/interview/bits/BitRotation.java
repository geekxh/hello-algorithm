package com.interview.bits;

/*
 * http://www.geeksforgeeks.org/rotate-bits-of-an-integer/
 */
public class BitRotation {

    public byte rotateLeft(byte num, int d){
        return (byte)((num << d) | (num >>> (8-d)));
    }
    
    public static void main(String args[]){
        BitRotation br = new BitRotation();
        System.out.println(br.rotateLeft((byte)28, 2));
    }
}
