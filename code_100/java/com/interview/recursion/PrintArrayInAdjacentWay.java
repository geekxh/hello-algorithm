package com.interview.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-all-possible-interpretations/
 * This is class fibonacci series example.
 * e.g {1,1,1,1} -> when n = 4 total number of ways possible are
 * f(3) + f(2). 
 * Suppose we solved for n =3 . When we add another 1 we know that total number
 * of combinations without this 1 doing anything will be f(3). Not if we involve this new 1
 * and exclude f(3) we get total of f(2) because think of as if we combined last 2 ones and 
 * then see how many ways first 2 ones can combine
 * example 
 * {1,1,1, new1}
 * f(3) is all combinations without including new1
 * Now lets combine {1,1,newnew1} . This gives total of f(2)
 * Test cases
 * negative number
 * 0
 * null array
 */
public class PrintArrayInAdjacentWay {

    public void printArray(int len,int k){
        List<Integer> result = new ArrayList<Integer>();
        printArray(len,0,result,k);
    }
    
    private void printArray(int len, int pos,List<Integer> result,int k){
        if(pos > len){
            return;
        }
        if(pos == len){
            for(int i:result){
                System.out.print(i + " ");
            }
            System.out.println();
            return ;
        }
        
        for(int i=0; i < k ; i++){
            result.add(i+1);
            printArray(len,pos+i+1,result,k);
            result.remove(result.size()-1);
        }
    }
    
    public int numberOfWaysPossible(int input[],int pos){
        if(pos > input.length){
            return 0;
        }
        if(pos == input.length){
            return 1;
        }
        
        
        int count = numberOfWaysPossible(input,pos+1);
        if(pos + 1 < input.length){
            int num = input[pos]*10 + input[pos+1];
            if(num < 27){
                count += numberOfWaysPossible(input, pos+2);
            }
        }
        return count;
    }
    
    /**
     * Since we know this is same as fibonacci series all we have to do is either use sum of last two numbers if 
     * total is less than equal to 26 or use just last number if total is greater than 26
     * total is calculated by creating a number from current number and previous number
     * @param input
     * @return
     */
    public int numberOfWaysPossibleFaster(int input[]){
        int a0 = 1;
        int a1 = 1;
        int c = 0;
        for(int i=1; i < input.length; i++){
            if(input[i] + input[i-1]*10 <=26){
                c = a1 + a0;
            }else{
                c = a1;
            }
            a0 = a1;
            a1 = c;
        }
        return c;
    }
    
    public static void main(String args[]){
        PrintArrayInAdjacentWay paw = new PrintArrayInAdjacentWay();
    //  paw.printArray(5, 2);
        int input[] = {1,3,7,7,1,7,2,3,2};
        System.out.println(paw.numberOfWaysPossible(input, 0));
        System.out.println(paw.numberOfWaysPossibleFaster(input));
    }
}
