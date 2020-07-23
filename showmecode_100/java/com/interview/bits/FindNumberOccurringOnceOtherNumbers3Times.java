package com.interview.bits;

/**
 * http://www.geeksforgeeks.org/find-the-element-that-appears-once/
 */
public class FindNumberOccurringOnceOtherNumbers3Times {

    public int getNumberOccurringOnce(int arr[]){
        int result = 0;
        for(int i=0; i < 32; i++){
            int sum = 0;
            for(int j=0; j < arr.length; j++){
                sum += (arr[j] & 1<<i)>>i; 
            }
            result = result | (sum%3)<<i;
        }
        return result;
    }
    
    public static void main(String args[]){
        FindNumberOccurringOnceOtherNumbers3Times fno = new FindNumberOccurringOnceOtherNumbers3Times();
        int arr[] = {1,1,2,5,6,1,6,6,2,2};
//      int arr[] = {1,1,1,4,1,1,1};
        System.out.println(fno.getNumberOccurringOnce(arr));
    }
}
