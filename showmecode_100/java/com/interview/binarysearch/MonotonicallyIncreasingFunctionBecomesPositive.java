package com.interview.binarysearch;

/**
 * http://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/
 */
public class MonotonicallyIncreasingFunctionBecomesPositive {

    private int f(int x){
        return x*x - 10*x - 20;
    }
    
    public int findPoint(){
        int i=1;
        while(f(i) <=0 ){
            i = i*2;
        }
        return binarySearch(i/2,i);
    }
    
    private int binarySearch(int start,int end){
        int mid = (start+end)/2;
        while(start < end){
            mid = (start+end)/2;
            if(f(mid) >0 && f(mid-1) <=0){
                return mid;
            }
            if(f(mid) <=0 && f(mid+1)>0){
                return mid+1;
            }
            if(f(mid) <= 0){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return mid;
    }
    
    public static void main(String args[]){
        MonotonicallyIncreasingFunctionBecomesPositive mif = new MonotonicallyIncreasingFunctionBecomesPositive();
        System.out.print(mif.findPoint());
    }
}
