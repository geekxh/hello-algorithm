package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Date 04/18/2015
 * @author tusroy
 * 
 * Video link - https://youtu.be/7uG0gLDbhsI
 * 
 * Given a binary tree print each level on new line.
 * 
 * e.g           10
 *           5         15
 *         0   -1    2    6
 * Output :   10
 *            5 15
 *            0 -1 2 6
 *            
 * Solution
 * Technique 1:
 * Use 2 queue. Keep polling from q1 and offer to q2 till q1 is empty. 
 * After that print a new line. Keep polling from q2 and offer to q1 
 * till q2 is empty. Keep doing this still both q1 and q2 are empty.
 * 
 * Technique 2
 * Use one queue with delimiter. Add a delimiter null after every level.
 * As soon as you encounter a null print a new line and add null at end of queue
 * 
 * Technique 3
 * Use one queue with count. Keep count of nodes at every level. As soon as this 
 * count is 0 print a new line.
 * 
 * Time space complexity for all above algorithm is O(n).
 */
public class TreeTraversalLevelByLevel {

    /**
     * Use two queue to print level by level
     */
    public void levelByLevelTwoQueue(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.add(root);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            while (!q1.isEmpty()) {
                root = q1.poll();
                System.out.print(root.data + " ");
                if (root.left != null) {
                    q2.offer(root.left);
                }
                if (root.right != null) {
                    q2.offer(root.right);
                }
            }
            System.out.println();
            while (!q2.isEmpty()) {
                root = q2.poll();
                System.out.print(root.data + " ");
                if (root.left != null) {
                    q1.offer(root.left);
                }
                if (root.right != null) {
                    q1.offer(root.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * Use one queue and delimiter to print level by level
     */
    public void levelByLevelOneQueueUsingDelimiter(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);
        q.offer(null);
        while (!q.isEmpty()) {
            root = q.poll();
            if (root != null) {
                System.out.print(root.data + " ");
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            } else {
                if (!q.isEmpty()) {
                    System.out.println();
                    q.offer(null);
                }
            }
        }
    }

    /**
     * Use one queue and count to print level by level
     */
    public void levelByLevelOneQueueUsingCount(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        int levelCount = 1;
        int currentCount = 0;
        q.offer(root);
        while (!q.isEmpty()) {
            while (levelCount > 0) {
                root = q.poll();
                System.out.print(root.data + " ");
                if (root.left != null) {
                    currentCount++;
                    q.offer(root.left);
                }
                if (root.right != null) {
                    currentCount++;
                    q.offer(root.right);
                }
                levelCount--;
            }
            System.out.println();
            levelCount = currentCount;
            currentCount = 0;
        }
    }

    public static void main(String args[]) {
        TreeTraversalLevelByLevel tt = new TreeTraversalLevelByLevel();
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(30, root);
        root = bt.addNode(15, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(0, root);
        root = bt.addNode(5, root);
        root = bt.addNode(-5, root);
        root = bt.addNode(-15, root);
        root = bt.addNode(27, root);
        root = bt.addNode(35, root);
        System.out.println("1. Two queue technique");
        tt.levelByLevelTwoQueue(root);
        System.out.println("\n2. One queue and delimiter");
        tt.levelByLevelOneQueueUsingDelimiter(root);
        System.out.println("\n\n3. One queue and count");
        tt.levelByLevelOneQueueUsingCount(root);
    }
}
