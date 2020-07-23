package com.interview.array;

/**
 * This solution is incorrect. It is greedy approach which will not work
 * e.g 1,6,6,8,9,10 - Result should be 1,9,10 and 6,6,8 but it will not give this result
 * since it will greedily break 9 and 10 into different sets
 * 
 * INCORRECT SOLUTION.(Still keeping the code in case I can improve it)
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideNumbersInEqualGroupWithClosestSum {

    public void divide(int arr[],List<Integer> list1, List<Integer> list2){
        Arrays.sort(arr);
        int len = arr.length;
        int sum1 = 0;
        int sum2 = 0;
        for(int i = len-1 ; i >=0; i--){
            if((sum1 < sum2 && list1.size() < len/2) || (list2.size() >= len/2)){
                list1.add(arr[i]);
                sum1 = sum1 + arr[i];
            }else{
                list2.add(arr[i]);
                sum2 = sum2 + arr[i];
            }
        }
    }
    
    public static void main(String args[]){
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        int arr[] = {15,14,13,1,3,2,};
        int arr1[] = {23, 45, 34, 12,11, 98, 99, 4, 189, 1,7,19,105, 201};
        
        DivideNumbersInEqualGroupWithClosestSum dn = new DivideNumbersInEqualGroupWithClosestSum();
        dn.divide(arr, list1, list2);
        System.out.println(list1);
        System.out.println(list2);
        
        list1.clear();
        list2.clear();
        dn.divide(arr1, list1, list2);
        System.out.println(list1);
        System.out.println(list2);
        
    }
}
