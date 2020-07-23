package com.interview.recursion;

import java.util.Deque;
import java.util.LinkedList;

public class Bracketology {

    public boolean matchBracket(char str[],int openCount,int pos){
        
        if(str.length == pos){
            if(openCount == 0){
                return true;
            }
            return false;
        }
        
        if(str[pos] == '('){
            openCount++;
        }else{
            openCount--;
        }
        if(openCount < 0){
            return false;
        }
        
        return matchBracket(str,openCount,pos+1);
        
    }
    
    private void printArray(char result[]){
        for(int i=0; i < result.length; i++){
            System.out.print(result[i]);
        }
        System.out.println();
    }
    
    public void bracketPermutation(char result[],int n, int pos,int openCount,int closeCount){
        if(pos == 2*n){
            printArray(result);
            return;
        }
        if(openCount < n){
            result[pos] = '(';
            bracketPermutation(result, n, pos+1,openCount+1,closeCount);
        }
        if(closeCount < openCount){
            result[pos] = ')';
            bracketPermutation(result, n, pos+1, openCount, closeCount+1);
        }
    }
    
    public boolean matchBracket(char []brackets){
        Deque<Character> stack = new LinkedList<Character>();
        
        for(Character ch : brackets){
            char checkChar = getOpeningCharacter(ch);
            if(checkChar == 0){
                stack.addFirst(ch);
            }else{
                if(stack.size() == 0 || stack.peek() != checkChar){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        if(stack.size() > 0){
            return false;
        }
        return true;
        
    }
    
    private Character getOpeningCharacter(char ch){
        switch(ch){
            case ')' : return '(';
            case ']' : return '[';
            case '}' : return '{';
            default : return 0;
        }
        
    }

    
    public static void main(String args[]){
        
        Bracketology matcher = new Bracketology();
        //System.out.print(matcher.matchBracket("(())())".toCharArray(), 0, 0));
        int n=4;
        char result[] = new char[n*2];
//      matcher.bracketPermutation(result, n, 0, 0, 0);
        
        System.out.println(matcher.matchBracket("[({()}{}[])]".toCharArray()));
    }
    
    
}
