package com.interview.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Date 10/08/2016
 * @author Tushar Roy
 *
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Time complexity O(log(n) + k)
 *
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class KClosestValueInBinaryTree {

    public List<Integer> closestKValues(Node root, double target, int k) {
        if (root == null || k == 0) {
            return new ArrayList<>();
        }

        Stack<Node> predecessor = new Stack();
        Stack<Node> successor = new Stack();

        double closestDiff = Double.MAX_VALUE;
        Node closestDiffNode = null;
        while (root != null) {
            predecessor.push(root);
            successor.push(root);
            if (Math.abs(target - root.data) < closestDiff) {
                closestDiff = Math.abs(target - root.data);
                closestDiffNode = root;
            }
            if (root.data == target) {
                break;
            }

            else if (target > root.data) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        while (predecessor.peek() != closestDiffNode) {
            predecessor.pop();
            successor.pop();
        }
        predecessor.pop();
        successor.pop();
        List<Integer> result = new ArrayList<>();
        result.add(closestDiffNode.data);
        Node prec = closestDiffNode;
        Node succ = closestDiffNode;
        k--;
        prec = predecessor(predecessor, prec);
        succ = successor(successor, succ);
        while (k > 0) {
            if (succ == null || (prec != null && Math.abs(target - prec.data) < Math.abs(target - succ.data))) {
                result.add(prec.data);
                prec = predecessor(predecessor, prec);
            } else {
                result.add(succ.data);
                succ = successor(successor, succ);
            }
            k--;
        }
        return result;
    }

    private Node predecessor(Stack<Node> stack, Node current) {
        if (current == null) {
            return null;
        }
        if (current.left != null) {
            stack.push(current);
            current = current.left;
            while (current.right != null) {
                stack.push(current);
                current = current.right;
            }
            return current;
        } else {
            while (!stack.isEmpty() && stack.peek().left == current) {
                current = stack.pop();
            }
            if (stack.isEmpty()) {
                return null;
            } else {
                return stack.pop();
            }
        }
    }

    private Node successor(Stack<Node> stack, Node current) {
        if (current == null) {
            return null;
        }
        if (current.right != null) {
            stack.push(current);
            current = current.right;
            while (current.left != null) {
                stack.push(current);
                current = current.left;
            }
            return current;
        } else {
            while (!stack.isEmpty() && stack.peek().right == current) {
                current = stack.pop();
            }
            if (stack.isEmpty()) {
                return null;
            } else {
                return stack.pop();
            }
        }
    }
}
