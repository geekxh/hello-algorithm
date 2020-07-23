package com.interview.graph;

/**
 http://www.geeksforgeeks.org/find-number-of-islands/
 */
public class NumberOfIsland {

    public int numberOfIsland(int[][] graph){
        
        boolean[][] visited = new boolean[graph.length][graph.length];
        int count = 0;
        for(int i=0; i < graph.length ; i ++){
            for(int j =0 ; j < graph[i].length ; j++){
                if(visited[i][j] == false && graph[i][j] == 1) {
                    count++;
                    DFS(graph,visited,i,j);
                }
            }
        }
        return count;
    }
    
    private void DFS(int[][] graph, boolean[][] visited,int i,int j){
        if(i <0 || j < 0 || i == graph.length || j == graph[i].length)
        {
            return;
            
        }
        visited[i][j] = true;
        if(graph[i][j] == 0){
            return;
        }
        DFS(graph,visited,i,j+1);
        DFS(graph,visited,i+1,j);
        DFS(graph,visited,i+1,j+1);
        DFS(graph,visited,i-1, j+1);
    }
    
    public static void main(String args[]){
        
        int matrix[][] = {{1,1,0,1,0},
                          {1,0,0,1,1},
                          {0,0,0,0,0},
                          {1,0,1,0,1},
                          {1,0,0,0,0}
                        };
        NumberOfIsland island = new NumberOfIsland();
        int count = island.numberOfIsland(matrix);
        System.out.println(count);
    }
}
                     