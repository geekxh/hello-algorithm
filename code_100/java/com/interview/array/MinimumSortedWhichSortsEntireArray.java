package com.interview.array;

/**
 *http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
 */
public class MinimumSortedWhichSortsEntireArray {

    public int minLength(int arr[]){
        int  i=0;
        while(i < arr.length -1 && arr[i] < arr[i+1]){
            i++;
        }
        if(i == arr.length-1){
            return 0;
        }
        int j = arr.length-1;
        while(j > 0 && arr[j] > arr[j-1]){
            j--;
        }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            if(max < arr[k]){
                max = arr[k];
            }
            if(min > arr[k]){
                min = arr[k];
            }
        }
        int x = i-1;
        while(x >=0){
            if(min > arr[x]){
                break;
            }
            x--;
        }
        
        int y = j +1;
        while(y < arr.length){
            if(max < arr[y]){
                break;
            }
            y++;
        }
        return y -x -2 + 1;
    }
    
    public static void main(String args[]){
        int arr[] = {4,5,10,21,18,23,7,8,19,34,38};
        int arr1[] = {4,5,6,12,11,15};
        int arr2[] = {4,5,6,10,11,15};
        MinimumSortedWhichSortsEntireArray msw = new MinimumSortedWhichSortsEntireArray();
        System.out.println(msw.minLength(arr1));
    }
    
}
