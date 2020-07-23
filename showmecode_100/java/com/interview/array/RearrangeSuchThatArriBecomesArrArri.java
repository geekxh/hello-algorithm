package com.interview.array;

/**
 * http://www.geeksforgeeks.org/rearrange-given-array-place/
 */
public class RearrangeSuchThatArriBecomesArrArri {

    public void rearrange(int arr[]){
        for(int i=0; i < arr.length; i++){
            int temp;
            if(arr[arr[i]] > arr.length-1){
                temp = arr[arr[i]]/arr.length-1;
            }else{
                temp = arr[arr[i]];
            }
            arr[i] = temp + arr.length*(arr[i]+1);
        }
        
        for(int i=0; i < arr.length;i++){
            arr[i] = arr[i] % arr.length;
        }
    }
    
    public static void main(String args[]){
        int arr[] = {4,2,0,1,3};
        RearrangeSuchThatArriBecomesArrArri rss = new RearrangeSuchThatArriBecomesArrArri();
        rss.rearrange(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}

