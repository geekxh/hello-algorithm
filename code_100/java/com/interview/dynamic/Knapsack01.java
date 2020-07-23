package com.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 04/04/2014
 * @author Tushar Roy
 *
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * Time complexity - O(W*total items)
 *
 * Youtube link
 * Topdown DP - https://youtu.be/149WSzQ4E1g
 * Bottomup DP - https://youtu.be/8LusJS5-AGo
 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 */
public class Knapsack01 {

    /**
     * Solves 0/1 knapsack in bottom up dynamic programming
     */
    public int bottomUpDP(int val[], int wt[], int W){
        int K[][] = new int[val.length+1][W+1];
        for(int i=0; i <= val.length; i++){
            for(int j=0; j <= W; j++){
                if(i == 0 || j == 0){
                    K[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    K[i][j] = Math.max(K[i-1][j], K[i-1][j-wt[i-1]] + val[i-1]);
                }else{
                    K[i][j] = K[i-1][j];
                }
            }
        }
        return K[val.length][W];
    }

    /**
     * Key for memoization
     */
    class Index {
        int remainingWeight;
        int remainingItems;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Index index = (Index) o;

            if (remainingWeight != index.remainingWeight) return false;
            return remainingItems == index.remainingItems;

        }

        @Override
        public int hashCode() {
            int result = remainingWeight;
            result = 31 * result + remainingItems;
            return result;
        }
    }

    /**
     * Solves 0/1 knapsack in top down DP
     */
    public int topDownRecursive(int values[], int weights[], int W) {
        //map of key(remainingWeight, remainingCount) to maximumValue they can get.
        Map<Index, Integer> map = new HashMap<>();
        return topDownRecursiveUtil(values, weights, W, values.length, 0, map);
    }

    public int topDownRecursiveUtil(int values[], int weights[], int remainingWeight, int totalItems, int currentItem, Map<Index, Integer> map) {
        //if currentItem exceeds total item count or remainingWeight is less than 0 then
        //just return with 0;
        if(currentItem >= totalItems || remainingWeight <= 0) {
            return 0;
        }

        //fom a key based on remainingWeight and remainingCount
        Index key = new Index();
        key.remainingItems = totalItems - currentItem -1;
        key.remainingWeight = remainingWeight;

        //see if key exists in map. If so then return the maximumValue for key stored in map.
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int maxValue;
        //if weight of item is more than remainingWeight then try next item by skipping current item
        if(remainingWeight < weights[currentItem]) {
            maxValue = topDownRecursiveUtil(values, weights, remainingWeight, totalItems, currentItem + 1, map);
        } else {
            //try to get maximumValue of either by picking the currentItem or not picking currentItem
            maxValue = Math.max(values[currentItem] + topDownRecursiveUtil(values, weights, remainingWeight - weights[currentItem], totalItems, currentItem + 1, map),
                    topDownRecursiveUtil(values, weights, remainingWeight, totalItems, currentItem + 1, map));
        }
        //memoize the key with maxValue found to avoid recalculation
        map.put(key, maxValue);
        return maxValue;

    }

    public static void main(String args[]){
        Knapsack01 k = new Knapsack01();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int r = k.bottomUpDP(val, wt, 30);
        int r1 = k.topDownRecursive(val, wt, 30);
        System.out.println(r);
        System.out.println(r1);
    }
}
