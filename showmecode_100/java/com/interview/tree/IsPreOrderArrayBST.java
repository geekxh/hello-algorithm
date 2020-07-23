package com.interview.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Check if given pre array sequence will create a binary search tree or not.
 *
 * Idea is to keep minimal acceptable value. Any element in pre array should never be less
 * than minimal acceptable value. Values are popped from stack till they are less than pre[i]
 * Last popped value becomes new minimally acceptable vaue.
 *
 * It does not work for duplicates
 *
 * Time complexity - O(n)
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * Reference - http://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
 */
public class IsPreOrderArrayBST {

    public boolean verifyPreorder(int pre[]) {
        Deque<Integer> deque = new LinkedList<>();
        int minAcceptable = Integer.MIN_VALUE;
        deque.addFirst(pre[0]);
        for(int i = 1; i < pre.length; i++) {
            if(pre[i] < minAcceptable) {
                return false;
            }
            while(!deque.isEmpty() && deque.peekFirst() < pre[i]) {
                minAcceptable = deque.pollFirst();
            }
            deque.addFirst(pre[i]);
        }
        return true;
    }

    public boolean verifyPreorderConstantSpace(int[] preorder) {
        int start = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] < min) {
                return false;
            }
            if (preorder[i] > preorder[i - 1]) {
                int index = binarySearch(preorder, start, i - 1, preorder[i]);
                min = preorder[index];
                start = i;
            }
        }
        return true;
    }

    int binarySearch(int[] preorder, int start, int end, int val) {
        int s = start;
        int e = end;
        while (s <= e) {
            int middle = (s + e)/2;
            if (preorder[middle] < val && (start == middle || preorder[middle - 1] > val)) {
                return middle;
            } else if (preorder[middle] < val) {
                e = middle - 1;
            } else {
                s = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        IsPreOrderArrayBST isb = new IsPreOrderArrayBST();
        int[] input = {10, 3, -1, 4, 13, 11, 12, 10};
        System.out.println(isb.verifyPreorder(input));
        System.out.println(isb.verifyPreorderConstantSpace(input));
    }
}
