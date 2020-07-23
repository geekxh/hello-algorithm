package com.interview.sort;

import java.util.Deque;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/iterative-quick-sort/
 * Test case
 * 0,1 or more elements in the array
 */
public class IterativeQuickSort {

    public void sort(int arr[]){
        Deque<Integer> stack = new LinkedList<Integer>();
        int l = 0;
        int h = arr.length-1;
        stack.offerFirst(l);
        stack.offerFirst(h);
        
        while(!stack.isEmpty()){
            h = stack.pollFirst();
            l = stack.pollFirst();
            
            int p = partition(arr,l,h);
            if(l < p-1 && h > p+1){
                stack.offerFirst(l);
                stack.offerFirst(p-1);
                stack.offerFirst(p+1);
                stack.offerFirst(h);
            }
            else if(l < p-1){
                stack.offerFirst(l);
                stack.offerFirst(p-1);
            }else if(h > p+1){
                stack.offerFirst(p+1);
                stack.offerFirst(h);
            }
        }
    }
    
    private int partition(int arr[], int low,int high){
        int pivot = arr[low];
        int i = low+1;
        int j = low+1;
        while(j <= high){
            if(arr[j] < pivot){
                swap(arr,i,j);
                i++;
            }
            j++;
        }
        swap(arr,i-1,low);
        return i-1;
        
    }
    
    private void swap(int arr[],int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    public static void main(String args[]){
        int arr[] = {3,2,8,0,11,-1,-5,4,32,-60,44};
        IterativeQuickSort iqs = new IterativeQuickSort();
        iqs.sort(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
