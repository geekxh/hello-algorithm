package com.interview.tree;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 03/12/2016
 * @author Tushar Roy
 *
 * Serialize/Deserialize a binary tree whose data is a number.
 *
 *  Time complexity O(n)
 *  Space complexity O(n)
 *
 * Reference
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeDeserializeBinaryTree {

    /**
     * Serialize Tree using preorder DFS
     * @param root
     * @return
     */
    public String serialize(Node root) {
        StringBuffer buff = new StringBuffer();
        serializeUtil(root, buff);
        return buff.toString();
    }

    private void serializeUtil(Node root, StringBuffer buff) {
        if (root == null) {
            buff.append("%,");
            return;
        }

        buff.append(root.data).append(",");
        if (root.left != null || root.right != null) {
            buff.append("$,");
            serializeUtil(root.left, buff);
            serializeUtil(root.right, buff);
        } else {
            return;
        }

    }
    int index = 0;

    /**
     * Deserialize Tree using preorder DFS
     * @param data
     * @return
     */
    public Node deserialize(String data) {
        String[] input = data.split(",");
        index = 0;
        return deserializeUtil(input);
    }

    private Node deserializeUtil(String input[]) {
        if (index == input.length) {
            return null;
        }

        if (input[index].equals("%")) {
            index++;
            return null;
        }
        Node n = new Node();
        n.data = Integer.parseInt(input[index]);
        if (index < input.length - 1) {
            if (input[index + 1].equals("$")) {
                index += 2;
                n.left = deserializeUtil(input);
                n.right = deserializeUtil(input);
            } else {
                index++;
            }
        }
        return n;
    }

    /**
     * Serialize tree using level order traversal.
     */
    public String serializeLevelOrder(Node root) {
        if (root == null) {
            return "";
        }

        Deque<Node> queue = new LinkedList<>();
        queue.offerFirst(root);
        StringBuffer buff = new StringBuffer();
        while (!queue.isEmpty()) {
            root = queue.pollFirst();
            if (root == null) {
                buff.append("%,");
            } else {
                buff.append(root.data).append(",");
                queue.offer(root.left);
                queue.offer(root.right);
            }
        }
        for (int i = buff.length() - 1; i >= 0; i--) {
            if (buff.charAt(i) == '%' || buff.charAt(i) == ',') {
                buff.deleteCharAt(i);
            } else {
                break;
            }
        }
        return buff.toString();
    }

    /**
     * Deserialize Tree using level order traversal.
     */
    public Node deserializeLevelOrder(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] input = data.split(",");
        Deque<Node> queue = new LinkedList<>();
        int index = 0;
        queue.offerFirst(Node.newNode(Integer.parseInt(input[index])));
        Node root = queue.peekFirst();
        index++;
        while (!queue.isEmpty()) {
            Node current = queue.pollFirst();
            if (index < input.length && !input[index].equals("%")) {
                current.left = Node.newNode(Integer.parseInt(input[index]));
                queue.offerLast(current.left);
            }
            index++;
            if (index < input.length && !input[index].equals("%")) {
                current.right = Node.newNode(Integer.parseInt(input[index]));
                queue.offerLast(current.right);
            }
            index++;
        }
        return root;
    }


    public static void main(String args[]) {
        SerializeDeserializeBinaryTree sd = new SerializeDeserializeBinaryTree();
        Node node = sd.deserialize("10,$,30,15,$,%,20,$,21,16,$,%,18");
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(node);
        String serializedTree= sd.serializeLevelOrder(node);
        Node root = sd.deserializeLevelOrder("1,2");
        tt.inOrder(root);
    }
}

