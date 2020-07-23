package com.interview.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Next inorder iterator of two trees.
 * Merging tree to print one result
 */
public class NextInorderSuccessorOfTwoTree {

    private Node root1 = null;
    private Node root2 = null;
    Stack<Node> stack1 = new Stack<Node>();
    Stack<Node> stack2 = new Stack<Node>();
    Set<Node> visited = new HashSet<Node>();

    NextInorderSuccessorOfTwoTree(Node root1, Node root2) {
        this.root1 = root1;
        this.root2 = root2;
    }

    public boolean hasNext() {
        if (root1 != null || stack1.size() > 0 || root2 != null
                || stack2.size() > 0) {
            return true;
        }
        return false;
    }

    public Node next() {
        Node node = null;
        while (root1 != null) {
            stack1.push(root1);
            root1 = root1.left;
        }
        while (root2 != null) {
            stack2.push(root2);
            root2 = root2.left;
        }
        if (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peek().data <= stack2.peek().data) {
                node = stack1.pop();
                root1 = node.right;
            } else {
                node = stack2.pop();
                root2 = node.right;
            }
        } else if (stack1.isEmpty()) {
            node = stack2.pop();
            root2 = node.right;
        } else {
            node = stack1.pop();
            root1 = node.right;
        }
        return node;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node node = null;
        node = bt.addNode(10, node);
        node = bt.addNode(-5, node);
        node = bt.addNode(7, node);
        node = bt.addNode(20, node);
        node = bt.addNode(3, node);
        node = bt.addNode(14, node);
     
        Node node1 = null;
        node1 = bt.addNode(8, node1);
        node1 = bt.addNode(-10, node1);
        node1 = bt.addNode(18, node1);
        node1 = bt.addNode(2, node1);
        node1 = bt.addNode(11, node1);
        
        NextInorderSuccessorOfTwoTree nis = new NextInorderSuccessorOfTwoTree(node, node1);
        while(nis.hasNext()){
            System.out.println(nis.next().data);
        }
        
    }

}
