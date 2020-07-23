package com.interview.dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Date 01/06/2016
 * @author Tushar Roy
 * Let there be a binary operation for 3 symbols a, b, c and result of these binary operation given in a table.
 * Given an expression of these 3 symbols and a final result, tell if this expression can be parenthesize in certain
 * way to produce the final result.
 *
 * Time complexity is O(n^4)
 * Space complexity is O(n^3)
 */
public class SymbolExpressionEvaluation {

    public boolean evaluateExpression(char input[][], Map<Character, Integer> index, char[] expression, char result) {
        Holder[][] T = new Holder[expression.length][expression.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T.length; j++) {
                T[i][j] = new Holder();
            }
        }

        for (int i = 0; i < expression.length; i++) {
            T[i][i].add(expression[i]);
        }

        for (int l = 2; l <= T.length; l++) {
            for (int i = 0; i <= T.length - l; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    for (char ch : T[i][k].values()) {
                        for (char ch1: T[k+1][j].values()) {
                            T[i][j].add(input[index.get(ch)][index.get(ch1)]);
                        }
                    }
                }
            }
        }

        for (char ch : T[0][T.length-1].values()) {
            if ( result == ch) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Map<Character, Integer> index = new HashMap<>();
        index.put('a', 0);
        index.put('b', 1);
        index.put('c', 2);

        char input[][] = {{'b', 'b', 'a'}, {'c', 'b', 'a'}, {'a', 'c', 'c'}};
        SymbolExpressionEvaluation sbe = new SymbolExpressionEvaluation();
        System.out.println(sbe.evaluateExpression(input, index, "bbbbac".toCharArray(), 'a'));
    }

}

class Holder {
    private Set<Character> valueHolder = new HashSet<>();
    void add(Character ch) {
        valueHolder.add(ch);
    }
    Set<Character> values() {
        return valueHolder;
    }
}

