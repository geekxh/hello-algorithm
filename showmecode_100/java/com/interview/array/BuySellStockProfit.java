package com.interview.array;

/**
 * Date 03/04/2016
 * @author Tushar Roy
 *
 * Best time to buy and sell stocks.
 * 1) Only 1 transaction is allowed
 * 2) Infinite number transactions are allowed
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BuySellStockProfit {

    public int oneProfit(int arr[]){
        int minPrice = arr[0];
        int maxProfit = 0;
        for(int i=1; i < arr.length; i++){
            if(arr[i] - minPrice > maxProfit){
                maxProfit = arr[i] - minPrice;
            }
            if(arr[i] < minPrice){
                minPrice = arr[i];
            }
        }
        return maxProfit;
    }
    
    public int allTimeProfit(int arr[]){
        int profit = 0;
        for(int i=1; i < arr.length;i++){
            if(arr[i-1] < arr[i]){
                profit += arr[i] - arr[i-1];
            }
        }
        return profit;
    }
    
    public static void main(String args[]){
        int arr[] = {7,10,15,5,11,2,7,9,3};
        int arr1[] = {6,4,1,3,5,7,3,1,3,4,7,9,2,5,6,0,1,2};
        BuySellStockProfit bss = new BuySellStockProfit();
        System.out.println(bss.oneProfit(arr));
        System.out.print(bss.allTimeProfit(arr1));
        
    }
}
