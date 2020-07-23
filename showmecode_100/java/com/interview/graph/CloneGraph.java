package com.interview.graph;

import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        Set<Integer> visited = new HashSet<>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(clone.label, clone);
        dfs(node, clone, map, visited);
        return clone;
    }

    private void dfs(UndirectedGraphNode current, UndirectedGraphNode clone, Map<Integer, UndirectedGraphNode> map,  Set<Integer> visited) {
        if (visited.contains(current.label)) {
            return;
        }
        visited.add(current.label);
        for (UndirectedGraphNode adj : current.neighbors) {
            if (adj.label != current.label) {
                UndirectedGraphNode adjClone = map.get(adj.label);
                if (adjClone == null) {
                    adjClone = new UndirectedGraphNode(adj.label);
                    map.put(adjClone.label, adjClone);
                }
                clone.neighbors.add(adjClone);
                dfs(adj, adjClone, map, visited);
            } else {
                clone.neighbors.add(clone);
            }
        }
    }
}
