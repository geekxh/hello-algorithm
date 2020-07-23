package com.interview.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Date 10/10/2016
 * @author Tushar Roy
 *
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * https://leetcode.com/problems/mini-parser/
 */
public class IntegerListParser {

    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack();

        NestedInteger current = null;
        StringBuffer subInteger = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                if (current != null) {
                    stack.push(current);
                }
                current = new NestedInteger();
                subInteger = new StringBuffer();
            } else if (s.charAt(i) == ']') {
                if (subInteger.length() > 0) {
                    current.add(new NestedInteger(Integer.parseInt(subInteger.toString())));
                    subInteger = new StringBuffer();
                }
                if (!stack.isEmpty()) {
                    NestedInteger top = stack.pop();
                    top.add(current);
                    current = top;
                }
            } else if (s.charAt(i) == ',') {
                if (subInteger.length() > 0) {
                    current.add(new NestedInteger(Integer.parseInt(subInteger.toString())));
                }
                subInteger = new StringBuffer();
            } else {
                subInteger.append(s.charAt(i));
            }
        }
        if (subInteger.length() > 0) {
            return new NestedInteger(Integer.parseInt(subInteger.toString()));
        }
        return current;
    }

    public String serialize(NestedInteger nestedInteger) {
        StringBuffer result = new StringBuffer();
        serialize(nestedInteger, result);
        return result.toString();
    }

    private void serialize(NestedInteger nestedInteger, StringBuffer result) {
        if (nestedInteger.isInteger()) {
            result.append(nestedInteger.getInteger());
            return;
        }
        boolean isFirst = true;
        result.append("[");
        for (NestedInteger ni : nestedInteger.getList()) {
            if (!isFirst) {
                result.append(",");
            }
            isFirst = false;
            if (ni.isInteger()) {
                result.append(ni.getInteger());
            } else {
                serialize(ni, result);
            }
        }
        result.append("]");
    }

    public static void main(String args[]) {
        IntegerListParser integerListParser = new IntegerListParser();
        NestedInteger nestedInteger = integerListParser.deserialize("123");
        String result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41,[1]]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[]]]]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[],[]]]],[],[]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[121,41,[1]],[2]]]],[2],[4]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
        nestedInteger = integerListParser.deserialize("[123,41,[[1,2,[],[]]]],[],[],[[1],[3]]");
        result = integerListParser.serialize(nestedInteger);
        System.out.println(result);
    }

    class NestedInteger {
        private List<NestedInteger> nestedInteger = new ArrayList<>();
        private Integer val;
        public NestedInteger() {

        }

        public NestedInteger(int value) {
            this.val = value;
        }

        public boolean isInteger() {
            return val != null;
        }

        public Integer getInteger() {
            return val;
        }

        public void setInteger(int value) {
            this.val = value;
        }

        public void add(NestedInteger ni) {
            this.nestedInteger.add(ni);
        }

        public List<NestedInteger> getList() {
            return val != null ? null : nestedInteger;
        }
    }

}
