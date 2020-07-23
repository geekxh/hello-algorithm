package com.interview.sort;

/**
 * http://www.geeksforgeeks.org/pancake-sorting/
 * Two ways to do it
 * 1) Start i from arr.length-1 towards 0, find max from 0 to i, move this max to top
 * by one flip and then move this max to ith position by another flip
 * 
 * 2) Start i from 0 towards arr.length-1, find floor of input[i] from 0 to i-1 lets call
 * f , flip 0 to f, then flip 0 to i-1 then flip 0 to i, then flip 0 to i-1.
 * e.g 1 2 3 5 4. Here i is 4 and f is 2
 * 1 2 3 5 4 flip(0,f) -> 3 2 1 5 4
 * 3 2 1 5 4 flip(0,i-1) -> 5 1 2 3 4
 * 5 1 2 3 4 flip(0,i) -> 4 3 2 1 5
 * 4 3 2 1 5 flip(0,i-1) -> 1 2 3 4 5
 */
public class PanCakeSorting {

    public void sort(int arr[]){
        for(int i=arr.length-1; i >= 0 ; i--){
            int pos = findMax(arr,i);
            flip(arr,pos);
            flip(arr,i);
        }
    }
    
    private int findMax(int arr[],int pos){
        int max = pos;
        for(int i= pos-1 ;i >= 0 ;i--){
            if(arr[i] > arr[max]){
                max = i;
            }
        }
        return max;
    }
    
    private void flip(int arr[],int pos){
        for(int i=0; i <= pos/2; i++){
            swap(arr,i,pos-i);
        }
    }
    
    private void swap(int arr[],int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String args[]){
        int arr[] = {9,2,7,11,3,6,1,10,8};
        PanCakeSorting pcs = new PanCakeSorting();
        pcs.sort(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
