package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/check-if-each-internal-node-of-a-bst-has-exactly-one-child/
 */
public class BSTOneChildPreOrderTraversal {

    public boolean isBST(int input[]){
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < input.length-1; i++){
            if(input[i] > min && input[i] < max){
                if(input[i+1] < input[i]){
                    max = input[i];
                }else{
                    min = input[i];
                }
            }else{
                return false;
            }
        }
        if(input[input.length-1] < max && input[input.length-1] > min){
            return true;
        }else{
            return false;
        }
    }
    
    public static void main(String args[]){
        int input[] = {20,10,14,15,17};
        BSTOneChildPreOrderTraversal boc = new BSTOneChildPreOrderTraversal();
        System.out.println(boc.isBST(input));
    }
}
