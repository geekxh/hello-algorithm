package com.interview.sort;

/**
 * http://en.wikipedia.org/wiki/Merge_sort
 * Test cases
 * 1 element
 * 2 element
 * negative numbers
 * already sorted
 * reverse sorted
 */
public class MergeSort {

    public void sort(int input[]){
        sort(input, 0, input.length-1);
    }
    
    private void sort(int input[], int low, int high){
        if(low >= high){
            return;
        }
        
        int middle = (low + high)/2;
        sort(input, low, middle);
        sort(input, middle+1, high);
        sortedMerge(input,low,high);
   }
    
    private void sortedMerge(int input[], int low, int high){
        int middle = (low+high)/2;
        int temp[] = new int[high-low+1];
        int i = low;
        int j = middle+1;
        int r = 0;
        while(i <= middle && j <= high){
            if(input[i] <= input[j]){
                temp[r++] = input[i++];
            }else{
                temp[r++] = input[j++];
            }
        }
        while(i <= middle){
            temp[r++] = input[i++];
        }
        
        while(j <= high){
            temp[r++] = input[j++];
        }
        i = low;
        for(int k=0; k < temp.length;){
            input[i++] = temp[k++];
        }
    }
    
    public void printArray(int input[]){
        for(int i : input){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void main(String args[]){
        int input1[] = {1};
        int input2[] = {4,2};
        int input3[] = {6,2,9};
        int input4[] = {6,-1,10,4,11,14,19,12,18};
        MergeSort ms = new MergeSort();
        ms.sort(input1);
        ms.sort(input2);
        ms.sort(input3);
        ms.sort(input4);
        
        ms.printArray(input1);
        ms.printArray(input2);
        ms.printArray(input3);
        ms.printArray(input4);
    }
}
