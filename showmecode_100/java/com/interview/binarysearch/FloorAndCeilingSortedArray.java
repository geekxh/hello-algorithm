package com.interview.binarysearch;

/**
 * http://www.geeksforgeeks.org/search-floor-and-ceil-in-a-sorted-array/
 */
public class FloorAndCeilingSortedArray {

    public int floor(int input[], int x){
        int low = 0;
        int high = input.length-1;
        while(low <= high){
            int middle = (low + high)/2;
            if(input[middle] == x || (input[middle] < x && (middle == input.length-1 || input[middle+1] > x))){
                return middle;
            }else if(input[middle] < x){
                low = middle+1;
            }else{
                high = middle-1;
            }
        }
        return -1;
    }

    public int ceiling(int input[], int x){
        int low = 0;
        int high = input.length-1;
        while(low <= high){
            int middle = (low + high)/2;
            if(input[middle] == x || (input[middle] > x && (middle == 0 || input[middle-1] < x))){
                return middle;
            }else if(input[middle] < x){
                low = middle+1;
            }else{
                high = middle-1;
            }
        }
        return -1;
    }
    
    public static void main(String args[]){
        int input[] = {1,2,5,6,11,15};
        FloorAndCeilingSortedArray foc = new FloorAndCeilingSortedArray();
        System.out.println(foc.floor(input, 15));
        System.out.println(foc.ceiling(input, 2));
    }
}
