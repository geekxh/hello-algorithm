package com.interview.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 */
public class GetKthPermutation {

    public String getPermutation(int n, int k) {
        List<Integer> unused = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            unused.add(i);
        }
        StringBuffer result = new StringBuffer();
        int fac = factorial(n);
        int d = n;
        while (n > 1) {
            fac = fac/d;
            d--;
            int index = (int)Math.ceil((double)k/fac);
            result.append(unused.get(index - 1));
            unused.remove(index - 1);
            n--;
            k = k - fac*(index - 1);
        }
        if (n == 0) {
            for (int i = unused.size() - 1; i >= 0; i--) {
                result.append(unused.get(i));
            }
        }

        if (n == 1) {
            for (int i = 0; i < unused.size(); i++) {
                result.append(unused.get(i));
            }
        }
        return result.toString();
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String args[]) {
        GetKthPermutation gp = new GetKthPermutation();
        System.out.println(gp.getPermutation(6, 343));
    }
}
