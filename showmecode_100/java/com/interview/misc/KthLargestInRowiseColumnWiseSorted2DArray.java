package com.interview.misc;

import com.interview.graph.BinaryMinHeap;

/**
 * Kth largest in rowwise and column wise sorted array
 * http://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/
 */
public class KthLargestInRowiseColumnWiseSorted2DArray {

    public int kthLargest(int input[][],int k){
        
        BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<Integer>();
        int c = input[0].length;
        int total = input.length * c;
        minHeap.add(input[0][0], 0);
        for(int i=1; i < k; i++){
            int minIndex = minHeap.extractMin();
            int minRow = minIndex/c;
            int minCol = minIndex%c;
            
            int downNeighbor = (minRow+1)*c + minCol;
            int rightNeighbor;
            if(minCol== (c-1)){
                rightNeighbor = total;
            }else{
                rightNeighbor = minRow*c + (minCol + 1);
            }
            if(downNeighbor < total && !minHeap.containsData(downNeighbor)){
                minHeap.add(input[minRow+1][minCol], downNeighbor);
            }
            
            if(rightNeighbor < total && !minHeap.containsData(rightNeighbor)){
                minHeap.add(input[minRow][minCol+1], rightNeighbor);
            }
        }
        
        int minIndex = minHeap.extractMin();
        return input[minIndex/c][minIndex%c];
    }
    
    public static void main(String args[]){
        int input[][] ={{1,5,11,21},
                        {6,8,12,22},
                        {7,9,15,26},
                        {11,13,18,28}};
        KthLargestInRowiseColumnWiseSorted2DArray kl = new KthLargestInRowiseColumnWiseSorted2DArray();
        System.out.println(kl.kthLargest(input, 10));
    }
}
