package com.interview.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/custom-tree-problem/
 */
public class PrintArrayInCustomizedFormat {

    void print(char str[][]){
        Map<Character,Boolean> visited = new HashMap<Character,Boolean>();
        Map<Character,Boolean> alreadyPrinted = new HashMap<Character,Boolean>();
        for(int i=0 ; i < str.length; i++){
            if(!visited.containsKey(str[i][0]) || !visited.get(str[i][0])){
                
                if(!alreadyPrinted.containsKey(str[i][0]) || !alreadyPrinted.get(str[i][0])){
                    System.out.println(str[i][0]);
                    alreadyPrinted.put(str[i][0],true);
                }
                DFS(str,i,5,visited);
            }
        }
    }
    
    private void DFS(char str[][],int pos,int distance,Map<Character,Boolean> visited){
        for(int i=0; i < distance; i++){
            System.out.print(" ");
        }
        System.out.println(str[pos][1]);
        char ch = str[pos][1];
        visited.put(ch, true);
        int i = pos+1;
        for(; i < str.length; i++){
            if(ch == str[i][0]){
                if(i != str.length){
                    DFS(str,i,distance + 5,visited);
                }
            }
        }
    }
    
    public static void main(String args[]){
        char str[][] = {{'a','b'},{'a','c'},{'b','d'},{'c','f'},{'b','e'},{'x','y'},{'y','z'}};
        
        PrintArrayInCustomizedFormat pac = new PrintArrayInCustomizedFormat();
        pac.print(str);
    }
}
