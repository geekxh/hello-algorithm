package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/sorted-order-printing-of-an-array-that-represents-a-bst/
 * Test case
 * empty array
 * 1 element array
 * multi element array
 */
public class SortedOrderPrintCompleteTreeArray {

    private void print(int arr[],int current){
        if(current >= arr.length){
            return;
        }
        print(arr,2*current+1);
        System.out.println(arr[current]);
        print(arr,2*current+2);
    }
    
    public void print(int arr[]){
        print(arr,0);
    }
    
    public static void main(String args[]){
        int arr[] = {4, 2, 5, 1, 3};
        SortedOrderPrintCompleteTreeArray sop = new SortedOrderPrintCompleteTreeArray();
        sop.print(arr);
    }
}
