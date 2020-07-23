package com.interview.tree;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * Time complexity O(n)
 * 
 * Reference
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class SortedArrayToBST {
    public Node sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private Node toBST(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high)/2;
        Node n = Node.newNode(nums[mid]);
        n.left = toBST(nums, low, mid - 1);
        n.right = toBST(nums, mid + 1, high);
        return n;
    }
}
