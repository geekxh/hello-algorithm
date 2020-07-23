package com.interview.tree;

/**
 * Created by tushar_v_roy on 4/1/16.
 */
public class LongestConsecutiveSequence {
    int max = 0;
    public int longestConsecutive(Node root) {
        if (root == null) {
            return 0;
        }
        max = 0;
        longestConsecutiveUtil(root, root.data - 1, 0);
        return max;
    }

    public void longestConsecutiveUtil(Node root, int prevData, int current) {
        if (root == null) {
            return;
        }

        if (root.data == prevData + 1) {
            current = current + 1;
        } else {
            current = 1;
        }
        max = Math.max(current, max);
        longestConsecutiveUtil(root.left, root.data, current);
        longestConsecutiveUtil(root.right, root.data, current);
    }
}
