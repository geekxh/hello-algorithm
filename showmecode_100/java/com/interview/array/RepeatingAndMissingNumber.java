package com.interview.array;

/**
 * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
 */
public class RepeatingAndMissingNumber {

    class Pair{
        int repeating;
        int missing;
        public String toString(){
            return repeating + " " + missing;
        }
    }
    
    public Pair findNumbers(int input[]){
        Pair p = new Pair();
        for(int i=0; i < input.length; i++){
            if(input[Math.abs(input[i])-1] < 0){
                p.repeating = Math.abs(input[i]);
            }else{
                input[Math.abs(input[i])-1] = -input[Math.abs(input[i])-1];
            }
        }
    
        for(int i=0; i < input.length; i++){
            if(input[i] < 0){
                input[i] = -input[i];
            }else{
                p.missing = i + 1;
            }
        }
        return p;
    }
    
    public static void main(String args[]){
        RepeatingAndMissingNumber rmn = new RepeatingAndMissingNumber();
        int input[] = {3,1,2,4,6,8,2,7};
        System.out.println(rmn.findNumbers(input));
    }
}
