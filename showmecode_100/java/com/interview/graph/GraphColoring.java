package com.interview.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 http://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
 */
public class GraphColoring {

    public void WelshPowell(){
        
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 6);
        graph.addEdge(1, 7);
        graph.addEdge(1, 8);
        graph.addEdge(2, 9);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2,4);
        graph.addEdge(3, 7);
        graph.addEdge(2, 7);

        ComparatorVertex c = new ComparatorVertex();
        Set<Vertex<Integer>> sortedSet = new TreeSet<Vertex<Integer>>(c);
        for(Vertex<Integer> v : graph.getAllVertex()){
            sortedSet.add(v);
        }
        
        Map<Long,String> assignedColor = new HashMap<Long,String>();
        Map<Long,String> finalAssignedColor = new HashMap<Long,String>();
        
        Map<String,Boolean> colorsUsed = new TreeMap<String,Boolean>();
        colorsUsed.put("Green", false);
        colorsUsed.put("Blue", false);
        colorsUsed.put("Red", false);
        colorsUsed.put("Yellow", false);
        colorsUsed.put("Orange",false);

        Set<Vertex<Integer>> removeSet = new HashSet<Vertex<Integer>>();
        while(sortedSet.size() != removeSet.size()){
            String color = null ;
            
            for(Vertex<Integer> v : sortedSet){
                if(removeSet.contains(v)){
                    continue;
                }
                boolean allUncolored = allAdjacentUnColored(v.getAdjacentVertexes(),assignedColor);
                if(allUncolored){
                    color = getUnusedColor(colorsUsed);
                    assignedColor.put(v.getId(), color);
                    removeSet.add(v);
                    finalAssignedColor.put(v.getId(), color);
                }
            }
            colorsUsed.remove(color);
            assignedColor.clear();
        }
        
        System.out.println(finalAssignedColor);
    }
    
    public void colorGraph(){
        
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 6);
        graph.addEdge(1, 7);
        graph.addEdge(1, 8);
        graph.addEdge(2, 9);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2,4);
        graph.addEdge(3,7);

        Map<String,Boolean> colorsUsed = new HashMap<String,Boolean>();
        colorsUsed.put("Green", false);
        colorsUsed.put("Blue", false);
        colorsUsed.put("Red", false);
        colorsUsed.put("Yellow", false);
        
        Map<Long,String> colorsAssigned = new HashMap<Long,String>();
        
        Collection<Vertex<Integer>> allVertex = graph.getAllVertex();
        
        for(Vertex<Integer> v : allVertex){
            List<Vertex<Integer>> adjacentVertexes = v.getAdjacentVertexes();
            for(Vertex<Integer> adjacentVertex : adjacentVertexes){
                String color = colorsAssigned.get(adjacentVertex.getId());
                if(color != null){
                    assignColor(color,colorsUsed);
                }
            }
            String color = getUnusedColor(colorsUsed);
            colorsAssigned.put(v.getId(), color);
            resetColor(colorsUsed);
        }
        
        System.out.println(colorsAssigned);
    }
    
    private String getUnusedColor(Map<String,Boolean> colorsUsed){
        for(String color : colorsUsed.keySet()){
            if(colorsUsed.get(color).equals(false)){
                return color;
            }
        }
        throw new RuntimeException();
    }
    
    private void resetColor(Map<String,Boolean> colorsUsed){
        Set<String> colors = new HashSet<String>();
        for(String color : colorsUsed.keySet()){
            colors.add(color);
        }
        for(String color : colors){
            colorsUsed.remove(color);
            colorsUsed.put(color, false);
        }
    }
    
    private void assignColor(String color, Map<String,Boolean> colorsUsed){
        colorsUsed.remove(color);
        colorsUsed.put(color, true);
    }
    
    private boolean allAdjacentUnColored(Collection<Vertex<Integer>> vertexes, Map<Long,String> colorsAssigned){
        for(Vertex<Integer> vertex : vertexes){
            if(colorsAssigned.containsKey(vertex.getId())){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]){
        GraphColoring graphColoring = new GraphColoring();
        graphColoring.WelshPowell();
    }
}


class ComparatorVertex implements Comparator<Vertex<Integer>>{

    @Override
    public int compare(Vertex<Integer> o1, Vertex<Integer> o2) {
        if(o1.getDegree() <= o2.getDegree()){
            return 1;
        }else{
            return -1;
        }
    }

}
