package com.interview.array;

/**
 * http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
 */
public class PositiveAndNegativeNumberAlternatively {

    public void arrange(int arr[]){
        int startOfPos = segregate(arr);
        
        int startOfNeg = 1;
        while(startOfNeg < startOfPos && startOfPos < arr.length){
            swap(arr,startOfNeg,startOfPos);
            startOfNeg+=2;
            startOfPos++;
        }
    }
    
    private int segregate(int arr[]){
        int low =0;
        int high = arr.length-1;
        while(low < high){
            if(arr[low] < 0){
                low++;
            }else if(arr[high] >= 0){
                high--;
            }else{
                swap(arr,low,high);
            }
        }
        return low;
    }
    
    private void swap(int arr[],int i,int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    
    public static void main(String args[]){
        int arr[] = {-1,-2,-3,-4,-5,1,2,3,4,5};
        PositiveAndNegativeNumberAlternatively pan = new PositiveAndNegativeNumberAlternatively();
        pan.arrange(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
    }
}
