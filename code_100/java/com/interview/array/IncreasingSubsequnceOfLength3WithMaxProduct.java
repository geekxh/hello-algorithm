package com.interview.array;

/**
 * http://www.geeksforgeeks.org/increasing-subsequence-of-length-three-with-maximum-product/
 * Keep two arrays which keeps max from current position to right side
 * Other array keeps max on left size which is smaller than current element
 * Once you have these two arrays from 2nd to 2nd last position keep multiplying
 * elements at 3 arrays index position to get max product
 * Test cases
 * Negative numbers
 * 0 in the input
 */
public class IncreasingSubsequnceOfLength3WithMaxProduct {

    public int maxProduct(int arr[]){
        int RGN[] = new int[arr.length];
        int LGN[] = new int[arr.length];
        RGN[arr.length-1] = arr[arr.length-1];
        int max = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--){
            if(max < arr[i]){
                max = arr[i];
            }
            if(max > arr[i]){
                RGN[i] = max;
            }
            else{
                RGN[i] = 0;
            }
        }
        LGN[0] = 0;
        //This can be implemented using an AVL tree instead of this way which will
        //make it O(nLogn) operation insteado ofO(n2).
        for(int i=1; i < arr.length; i++){
            getLGN(arr,i,LGN);
        }
        int maxProduct = 0;
        for(int i=1; i < arr.length-1; i++){
            int product = arr[i]*LGN[i]*RGN[i];
            if(maxProduct < product){
                maxProduct = product;
            }
        }
        return maxProduct;
    }
    
    private void getLGN(int arr[],int pos,int LGN[]){
        int max = 0;
        int i =0;
        while(i < pos){
            if(arr[i] < arr[pos]){
                if(arr[i] > max){
                    max = arr[i];
                }
            }
            i++;
        }
        LGN[pos] = max;
    }
    
    public static void main(String args[]){
        int arr[] = {6, 7, 8, 1, 2, 3, 9, 10};
        IncreasingSubsequnceOfLength3WithMaxProduct iss = new IncreasingSubsequnceOfLength3WithMaxProduct();
        System.out.println(iss.maxProduct(arr));
        int arr1[] = {1, 5, 10, 8, 9};
        System.out.println(iss.maxProduct(arr1));
    }
}
