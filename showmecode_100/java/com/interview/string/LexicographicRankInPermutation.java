package com.interview.string;

public class LexicographicRankInPermutation {

    //you can create a AVL tree to efficiently find total
    //number of smaller characters.
    //You can keep size of subtree at root and keep moving left or right
    //depending on the character you looking for
    private int findNumberOfSmallerCharactersOnRight(int index,char []str){
        int count=0;
        for(int i=index+1; i < str.length; i++){
            if(str[i] < str[index]){
                count++;
            }
        }
        return count;
    }
    
    private int factorial(int n){
        int fact = 1;
        for(int i =1; i <=n; i++){
            fact = i*fact;
        }
        return fact;
    }
    
    public int rank(char []str){
        
        int rank =0;
        for(int i=0; i < str.length;i++){
            int num = findNumberOfSmallerCharactersOnRight(i, str);
            rank += factorial(str.length -i-1)*num;
        }
        return rank+1;
        
    }
    
    public static void main(String args[]){
        LexicographicRankInPermutation lrp = new LexicographicRankInPermutation();
        int rank = lrp.rank("STRING".toCharArray());
        System.out.println(rank);
    }
}
