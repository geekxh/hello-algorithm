package com.interview.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-all-possible-interpretations/
 */
public class InterpretationOfArray {

    public void interpret(int arr[]){
        char t[][] = new char[arr.length][2];
        for(int i=0; i < arr.length; i++){
            for(int j=0; j < 2; j++){
                t[i][j] = '0';
            }
        }
        for(int l=1; l <=2; l++){
            for(int i=0; i <= arr.length -l ; i++){
                int j = i + l-1;
                t[i][l-1] = getRepresentation(i == j ? arr[i] : arr[i]*10 +arr[j]);
            }
        }
        List<Character> result = new ArrayList<Character>();
        interpret(arr.length,0,result,t);
    }
    
    private void interpret(int len,int pos,List<Character> result,char[][] t){
        if(pos== len){
            print(result);
            return;
        }
        if(t[pos][0] != '0'){
            result.add(t[pos][0]);
            interpret(len,pos+1,result,t);
            result.remove(result.size()-1);
        }
        
        if(pos+1 < len && t[pos][1] != '0'){
            result.add(t[pos][1]);
            interpret(len, pos+2, result, t);
            result.remove(result.size()-1);
        }
            
    }
    
    private void print(List<Character> result){
        for(int i=0; i < result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }
    
    private char getRepresentation(int number){
        if(number > 26 || number <= 0){
            return '0';
        }
        return (char)('a' + number -1);
    }
    
    public static void main(String args[]){
        int arr[] = {1,2,6,1,7};
        InterpretationOfArray ioa = new InterpretationOfArray();
        ioa.interpret(arr);
    }
    
}
