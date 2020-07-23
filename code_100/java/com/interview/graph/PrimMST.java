package com.interview.graph;

import java.util.*;

/**
 * Date 10/11/2014
 * @author Tushar Roy
 *
 * Find minimum spanning tree using Prim's algorithm
 *
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 *
 * References
 * https://en.wikipedia.org/wiki/Prim%27s_algorithm
 * CLRS book
 */
public class PrimMST {

    /**
     * Main method of Prim's algorithm.
     */
    public List<Edge<Integer>> primMST(Graph<Integer> graph){

        //binary heap + map data structure
        BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

        //map of vertex to edge which gave minimum weight to this vertex.
        Map<Vertex<Integer>,Edge<Integer>> vertexToEdge = new HashMap<>();

        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();

        //insert all vertices with infinite value initially.
        for(Vertex<Integer> v : graph.getAllVertex()){
            minHeap.add(Integer.MAX_VALUE, v);
        }

        //start from any random vertex
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

        //for the start vertex decrease the value in heap + map to 0
        minHeap.decrease(startVertex, 0);

        //iterate till heap + map has elements in it
        while(!minHeap.empty()){
            //extract min value vertex from heap + map
            Vertex<Integer> current = minHeap.extractMin();

            //get the corresponding edge for this vertex if present and add it to final result.
            //This edge wont be present for first vertex.
            Edge<Integer> spanningTreeEdge = vertexToEdge.get(current);
            if(spanningTreeEdge != null) {
                result.add(spanningTreeEdge);
            }

            //iterate through all the adjacent vertices
            for(Edge<Integer> edge : current.getEdges()){
                Vertex<Integer> adjacent = getVertexForEdge(current, edge);
                //check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight
                if(minHeap.containsData(adjacent) && minHeap.getWeight(adjacent) > edge.getWeight()){
                    //decrease the value of adjacent vertex to this edge weight.
                    minHeap.decrease(adjacent, edge.getWeight());
                    //add vertex->edge mapping in the graph.
                    vertexToEdge.put(adjacent, edge);
                }
            }
        }
        return result;
    }

    private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }

    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(false);
     /* graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(2, 5, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(0, 7, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);*/

        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimMST prims = new PrimMST();
        Collection<Edge<Integer>> edges = prims.primMST(graph);
        for(Edge<Integer> edge : edges){
            System.out.println(edge);
        }
    }
    
    
}
