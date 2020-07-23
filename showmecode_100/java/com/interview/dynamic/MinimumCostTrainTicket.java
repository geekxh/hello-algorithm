package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-direction/
 */
public class MinimumCostTrainTicket {

    public int minCost(int ticket[][]){
        assert ticket != null && ticket.length > 0 && ticket.length == ticket[0].length;
        int T[] = new int[ticket.length];
        int T1[] = new int[ticket.length];
        T1[0] = -1;
        for(int i=1; i < T.length; i++){
            T[i] = ticket[0][i];
            T1[i] = i-1;
        }
        
        for(int i=1; i < T.length; i++){
            for(int j=i+1; j < T.length; j++){
                if(T[j] > T[i] + ticket[i][j]){
                    T[j] = T[i] + ticket[i][j];
                    T1[j] = i;
                }
            }
        }
        
        //printing actual stations
        int i = ticket.length-1;
        while(i != -1){
            System.out.print(i + " ");
            i = T1[i];
        }
        System.out.println();
        return T[ticket.length-1];
    }
    
    public static void main(String args[]){
        int input[][] = {{0, 15, 80, 90},
                         {-1, 0,  40, 50},
                         {-1, -1,  0,  70},
                         {-1, -1,  -1,  0}};
        MinimumCostTrainTicket mctt = new MinimumCostTrainTicket();
        System.out.println(mctt.minCost(input));
    }
}
