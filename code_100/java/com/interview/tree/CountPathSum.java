package com.interview.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/description/
 */
public class CountPathSum {
    public int pathSum(Node root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return countPathSum(root, sum, map, 0);
    }

    private int countPathSum(Node root, int sum, Map<Integer, Integer> map, int prefixSum) {
        if (root == null) {
            return 0;
        }

        prefixSum += root.data;
        int count = map.getOrDefault(prefixSum - sum,0);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        count += countPathSum(root.left, sum, map, prefixSum) + countPathSum(root.right, sum, map, prefixSum);
        map.put(prefixSum, map.getOrDefault(prefixSum, 1) - 1);
        return count;
    }
}

