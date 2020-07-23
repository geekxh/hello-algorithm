package com.interview.array;

public class MoveAllZerosToEnd {

    public void moveZeros(int arr[]){
        int slow =0;
        int fast =0;
        while(fast < arr.length){
            if(arr[fast] == 0){
                fast++;
                continue;
            }
            arr[slow] = arr[fast];
            slow++;
            fast++;
        }
        while(slow < arr.length){
            arr[slow++] = 0;
        }
    }
    
    public static void main(String args[]){
        MoveAllZerosToEnd maz  = new MoveAllZerosToEnd();
        int arr[] = {0,0,1,2,0,5,6,7,0};
        maz.moveZeros(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}
