package com.interview.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 http://www.geeksforgeeks.org/bridge-in-a-graph/
 */
public class Bridge<T> {

    private int time;
    
    public Set<Edge<T>> getBridge(Graph<T> graph){
        
        Set<Edge<T>> result = new HashSet<Edge<T>>();
        Map<Vertex<T>,Integer> discovery = new HashMap<Vertex<T>,Integer>();
        Map<Vertex<T>,Integer> low = new HashMap<Vertex<T>,Integer>();
        Map<Vertex<T>,Vertex<T>> parent = new HashMap<Vertex<T>,Vertex<T>>();
        Map<Vertex<T>,Boolean> visited = new HashMap<Vertex<T>,Boolean>();
        
        for(Vertex<T> vertex : graph.getAllVertex()){
            if(!visited.containsKey(vertex)){
                BridgeUtil(vertex,result,discovery,low,parent,visited);
            }
        }
        return result;
    }

    private void BridgeUtil(Vertex<T> vertex, Set<Edge<T>> result,Map<Vertex<T>,Integer> discovery,
            Map<Vertex<T>,Integer> low,Map<Vertex<T>,Vertex<T>> parent,Map<Vertex<T>,Boolean> visited){
        visited.put(vertex, true);
        discovery.put(vertex, time);
        low.put(vertex, time);
        time++;
        for(Vertex<T> child : vertex.getAdjacentVertexes()){
            if(!visited.containsKey(child)){
                parent.put(child, vertex);
                BridgeUtil(child,result,discovery,low,parent,visited);
                
                if(discovery.get(vertex) < low.get(child) ){
                    result.add(new Edge<T>(vertex,child));
                }
                low.put(vertex, Math.min(discovery.get(vertex), low.get(child)));
            }else{
                if(!child.equals(parent.get(vertex))){
                    low.put(vertex,Math.min(discovery.get(vertex), low.get(child)));
                }
            }
        }
    }
    
    public static void main(String args[]){
        
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(2, 1);
        graph.addEdge(3, 1);
        graph.addEdge(1, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);
        Bridge<Integer> ap = new Bridge<Integer>();
        Set<Edge<Integer>> result = ap.getBridge(graph);
        System.out.print(result);
    }
    
}
