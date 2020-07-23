package com.interview.bits;

import java.util.*;

/**
 * Date 03/08/2016
 * @author Tushar Roy
 *
 * Find repeated sequence of length 10 in string consisting of A,C,G,T
 *
 * Time complexity is O(n)
 * Space complexity is O(n)
 *
 * https://leetcode.com/problems/repeated-dna-sequences/
 */
public class RepeatedDnaSequence {
    private static final int mask = 0xFFFFF;
    private static final int mask1 = 3;


    public List<String> findRepeatedDnaSequences(String s) {

        if (s.length() < 10) {
            return Collections.emptyList();
        }

        Set<Integer> set = new HashSet<>();
        int val = 0;
        for (int i = 0; i < 10; i++) {
            val = add(val, s.charAt(i));
        }
        set.add(val);
        List<String> result = new ArrayList<>();
        createString(val);

        Set<Integer> repeatSet = new HashSet<>();

        for (int i = 10; i < s.length(); i++) {
            val = add(val, s.charAt(i));
            if (set.contains(val) && !repeatSet.contains(val)) {
                result.add(createString(val));
                repeatSet.add(val);
            } else {
                set.add(val);
            }
        }

        return result;
    }

    private String createString(int input) {
        StringBuffer sb = new StringBuffer();
        for (int i = 9; i >= 0; i--) {
            sb.append(getChar(input>>(i*2) & mask1));
        }
        return sb.toString();
    }

    private int add(int input, char ch) {
        int val = getVal(ch);
        input = input<<2;
        input = input & mask;
        return input | val;
    }

    private int getVal(char ch) {
        switch(ch) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                throw new IllegalArgumentException();
        }
    }

    private char getChar(int val) {
        switch (val) {
            case 0:
                return 'A';
            case 1:
                return 'C';
            case 2:
                return 'G';
            case 3:
                return 'T';
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String args[]) {
        RepeatedDnaSequence rds = new RepeatedDnaSequence();
        List<String> result = rds.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.print(result);
    }
}
