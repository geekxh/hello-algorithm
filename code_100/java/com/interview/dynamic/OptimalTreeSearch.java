package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
 */
public class OptimalTreeSearch {

    public int minCostRec(int input[],int freq[]){
        
        return minCostRec(input,freq,0,input.length-1,1);
    }
    
    private int minCostRec(int input[],int freq[],int low,int high,int level){
        if(low > high){
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=low; i <= high; i++){
            int val = minCostRec(input,freq,low,i-1,level+1) + 
                    minCostRec(input,freq,i+1,high,level+1)
                    + level*freq[i];
            if(val < min){
                min = val;
            }
        }
        return min;
    }
    
    public int minCost(int input[], int freq[]){
        int T[][] = new int[input.length][input.length];
        
        for(int i=0; i < T.length; i++){
            T[i][i] = freq[i];
        }
        
        for(int l = 2; l <= input.length; l++){
            for(int i=0; i <= input.length-l; i++){
                int j = i + l -1;
                T[i][j] = Integer.MAX_VALUE;
                int sum = getSum(freq, i, j);
                
                for(int k=i; k <= j; k++){
                     int val = sum + (k-1 < i ? 0 : T[i][k-1]) +
                            (k+1 > j ? 0 : T[k+1][j]) ;
                     if(val < T[i][j]){
                         T[i][j] = val;
                     }
                }
            }
        }
        return T[0][input.length-1];
    }
    
    private int getSum(int freq[], int i, int j){
        int sum = 0;
        for(int x = i; x <= j; x++){
            sum += freq[x];
        }
        return sum;
    }
 
    
    public static void main(String args[]){
        int input[] = {10,12,20,35,46};
        int freq[] = {34,8,50,21,16};
        OptimalTreeSearch ots = new OptimalTreeSearch();
        System.out.println(ots.minCost(input, freq));
        System.out.println(ots.minCostRec(input, freq));
    }
}
