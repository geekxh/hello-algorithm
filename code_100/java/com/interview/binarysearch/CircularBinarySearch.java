package com.interview.binarysearch;

/**
 * http://www.careercup.com/question?id=4877486110277632
 * Given a circle with N defined points and a point M outside the circle, 
 * find the point that is closest to M among the set of N. O(LogN)
 * Test cases
 * 1) smallest element at center
 * 2) smallest element at left/right end
 * 3) largest element at center
 * 4) smallest element at left side
 * 5) smallest element at right side
 */
public class CircularBinarySearch {

    //if mid is greater than both ends than result is low of two ends else move in direction
    //where either mid-1 or mid+1 is less
    public int search(int arr[]) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            //if middle is less than both mid-1 and mid+1 then mid is the answer
            if((low == mid || arr[mid] < arr[mid-1])&& arr[mid] < arr[mid+1]){
                return arr[mid];
            }
            if ((arr[mid] >= arr[low] && arr[mid] >= arr[high])){
                if(arr[low] < arr[high]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{
                if(arr[mid-1] < arr[mid+1]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
        }
        return arr[low];
    }

    public static void main(String args[]) {
        CircularBinarySearch cbs = new CircularBinarySearch();
        int arr[] = { 7, 10, 8, 5, 2, 3, 5 };
        System.out.print(cbs.search(arr));

        int arr1[] = { 5, 8, 10, 7, 5, 3, 2 };
        System.out.print(cbs.search(arr1));

        int arr2[] = { 3, 5, 7, 10, 8, 5, 2 };
        System.out.print(cbs.search(arr2));

        int arr3[] = { 8, 5, 2, 3, 5, 7, 10 };
        System.out.print(cbs.search(arr3));

        int arr4[] = { 5, 3, 2, 5, 8, 10, 7 };
        System.out.print(cbs.search(arr4));

        int arr5[] = {100,20,10,5,2,8,11,16,19};
        System.out.print(cbs.search(arr5));

        int arr6[] = {200,2,10,15,20,80,110,160,190};
        System.out.print(cbs.search(arr6));

        int arr7[] = {5,10,20,50,200,800,1100,1600,1900,2};
        System.out.print(cbs.search(arr7));

        int arr8[] = {2,5,10,20,50,200,800,1100,1600,1900};
        System.out.print(cbs.search(arr8));
        
        int arr9[] = {3,1,8,5,4};
        System.out.print(cbs.search(arr9));
        
    }
}
