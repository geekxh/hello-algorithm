package com.interview.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/given-array-strings-find-strings-can-chained-form-circle/
 * Test cases:
 * Empty input
 * 1 element in input
 * no chain formed
 * chain formed
 */
public class ChainWordsToFormCircle {

    public List<String> formCircle(String input[]){
        List<String> result = new ArrayList<String>();
        //since chain is a circle any point can be the start point of the chain.
        //We make input[0] as start point
        result.add(input[0]);
        boolean used[] = new boolean[input.length];
        boolean r = formCircle(input,result,used,input[0].charAt(0));
        if(!r){
            return Collections.emptyList();
        }
        return result;
    }
    
    //we keep track of first char of the chain and the end compare that with last char of last element of the chain
    private boolean formCircle(String input[], List<String> result,boolean used[],char firstChar){
        if(input.length == result.size()){
            String str = result.get(result.size()-1);
            if(firstChar == str.charAt(str.length()-1)){
                return true;
            }
            return false;
        }
        String str = result.get(result.size()-1);
        char lastChar = str.charAt(str.length()-1);
        for(int i=1; i < input.length; i++){
            if(used[i]){
                continue;
         
            }
            if(lastChar == input[i].charAt(0)){
                used[i] = true;
                result.add(input[i]);
                boolean r = formCircle(input,result,used,firstChar);
                if(r){
                    return true;
                }
                used[i] = false;
                result.remove(result.size()-1);
            }
            
        }
        return false;
    }
    
    public static void main(String args[]){
        String notChaininput[] = {"geeks","king", "seeks", "sing","gik","kin"};
        String chainInput[] = {"king","gik","geeks", "seeks", "sing"};
        ChainWordsToFormCircle cwt = new ChainWordsToFormCircle();
        List<String> result = cwt.formCircle(chainInput);
        if(result.size() == 0){
            System.out.println("Not able to form a chain");
        }else{
            for(String chainNode : result){
                System.out.print(chainNode + " ");
            }
        }
    }

}
