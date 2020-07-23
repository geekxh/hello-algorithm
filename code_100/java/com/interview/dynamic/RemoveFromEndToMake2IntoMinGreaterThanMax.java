package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
 */
public class RemoveFromEndToMake2IntoMinGreaterThanMax {

    public int removeFromEnd(int input[]){
        return removeFromEnd(input,0,input.length-1);
    }
    
    private int removeFromEnd(int input[],int low,int high){
        if(low > high){
            return input.length;
        }
        int min = min(input,low,high);
        int max = max(input,low,high);
        if(2*min > max){
            return 0;
        }

        return Math.min(removeFromEnd(input,low,high-1), removeFromEnd(input,low+1,high)) +1;
    }
    
    private int min(int input[],int low,int high){
        int min = Integer.MAX_VALUE;
        for(int i=low; i <=high; i++){
            if(min > input[i]){
                min = input[i];
            }
        }
        return min;
    }
    private int max(int input[],int low,int high){
        int max = Integer.MIN_VALUE;
        for(int i=low; i <=high; i++){
            if(max < input[i]){
                max = input[i];
            }
        }
        return max;
    }
    
    public int removeFromEndDynamic(int input[]){
        int T[][] = new int[input.length][input.length];
        for(int l=1; l <= input.length; l++){
            for(int i=0, j = i + l-1; i < input.length-l+1; i++,j++){
                int min = min(input,i,j);
                int max = max(input,i,j);
                if(2*min > max){
                    T[i][j] = 0;
                }else{
                    T[i][j] = Math.min(T[i+1][j] , T[i][j-1]) +1;
                }
            }
        }
        return T[0][input.length-1];
    }
    public static void main(String args[]){
        int input[] = {5,1,3,1,3,8,3};
        RemoveFromEndToMake2IntoMinGreaterThanMax rme = new RemoveFromEndToMake2IntoMinGreaterThanMax();
        System.out.println(rme.removeFromEnd(input));
        System.out.println(rme.removeFromEndDynamic(input));
    }
}
