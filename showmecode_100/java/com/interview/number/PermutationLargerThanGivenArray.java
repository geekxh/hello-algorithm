package com.interview.number;

import java.util.Arrays;

/**
 * e.g if src array is {1,2,5,7,8} and dest is {5,8,1,9,8}
 * permute src in such a way that it is greater than dest but diff is minimal
 * so here we will have {5,8,2,1,7}
 *
 */
public class PermutationLargerThanGivenArray {

    public int[] findBiggerNumber(int src[],int dest[]){
        int result[] = new int[src.length];
        boolean[] used = new boolean[src.length];
        boolean r = findNumber(src, dest, result, 0, used);
        if(!r){
            return null;
        }
        return result;
    }
    
    private void sortRemaining(int src[],int result[],boolean used[],int pos){
        int pos1 = pos;
        for(int i=0; i < src.length; i++){
            if(!used[i]){
                result[pos1] = src[i];
                pos1++;
            }
        }
        Arrays.sort(result,pos,result.length);
    }
    
    private boolean findNumber(int src[],int dest[],int result[],int pos,boolean used[]){
        
        if(pos == result.length){
            return false;
        }
        
        boolean hasEqual = false;
        int nextGreaterIndex = -1;
        int equalIndex = -1;
        int nextGreaterDiff = Integer.MAX_VALUE;
        for(int i=0; i < src.length; i++){
            if(used[i]){
                continue;
            }
            if(dest[pos] == src[i]){
                hasEqual = true;
                equalIndex = i;
                continue;
            }
            if(src[i] > dest[pos]){
                if(src[i] - dest[pos] < nextGreaterDiff){
                    nextGreaterIndex = i;
                    nextGreaterDiff = src[i] - dest[pos];
                }
            }
        }
        //first try with equal item and see next numbers in array might find larger one.
        //if it fail try next larger number
        if(hasEqual){
            used[equalIndex] = true;
            result[pos] = src[equalIndex];
            if(findNumber(src,dest,result,pos+1,used)){
                return true;
            }
            used[equalIndex] = false;
        }
        if(nextGreaterIndex != -1){
            used[nextGreaterIndex] = true;
            result[pos] = src[nextGreaterIndex];
            sortRemaining(src, result, used, pos+1);
            return true;
        }
        return false;
    }
    
    public static void main(String args[]){
        int src[] = {1,2,5,7,8};
        int dest[] = {5,8,1,9,8};
        PermutationLargerThanGivenArray nld = new PermutationLargerThanGivenArray();
        int result[] = nld.findBiggerNumber(src, dest);
        if(result != null){
            for(int i=0; i < result.length; i++){
                System.out.print(result[i] + " ");
            }
        }else{
            System.out.println("Cant find bigger permutation");
        }
    }
    
}
