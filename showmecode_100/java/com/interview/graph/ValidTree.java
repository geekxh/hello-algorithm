package com.interview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * https://leetcode.com/problems/graph-valid-tree/
 */
public class ValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (n <= 1) {
            return true;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> neighbors = graph.get(edge[0]);
            if (neighbors == null) {
                neighbors = new ArrayList<>();
                graph.put(edge[0], neighbors);
            }
            neighbors.add(edge[1]);
            neighbors = graph.get(edge[1]);
            if (neighbors == null) {
                neighbors = new ArrayList<>();
                graph.put(edge[1], neighbors);
            }
            neighbors.add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        boolean hasCycle = isCycle(0, graph, -1, visited);
        if (hasCycle) {
            return false;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    boolean isCycle(int vertex, Map<Integer, List<Integer>> graph, int parent, boolean[] visited) {
        if (visited[vertex]) {
            return true;
        }
        visited[vertex] = true;
        if (graph.get(vertex) == null) {
            return false;
        }
        for (int i : graph.get(vertex)) {
            if (i == parent) {
                continue;
            }
            if (isCycle(i, graph, vertex, visited)) {
                return true;
            }
        }
        return false;
    }
}

