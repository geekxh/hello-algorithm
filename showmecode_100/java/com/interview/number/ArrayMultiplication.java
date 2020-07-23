package com.interview.number;

import com.interview.array.ArrayAddition;

public class ArrayMultiplication {

    public int[] multiplyDivideAndConquer(int arr1[],int arr2[],int low1,int high1,int low2,int high2){
        
        if(low1 == high1 || low2 == high2){
            return simpleMultiplication(arr1, arr2, low1, high1, low2, high2);
        }
        
        int mid1 = (low1 + high1)/2;
        int mid2 = (low2 + high2)/2;
        
        int r1[] = multiplyDivideAndConquer(arr1,arr2,low1,mid1,low2,mid2);
        int shiftBy = high1-mid1 + high2-mid2;
        r1 = shift(r1,shiftBy);
        
        int r2[] = multiplyDivideAndConquer(arr1,arr2,mid1+1,high1,mid2+1,high2);
        
        int r3[] = multiplyDivideAndConquer(arr1,arr2,low1,mid1,mid2+1,high2);
        shiftBy = high1 - mid1;
        r3 = shift(r3,shiftBy);
        
        int r4[] = multiplyDivideAndConquer(arr1,arr2,mid1+1,high1,low2,mid2);
        shiftBy = high2 - mid2;
        r4 = shift(r4,shiftBy);
        
        ArrayAddition aa = new ArrayAddition();
        r1 = aa.add(r1, r2);
        r1 = aa.add(r1, r3);
        r1 = aa.add(r1, r4);
        return r1;
        
    }
    
    private int[] shift(int arr[],int n){
        int[] result = new int[arr.length + n];
        int i=0;
        for(i=0; i < arr.length; i++){
            result[i] = arr[i];
        }
        return result;
    }
    
    public int[] simpleMultiplication(int arr1[],int arr2[],int low1,int high1,int low2,int high2){
        
        int[] result = new int[high1-low1 + high2 -low2 + 2];
        
        int m=0;
        int c=0;
        int n =0;
        int index = result.length-1;
        int temp[] = new int[Math.max(high1-low1+1, high2-low2+1)+1];
        for(int i= high1; i >=low1; i--){
            int l = temp.length-1;
            for(int j=high2; j>=low2; j--){
                m = arr1[i]*arr2[j] + c;
                n = m%10;
                temp[l--] = n;
                c = m/10;
            }
            temp[l] = c;
            addToResult(result,temp,index);
            index--;
            c=0;
            for(int t=0; t < temp.length; t++){
                temp[t] = 0;
            }
        }
        return result;
    }
    
    private void addToResult(int[] result,int temp[],int start){
        int c=0;
        for(int i=temp.length-1; i>=0 ; i--){
            if(start == -1){
                break;
            }
            int m = result[start] + temp[i] + c;
            result[start--] = m%10;
            c = m/10;
        }
    }
    
    public int multiplicationImproved(int x, int y, int len){
        if(len == 1){
            return x*y;
        }
        len = len/2;
        int div = power(len);
        int result1 = multiplicationImproved(x/div,y/div,len);
        int result2 = multiplicationImproved(x%div, y % div, len);
        int result3 = multiplicationImproved(x/div + x%div,y/div + y % div,len);
        
        return result1*div*(div -1) +
                result3*div - result2*(div -1 );
        
    }
    
    private int power(int n){
        return (int)Math.pow(10, n);
    }
    
    
    public static void main(String args[]){
        int arr2[] = {9,9,7,4,7,9};
        int arr1[] = {9,9,5,7,4,2,1};
        ArrayMultiplication am = new ArrayMultiplication();
        int result[] = am.multiplyDivideAndConquer(arr1, arr2, 0, arr1.length-1, 0, arr2.length-1);
        for(int i=0; i < result.length; i++){
            System.out.print(" " + result[i]);
        }
        
        System.out.print("\n" + am.multiplicationImproved(999, 168, 2));
        
    }
}
