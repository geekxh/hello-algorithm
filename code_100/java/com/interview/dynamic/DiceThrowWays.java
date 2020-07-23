package com.interview.dynamic;

/**
 * @author Tushar Roy
 * http://www.geeksforgeeks.org/dice-throw-problem/
 * This solution assumes that 1,2,1 is different from 2,1,1 which is different from 1,1 2
 * so total 3 ways are possible
 */
public class DiceThrowWays {

    public int numberOfWays(int n, int f, int k){
        
        int T[][] = new int[n+1][k+1];
        T[0][0] = 1;
    /*  for(int i=0; i < T.length; i++){
            T[0][i] = 1;
        }*/
        
        for(int i=1; i <= n; i++){
            for(int j =1; j <= i*f && j <= k ; j++){
                if(j == i){
                    T[i][j] = 1;
                    continue;
                }
                if(j < i){
                    continue;
                }
                for(int l =1; l <=f ;l++){
                    if(j >= l){
                        T[i][j] += T[i-1][j-l];
                    }
                }
            }
        }
        return T[n][k];
    }
    
    public static void main(String args[]){
        DiceThrowWays dtw = new DiceThrowWays();
        System.out.println(dtw.numberOfWays(3, 3, 6));
    }
}   
