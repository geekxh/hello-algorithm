package com.interview.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * Date 01/07/2016
 * @author Tushar Roy
 *
 * Given an expression with +, -, * and / operators. Tell if expression can evaluate to given result with different
 * parenthesis combination.
 * e.g expresson is 2*3-1 and result is 4, function should return true since 2*(3-1) evaluate to 4.
 *
 * Time complexity is O(n^5)
 * Space complexity is O(n^3)
 */
public class ExpressionEvaluation {

    public boolean evaluate(char[] expression, int result) {
        int operands[] = new int[expression.length/2 + 1];
        char operators[] = new char[expression.length/2];

        int index1 = 0;
        int index2 = 0;
        operands[index1++] = expression[0] - '0';
        for (int i = 1; i < expression.length; i += 2 ) {
            operators[index2++] = expression[i];
            operands[index1++] = expression[i+1] - '0';
        }

        Holder T[][] = new Holder[operands.length][operands.length];

        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T.length; j++) {
                T[i][j] = new Holder();
            }
        }

        for (int i = 0; i < operands.length; i++) {
            T[i][i].add(operands[i]);
        }

        for (int l = 2; l <= T.length; l++) {
            for (int i = 0; i <= T.length - l; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    for (int x : T[i][k].values()) {
                        for (int y : T[k + 1][j].values()) {
                            if (operators[k] == '/' && y == 0) {
                                continue;
                            }
                            T[i][j].add(operate(operators[k], x, y));
                        }
                    }
                }
            }
        }

        T[0][T.length-1].values().forEach((i -> System.out.print(i + " ")));

        for (int i : T[0][T.length - 1].values()) {
            if ( i == result) {
                return true;
            }
        }

        return false;
    }

    private int operate(char operator, int x, int y) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x*y;
            case '/':
                return x/y;
            default:
                throw new IllegalArgumentException();
        }
    }
    static class Holder {
        private Set<Integer> valueHolder = new HashSet<>();
        void add(Integer ch) {
            valueHolder.add(ch);
        }
        Set<Integer> values() {
            return valueHolder;
        }
    }

    public static void main(String args[]) {
        ExpressionEvaluation ee = new ExpressionEvaluation();
        System.out.println(ee.evaluate("9*3+1/7".toCharArray(), 0));
    }
}


