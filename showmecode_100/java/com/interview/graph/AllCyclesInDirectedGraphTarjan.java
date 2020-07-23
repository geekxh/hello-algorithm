package com.interview.graph;

import java.util.*;

/**
 * Find all simple cycles in a directed graph using Tarjan's algorithm.
 *
 * Space complexity - O(E + V + S) where S is length of all cycles
 * Time complexity - O(E*V(C+1) where C is total number of cycles
 *
 * Reference - https://ecommons.cornell.edu/handle/1813/5941
 * https://github.com/jgrapht/jgrapht/tree/master/jgrapht-core/src/main/java/org/jgrapht/alg/cycle
 */
public class AllCyclesInDirectedGraphTarjan {

    private Set<Vertex<Integer>> visited;
    private Deque<Vertex<Integer>> pointStack;
    private Deque<Vertex<Integer>> markedStack;
    private Set<Vertex<Integer>> markedSet;

    public AllCyclesInDirectedGraphTarjan() {
        reset();
    }

    private void reset() {
        visited = new HashSet<>();
        pointStack = new LinkedList<>();
        markedStack = new LinkedList<>();
        markedSet = new HashSet<>();
    }

    public List<List<Vertex<Integer>>> findAllSimpleCycles(Graph<Integer> graph) {
        reset();
        List<List<Vertex<Integer>>> result = new ArrayList<>();
        for(Vertex<Integer> vertex : graph.getAllVertex()) {
            findAllSimpleCycles(vertex, vertex, result);
            visited.add(vertex);
            while(!markedStack.isEmpty()) {
                markedSet.remove(markedStack.pollFirst());
            }
        }
        return result;
    }

    private boolean findAllSimpleCycles(Vertex start, Vertex<Integer> current,List<List<Vertex<Integer>>> result) {
        boolean hasCycle = false;
        pointStack.offerFirst(current);
        markedSet.add(current);
        markedStack.offerFirst(current);

        for (Vertex<Integer> w : current.getAdjacentVertexes()) {
            if (visited.contains(w)) {
                continue;
            } else if (w.equals(start)) {
                hasCycle = true;
                pointStack.offerFirst(w);
                List<Vertex<Integer>> cycle = new ArrayList<>();
                Iterator<Vertex<Integer>> itr = pointStack.descendingIterator();
                while(itr.hasNext()) {
                    cycle.add(itr.next());
                }
                pointStack.pollFirst();
                result.add(cycle);
            } else if (!markedSet.contains(w)) {
                hasCycle = findAllSimpleCycles(start, w, result) || hasCycle;
            }
        }

        if (hasCycle) {
            while(!markedStack.peekFirst().equals(current)) {
                markedSet.remove(markedStack.pollFirst());
            }
            markedSet.remove(markedStack.pollFirst());
        }

        pointStack.pollFirst();
        return hasCycle;
    }

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 4);
        graph.addEdge(1, 7);
        graph.addEdge(1, 6);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 7);
        graph.addEdge(2, 6);
        graph.addEdge(7, 8);
        graph.addEdge(7, 5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 3);
        graph.addEdge(3, 7);
        graph.addEdge(3, 6);
        graph.addEdge(3, 4);
        graph.addEdge(6, 5);
        graph.addEdge(6, 8);

        AllCyclesInDirectedGraphTarjan tarjan = new AllCyclesInDirectedGraphTarjan();
        List<List<Vertex<Integer>>> result = tarjan.findAllSimpleCycles(graph);
        result.forEach(cycle -> {
            cycle.forEach(v -> System.out.print(v.getId() + " "));
            System.out.println();
        });
    }
}
