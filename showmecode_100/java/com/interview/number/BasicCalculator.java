package com.interview.number;

import java.util.Stack;

/**
 * Date 10/11/2016
 * @author Tushar Roy
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
 * The integer division should truncate toward zero.
 *
 * Time complexity O(n)
 * Space complexity (n)
 *
 * https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> operand = new Stack<>();
        int current = 0;
        char prevOperator = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                current = current * 10 + ch - '0';
            }
            //if its not a digit or space then go in this block.
            //also if it is last character then go in this block and finish up last operation.
            if (i == s.length() - 1 || (ch != ' ' && !Character.isDigit(ch))) {
                if (prevOperator == '+') {
                    operand.push(current);
                } else if (prevOperator == '-') {
                    operand.push(-current);
                } else if (prevOperator == '/') {
                    operand.push(operand.pop() / current);
                } else {
                    operand.push(operand.pop() * current);
                }
                prevOperator = ch;
                current = 0;
            }
        }
        int result = 0;
        while (!operand.isEmpty()) {
            result += operand.pop();
        }
        return result;
    }
}
