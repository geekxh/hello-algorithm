package com.interview.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/ Assumption
 * that both a and b are unique in tree. Test cases: Empty tree Tree with only
 * root Tree and input with a and b as cousin node Tree and input with a and b
 * not cousin node Tree with input a and b being siblings(not cousin)
 */
public class CousinNodes {

    /**
     * Little more efficient than other method since this guy does not need
     * another look up to see if parent is same for found nodes.
     */
    public boolean areCousins1(Node root, int data1, int data2) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int tempLevel = 1;
        int level = 0;
        boolean found1 = false;
        boolean found2 = false;
        Node parent1 = null;
        Node parent2 = null;
        while (!queue.isEmpty()) {
            while (tempLevel > 0) {
                root = queue.poll();
                if (root.left != null) {
                    if (root.left.data == data1) {
                        found1 = true;
                        parent1 = root;
                    } else if (root.left.data == data2) {
                        found2 = true;
                        parent2 = root;
                    } else {
                        queue.add(root.left);
                        level++;
                    }
                }
                if (root.right != null) {
                    if (root.right.data == data1) {
                        found1 = true;
                        parent1 = root;
                    } else if (root.right.data == data2) {
                        found2 = true;
                        parent2 = root;
                    } else {
                        queue.add(root.right);
                        level++;
                    }
                }
                tempLevel--;
            }
            if (found1 && found2 && parent1 != parent2) {
                return true;
            } else if (found1 || found2) {
                return false;
            }
            tempLevel = level;
            level = 0;
        }
        return false;
    }

    public boolean areCousins(Node root, int a, int b) {
        Deque<Node> queue = new LinkedList<Node>();
        queue.offerFirst(root);
        int levelSize = 1;
        int tempLevelSize = 1;
        boolean foundFirst = false;
        while (!queue.isEmpty()) {
            levelSize = 0;
            while (tempLevelSize > 0) {
                Node node = queue.pollLast();
                // this is to make sure a and b are not siblings of each other
                // if they are return false since they cant be cousins
                if (checkSameParent(node, a, b)) {
                    return false;
                }
                if (node.data == a || node.data == b) {
                    if (foundFirst) {
                        return true;
                    }
                    foundFirst = true;
                }
                if (node.left != null) {
                    queue.offerFirst(node.left);
                    levelSize++;
                }
                if (node.right != null) {
                    queue.offerFirst(node.right);
                    levelSize++;
                }
                tempLevelSize--;
            }
            if (foundFirst) {
                return false;
            }
            tempLevelSize = levelSize;
        }
        return false;
    }

    private boolean checkSameParent(Node root, int a, int b) {
        if (root.left != null && root.right != null) {
            if ((root.left.data == a || root.left.data == b)
                    && (root.right.data == a || root.right.data == b)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        head = bt.addNode(-6, head);
        CousinNodes cn = new CousinNodes();
        System.out.println(cn.areCousins(head, 10, 19));
        System.out.println(cn.areCousins(head, 19, 7));
        System.out.println(cn.areCousins(head, 19, -1));
        System.out.println(cn.areCousins(head, 19, -6));
        System.out.println(cn.areCousins(head, -1, 7));
        System.out.println(cn.areCousins(head, 7, -1));
    }
}
