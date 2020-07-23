package com.interview.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/path-sum/
 * https://leetcode.com/problems/path-sum-ii/
 */
public class PathSum {
    public List<List<Integer>> pathSum(Node root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumUtil(root, sum, result, current);
        return result;
    }

    private void pathSumUtil(Node root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.data == sum) {
                currentPath.add(root.data);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
            return;
        }
        currentPath.add(root.data);
        pathSumUtil(root.left, sum - root.data, result, currentPath);
        pathSumUtil(root.right, sum - root.data, result, currentPath);
        currentPath.remove(currentPath.size() - 1);
    }

    public boolean hasPathSum(Node root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.data == sum;
        }
        return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
    }
}
