package com.interview.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * http://www.glassdoor.com/Interview/Create-an-iterator-to-traverse-a-binary-tree-When-the-next-function-is-called-on-the-binary-tree-return-the-value-at-the-QTN_674695.htm
 * null tree.
 */
public class NextInorderSuccessorIterator {

    private Node root = null;
    Stack<Node> stack = new Stack<Node>();
    Set<Node> visited = new HashSet<Node>();
    NextInorderSuccessorIterator(Node root){
        this.root = root;
    }
    
    public int next(){
        Node node = null;
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        node = root;
        root = root.right;
        return node.data;
    }
    
    public boolean hasNext(){
        if(root != null || stack.size() > 0){
            return true;
        }
        return false;
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
        NextInorderSuccessorIterator nis = new NextInorderSuccessorIterator(node);
        while(nis.hasNext()){
            System.out.println(nis.next());
        }
    }
}
