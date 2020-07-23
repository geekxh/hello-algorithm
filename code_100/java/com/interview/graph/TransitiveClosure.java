package com.interview.graph;

/**
 * http://www.geeksforgeeks.org/transitive-closure-of-a-graph/
 */
public class TransitiveClosure {

    public boolean[][] getTransitiveClosure(int [][]graph){
        int rows = graph.length;
        int cols = graph[0].length;
        boolean[][] result = new  boolean[rows][cols];

        for(int i = 0; i < graph.length; i++){
            for(int j=0; j < graph.length; j++){
                if(graph[i][j] != 100){
                    result[i][j] = true;
                }
            }
        }
        for(int i=0; i < rows; i++){
            for(int j=0 ; j < rows; j++){
                for(int k=0; k < rows; k++){
                    result[i][j] = result[i][j] || (result[i][k] && result[k][j]);
                }
            }
        }
        return result;
    }
    
    public static void main(String args[]){
        TransitiveClosure closure = new TransitiveClosure();
        int[][] graph = {{0,2,2,4,100},{100,0,100,1,100},{100,100,0,3,100},{100,100,3,0,2},{100,3,100,100,0}};
        boolean result[][] = closure.getTransitiveClosure(graph);
        for(int i=0; i < result.length; i++){
            for(int j=0; j < result.length; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
