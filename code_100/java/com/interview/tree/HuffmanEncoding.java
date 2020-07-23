package com.interview.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding-set-2/
 */
class HuffmanNode{
    int freq;
    char input;
    HuffmanNode left;
    HuffmanNode right;
}

class HuffmanNodeComparator implements Comparator<HuffmanNode>{

    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        if(o1.freq < o2.freq){
            return -1;
        }else{
            return 1;
        }
    }
    
}

public class HuffmanEncoding {

    public Map<Character,String> huffman(char[] input, int freq[]){
        HuffmanNodeComparator comparator = new HuffmanNodeComparator();
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<HuffmanNode>(input.length,comparator);
        for(int i=0; i < input.length; i++){
            HuffmanNode node = new HuffmanNode();
            node.freq = freq[i];
            node.input = input[i];
            heap.offer(node);
        }
    
        while(heap.size() > 1){
            HuffmanNode node1 = heap.poll();
            HuffmanNode node2 = heap.poll();
            HuffmanNode node = new HuffmanNode();
            node.left = node1;
            node.right = node2;
            node.freq = node1.freq + node2.freq;
            heap.offer(node);
        }
        
        Map<Character,String> map = new HashMap<Character,String>();
        StringBuffer buff = new StringBuffer();
        createCode(heap.poll(),map,buff);
        return map;
        
    }
    
    public void createCode(HuffmanNode node,Map<Character,String> map,StringBuffer buff){
        if(node == null){
            return;
        }
        
        if(node.left == null && node.right == null){
            map.put(node.input,buff.toString());
            return;
        }
        buff.append("0");
        createCode(node.left,map,buff);
        buff.deleteCharAt(buff.length()-1);
        buff.append("1");
        createCode(node.right,map,buff);
        buff.deleteCharAt(buff.length()-1);
    }
    
    
    public static void main(String args[]){
        HuffmanEncoding he = new HuffmanEncoding();
        char input[] = {'a','b','c','d','e','f'};
        int freq[] = {5,9,12,13,16,45};
        Map<Character,String> code = he.huffman(input, freq);
        System.out.println(code);
    }
    
}
