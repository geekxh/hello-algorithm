package com.interview.number;

/**
 * http://www.careercup.com/question?id=4857362737266688
 * Test cases : 
 * 6 2 3 -> 6 7 8
 * 1 2 3 -> 1 2 4
 * 1,7,9 -> 1,8,9
 * 1,8,9 -> 2,3,4
 * 9,8,7 -> 1,2,3,4
 * 2,6   -> 2,7
 */
public class SmallestNumberGreaterThanGiveNumberIncreasingSequence {

    public int[] getNextInt(int []input){
        int result[] = new int[input.length];
        boolean flag = getNextInt(input,result,0);
        if(flag){
            return result;
        }else{
            result = new int[input.length+1];
            fillRestOfArray(result, 0, (input[0] +1)%9);
            return result;
        }
    }
    
    private boolean getNextInt(int input[], int result[], int pos){
        if(pos == input.length-1){
            int higher = getHigherNumber(input[pos]+1,pos,input.length);
            if(higher == -1){
                return false;
            }else{
                result[pos] = input[pos]+1;
                return true;
            }
        }
        
        if(pos != 0 && input[pos] <= input[pos-1]){
            int higher = getHigherNumber(input[pos-1] +1,pos,input.length);
            if(higher == -1){
                return false;
            }else{
                fillRestOfArray(result, pos, input[pos-1]+1);
                return true;
            }
        }
        if(input[pos] + (input.length - 1 -pos) <=9){
            result[pos] = input[pos];
            boolean flag = getNextInt(input,result,pos+1);
            if(flag){
                return true;
            }
            int higher = getHigherNumber(input[pos]+1,pos,input.length);
            if(higher == -1){
                return false;
            }else{
                fillRestOfArray(result,pos,input[pos]+1);
                return true;
            }
            
        }else{
            return false;
        }
        
    }
    
    private void fillRestOfArray(int result[],int pos,int val){
        for(int i=pos; i < result.length ; i++){
            result[i] = val;
            val++;
        }
    }
    
    private int getHigherNumber(int input, int i, int len){
        if(input + len - i-1 <=9){
            return input;
        }
        return -1;
    }
    
    public static void main(String args[]){
        int arr[] = {2,6};
        SmallestNumberGreaterThanGiveNumberIncreasingSequence sm = new SmallestNumberGreaterThanGiveNumberIncreasingSequence();
        int result[] = sm.getNextInt(arr);
        for(int i : result){
            System.out.print(i);
        }
    }
}
