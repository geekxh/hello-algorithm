package com.interview.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
http://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 */
public class DAGShortestPathTopological<T> {

    public Map<Vertex<T>,Integer> shortestPath(Graph<T> graph,Vertex<T> startVertex){
        
        Map<Vertex<T>,Integer> distance = new HashMap<Vertex<T>,Integer>();
        TopologicalSort<T> sort = new TopologicalSort<T>();
        Deque<Vertex<T>> deque = sort.topSort(graph);
        distance.put(startVertex, 0);
        while(!deque.isEmpty()){
            Vertex<T> vertex = deque.poll();
            for(Edge<T> edge : vertex.getEdges()){
                if(getDistance(edge.getVertex2(),distance) > getDistance(edge.getVertex1(),distance) + edge.getWeight()){
                    distance.put(edge.getVertex2(), getDistance(edge.getVertex1(),distance) + edge.getWeight());
                }
            }
        }
        
        return distance;
    }
    
    private int getDistance( Vertex<T> vertex,Map<Vertex<T>,Integer> distance){
        return distance.containsKey(vertex) ? distance.get(vertex) : 1000;
    }
    
    public static void main(String args[]){
        Graph<Integer> graph = new Graph<Integer>(true);
        graph.addEdge(1, 2,4);
        graph.addEdge(2, 3,3);
        graph.addEdge(2, 4,2);
        graph.addEdge(1, 3,2);
        graph.addEdge(3, 5,1);
        graph.addEdge(4, 5,5);
        graph.addEdge(5, 6,2);
        graph.addEdge(4, 7,3);

        DAGShortestPathTopological<Integer> shortestPath = new DAGShortestPathTopological<Integer>();
        Map<Vertex<Integer>,Integer> distance = shortestPath.shortestPath(graph, graph.getAllVertex().iterator().next());
        System.out.print(distance);
        
    }
}
