package com.interview.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.careercup.com/question?id=5988741646647296
 * Given an undirected graph find number of triangles in this graph
 * Find cycle of length 3. Pass parent in DFS search.
 * If there is a cycle check if my parent is neighbor of the the node 
 * which caused it to be a cycle.
 */
public class NumberofTriangles {

    public int countTriangles(Graph<Integer> graph){
        Map<Vertex<Integer>,Boolean> visited = new HashMap<Vertex<Integer>,Boolean>();
        int count =0;
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            count += DFS(vertex,visited,null);
        }
        return count;
    }
    
    public int DFS(Vertex<Integer> vertex, Map<Vertex<Integer>,Boolean> visited,Vertex<Integer> parent){
        
        if(visited.containsKey(vertex)){
            return 0;
        }
        visited.put(vertex, true);
        int count = 0;
        for(Vertex<Integer> child : vertex.getAdjacentVertexes()){
            if(child.equals(parent)){
                continue;
            }
            if(visited.containsKey(child)){
                count += isNeighbor(child, parent) ? 1: 0;
            }else{
                count += DFS(child, visited, vertex);
            }
        }
        return count;
    }
    
    private boolean isNeighbor(Vertex<Integer> vertex, Vertex<Integer> destVertex){
        for(Vertex<Integer> child : vertex.getAdjacentVertexes()){
            if(child.equals(destVertex)){
                return true;
            }
        }
        return false;
    }
    
    public static void main(String args[]){
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(0, 4);
        graph.addEdge(0,3);
        NumberofTriangles not = new NumberofTriangles();
        System.out.println(not.countTriangles(graph));
    }
}
