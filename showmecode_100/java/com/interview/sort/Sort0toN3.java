package com.interview.sort;

/**
 * http://www.geeksforgeeks.org/sort-n-numbers-range-0-n2-1-linear-time/
 */
public class Sort0toN3 {

    public void sort(int arr[],int n){
        
        sort(arr,n,1);
        sort(arr,n,n);
        sort(arr,n,n*n);
    }
    
    private void sort(int arr[],int n, int exp){
        int count[] = new int[n];
        for(int i=0; i < arr.length;i++){
            count[(arr[i]/exp)%n]++;
        }
        
        for(int i=1; i < arr.length; i++){
            count[i] += count[i-1];
        }
    
        int output[] = new int[n];
        
        for(int i=arr.length-1;i>=0; i--){
            output[count[(arr[i]/exp)%n]-1] = arr[i];
            count[(arr[i]/exp)%n]--;
        }
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
        }
    }
    
    public static void main(String args[]){
        int arr[] = {100,2,124,18,36};
        Sort0toN3 sn = new Sort0toN3();
        sn.sort(arr,arr.length);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    
}
