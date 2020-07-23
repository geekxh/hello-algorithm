package com.interview.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 07/20/2015
 * @author Tushar Roy
 *
 * Given a list of list of Strings. Print cartesian product of lists.
 * input -> {"Hello", "World"} , {"Game"}, {"Go","Home"}
 * output ->
 * Hello Game Go
 * Hellow Game Home
 * World Game Go
 * World Game Home
 */
public class WordCombination {

    public void printCombinations(List<List<String>> input) {
        int[] result = new int[input.size()];
        print(input,result, 0);
    }

    private void print(List<List<String>> input, int[] result, int pos) {

        if(pos == result.length){
            for (int i = 0; i < input.size(); i++) {
                System.out.print(input.get(i).get(result[i]) + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i < input.get(pos).size(); i++){
            result[pos] = i;
            print(input,result, pos+1);

        }
    }

    public static void main(String args[]){
        List<String> l1 = new ArrayList<>();
        l1.add("quick");
        l1.add("slow");

        List<String> l2 = new ArrayList<>();
        l2.add("brown");
        l2.add("red");

        List<String> l3 = new ArrayList<>();
        l3.add("fox");
        l3.add("dog");

        List<List<String>> input = new ArrayList<>();
        input.add(l1);
        input.add(l2);
        input.add(l3);

        WordCombination wc = new WordCombination();
        wc.printCombinations(input);

    }
}
