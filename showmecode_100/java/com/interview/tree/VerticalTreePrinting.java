package com.interview.tree;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

/**
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 */
public class VerticalTreePrinting {

    public void printVertical(Node root){
        Map<Integer,List<Node>> map = new TreeMap<Integer,List<Node>>();
        populateMap(root,map,0);
        printLevel(map);
    }
    
    private void printLevel(Map<Integer,List<Node>> map){
        for(Integer key : map.keySet()){
            List<Node> listNodes = map.get(key);
            for(Node n : listNodes){
                System.out.print(n.data + " ");
            }
            System.out.println();
        }
    }
    
    private void populateMap(Node root, Map<Integer,List<Node>> map,int level){
        if(root == null){
            return;
        }
        List<Node> listNodes = null;
        if(map.containsKey(level)){
            listNodes = map.get(level);
        }else{
            listNodes = new ArrayList<Node>();
            map.put(level, listNodes);
        }
        listNodes.add(root);
        populateMap(root.left,map,level-1);
        populateMap(root.right,map,level+1);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(3, head);
        head = bt.addNode(-6, head);
        head = bt.addNode(-7, head);
        head = bt.addNode(2, head);
        head = bt.addNode(9, head);
        head = bt.addNode(6, head);
        head = bt.addNode(11, head);
        head = bt.addNode(13, head);
        head = bt.addNode(12, head);
        VerticalTreePrinting vtp = new VerticalTreePrinting();
        vtp.printVertical(head);
    }
}
