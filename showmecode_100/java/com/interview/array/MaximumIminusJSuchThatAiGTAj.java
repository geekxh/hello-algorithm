package com.interview.array;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 */
public class MaximumIminusJSuchThatAiGTAj {

    class Node{
        int index;
        int size;
    }
    
    public int maximumGeeks(int input[]){
        int lhs[] = new int[input.length];
        int rhs[] = new int[input.length];
        lhs[0] = 0;
        for(int i=1; i < lhs.length; i++){
            if(input[lhs[i-1]] < input[i]){
                lhs[i] = lhs[i-1];
            }else{
                lhs[i] = i;
            }
        }
        rhs[input.length-1] = input.length-1;
        for(int i=input.length-2; i >= 0; i--){
            if(input[rhs[i+1]] > input[i]){
                rhs[i] = rhs[i+1];
            }else{
                rhs[i] = i;
            }
        }
        
        int i=0;
        int j=0;
        int max = 0;
        for(;j < input.length;){
            if(input[lhs[i]] < input[rhs[j]]){
                max = Math.max(max, j-i);
                j++;
            }else{
                i++;
            }
        }
        return max;
    }
    
    public static void main(String args[]){
        MaximumIminusJSuchThatAiGTAj mj = new MaximumIminusJSuchThatAiGTAj();
        int input[] = {11,14,13,1,4,13,1,10};
        System.out.println(mj.maximumGeeks(input));
    }

}
