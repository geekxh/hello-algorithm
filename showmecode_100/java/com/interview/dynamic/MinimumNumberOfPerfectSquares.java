package com.interview.dynamic;

import java.util.*;

/**
 * Date 10/19/2016
 * @author Tushar Roy
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * Solution 1 - Using DP similar to coin change problem with infinite supply
 * Solution 2 - Using a BFS. Put all perfect squares in queue. Then considering each as a node try adding
 * another perfect square and see if we can get n. Keep doing this in BFS fashion till you hit the number.
 *
 * https://leetcode.com/problems/perfect-squares/
 */
public class MinimumNumberOfPerfectSquares {
    public int numSquaresUsingDP(int n) {
        int count = (int)Math.ceil(Math.sqrt(n));

        int[] T = new int[n + 1];

        T[0] = 0;

        for (int i = 1; i < T.length; i++) {
            T[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= count; j++) {
                if (i < j*j) {
                    break;
                }
                T[i] = Math.min(T[i], T[i - j*j] + 1);
            }
        }
        return T[n];
    }

    public int numSquaresUsingBFS(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        int i = 1;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        while (true) {
            int square = i * i;
            if (square == n) {
                return 1;
            }
            if (square > n) {
                break;
            }
            perfectSquares.add(square);
            queue.offer(square);
            visited.add(square);
            i++;
        }
        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int j = 0; j < size; j++) {
                int node = queue.poll();
                for (int square : perfectSquares) {
                    int sum = node + square;
                    if (sum == n) {
                        return distance;
                    } else if (!visited.contains(sum)) {
                        visited.add(sum);
                        queue.add(sum);
                    } else if (sum > n) {
                        break;
                    }
                }
            }
        }
        return distance;
    }
}
