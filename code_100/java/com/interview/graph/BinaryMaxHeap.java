package com.interview.graph;

import java.util.ArrayList;
import java.util.List;

public class BinaryMaxHeap<T> {

    private List<Node> allNodes = new ArrayList<Node>();

    class Node {
        int weight;
        T data;
    }

    public void add(int weight,T data) {

        Node node = new Node();
        node.weight = weight;
        node.data = data;
        allNodes.add(node);
        int size = allNodes.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;

        while (parentIndex >= 0) {
            Node parentNode = allNodes.get(parentIndex);
            Node currentNode = allNodes.get(current);
            if (parentNode.weight < currentNode.weight) {
                swap(parentNode,currentNode);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }

    }

    private void swap(Node node1,Node node2){
        int weight = node1.weight;
        T data = node1.data;
        
        node1.data = node2.data;
        node1.weight = node2.weight;
        
        node2.data = data;
        node2.weight = weight;
    }
    
    public T max(){
        return allNodes.get(0).data;
    }
    
    public boolean empty(){
        return allNodes.size() == 0;
    }
    
    
    public T extractMap(){
        int size = allNodes.size() -1;
        T max = allNodes.get(0).data;
        int lastNodeWeight = allNodes.get(size).weight;
        allNodes.get(0).weight = lastNodeWeight;
        allNodes.get(0).data = allNodes.get(size).data;
        allNodes.remove(size);
        
        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int largerIndex = allNodes.get(left).weight >= allNodes.get(right).weight ? left : right;
            if(allNodes.get(currentIndex).weight < allNodes.get(largerIndex).weight){
                swap(allNodes.get(currentIndex),allNodes.get(largerIndex));
                currentIndex = largerIndex;
            }else{
                break;
            }
        }
        return max;
    }
    
    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.weight + " " + n.data);
        }
    }
    
    public static void main(String args[]){
        BinaryMaxHeap<String> heap = new BinaryMaxHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.printHeap();
    }
}
