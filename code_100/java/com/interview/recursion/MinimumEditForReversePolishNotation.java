package com.interview.recursion;

/**
 *http://www.careercup.com/question?id=5762415492857856 
 */
public class MinimumEditForReversePolishNotation {

    public int minimum(char input[]){
        return minimum(input,0,0);
    }
    
    private int minimum(char input[],int pos,int countXs){
        if(pos == input.length){
            //we should be always be left with 1 x otherwise notation is incomplete
            if(countXs > 1){
                return Integer.MAX_VALUE;
            }
            return 0;
        }
        //if input is x we can have x as it is or delete it or convert it to *.
        //So basically we try all those options below
        //If input is * we can take 2 xs and conver it to one x. If we dont have
        //2xs then we can either delete this * or convert it to x.
        //Remember adding anything does not make any sense since deleting things
        //can achieve same things unless cost of adding is different from deleting
        if(input[pos] == 'x'){
            int v1 = minimum(input,pos+1,countXs+1);
            int v2 = Integer.MAX_VALUE;
            if(countXs > 1){
                v2 = minimum(input,pos+1,countXs-1);
                v2 =( v2 != Integer.MAX_VALUE ? v2 + 1 : Integer.MAX_VALUE);
            }
            int v3 = minimum(input,pos+1,countXs);
            v3 =( v3 != Integer.MAX_VALUE ? v3 + 1 : Integer.MAX_VALUE);
            return Math.min(Math.min(v1,v2),v3);
        }else{
            if(countXs >= 2){
                return minimum(input,pos+1,countXs-1);
            }else{
                int v1 = minimum(input,pos+1,countXs);
                v1 = (v1 != Integer.MAX_VALUE ? v1 + 1 : Integer.MAX_VALUE);
                int v2 = minimum(input,pos+1,countXs+1);
                v2 =( v2 != Integer.MAX_VALUE ? v2 + 1 : Integer.MAX_VALUE);
                return Math.min(v1, v2);
            }
        }
    }
    public static void main(String args[]){
        MinimumEditForReversePolishNotation mef = new MinimumEditForReversePolishNotation();
        System.out.println(mef.minimum("xxxx*x*x*".toCharArray()));
    }
}
