package com.interview.bits;

/**
 * http://www.geeksforgeeks.org/count-set-bits-in-an-integer/
 */
public class CountBits {

    public CountBits(){
        preCalculate();
    }
    public int countBits(int num){
        int count=0;
        while(num > 0){
            num &= num-1;
            count++;
        }
        return count;
    }
    
    private int count[] = new int[256];
    
    void preCalculate(){
        for(int i=0; i < 256; i++){
            count[i] = countBits(i);
        }
    }
    
    public int countBitsFaster(int num){
        //get 8 bits at a time and check count from count array
        int total = 0;
        int mask = (1<<8) - 1;
        for(int i=0 ; i < 4; i++){
            total += count[num & mask];
            num = num>>>8;
        }
        return total;
    }
    
    //http://bits.stephan-brumme.com/countBits.html
    public int countBitsEvenFaster(int x){
        // count bits of each 2-bit chunk
        x = x - ((x >> 1) & 0x55555555);
        // count bits of each 4-bit chunk
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
        // count bits of each 8-bit chunk
        x = x + (x >> 4);
        // mask out junk
        x &= 0xF0F0F0F;
        // add all four 8-bit chunks
        return (x * 0x01010101) >> 24;
    }

    public static void main(String args[]){
        CountBits cb = new CountBits();
        System.out.println(cb.countBits(3636363));
        System.out.println(cb.countBitsFaster(3636363));
        System.out.println(cb.countBitsEvenFaster(3636363));
    }
}
