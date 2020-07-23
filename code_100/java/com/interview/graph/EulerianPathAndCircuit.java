package com.interview.graph;

import java.util.HashMap;
import java.util.Map;

public class EulerianPathAndCircuit<T> {

    public enum Eulerian{
        NOT_EULERIAN,
        EULERIAN,
        SEMIEULERIAN
    }
    
    private boolean isConnected(Graph<T> graph){
        
        Vertex<T> startVertex = null;
        
        for(Vertex<T> vertex : graph.getAllVertex()){
            if(vertex.getDegree() != 0){
                startVertex = vertex;
                break;
            }
        }
        
        if(startVertex == null){
            return true;
        }
        
        Map<Vertex<T>,Boolean> visited = new HashMap<Vertex<T>, Boolean>();
        DFS(startVertex,visited);
        
        for(Vertex<T> testVertex : graph.getAllVertex()){
            if(testVertex.getDegree()!= 0 && !visited.containsKey(testVertex)){
                return false;
            }
        }
        return true;
        
    }
    
    private void DFS(Vertex<T> startVertex, Map<Vertex<T>, Boolean> visited){
        visited.put(startVertex, true);
        for(Vertex<T> child : startVertex.getAdjacentVertexes()){
            if(!visited.containsKey(child)){
                DFS(child,visited);
            }
        }
    }
    
    public Eulerian isEulerian(Graph<T> graph){
        
        if(!isConnected(graph)){
            return Eulerian.NOT_EULERIAN;
        }

        int odd = 0;
        for(Vertex<T> vertex : graph.getAllVertex()){
            if(vertex.getDegree()!=0 && vertex.getDegree() % 2 != 0){
                odd++;
            }
        }
        
        if(odd > 2){
            return Eulerian.NOT_EULERIAN;
        }
        
        return odd == 0 ? Eulerian.EULERIAN : Eulerian.SEMIEULERIAN; 
    }
    
    
    public static void main(String args[]){
        
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addSingleVertex(1);
        graph.addSingleVertex(2);
        graph.addSingleVertex(3);
        graph.addEdge(4, 5);
        graph.addEdge(6, 4);
        graph.addEdge(5,6);
        
        EulerianPathAndCircuit<Integer> eulerian = new EulerianPathAndCircuit<Integer>();
        Eulerian result = eulerian.isEulerian(graph);
        System.out.print(result);
    }
}
