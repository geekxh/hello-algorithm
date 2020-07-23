package com.interview.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Date 11/01/2015
 *  @author Tushar
 *
 *  Encode and decode binary tree using succinct encoding technique
 *
 * References - http://www.geeksforgeeks.org/succinct-encoding-of-binary-tree/
 * https://en.wikipedia.org/wiki/Binary_tree#Succinct_encodings
 */
public class SuccinctTree {

    public static class Result {
        List<Integer> binaryRep = new ArrayList<>();
        List<Integer> actualData = new ArrayList<>();
    }

    public Result encode(Node root) {
        Result r = new Result();
        encode(root, r);
        return r;
    }

    private void encode(Node root, Result r) {
        if(root == null) {
            r.binaryRep.add(0);
            return;
        }
        r.actualData.add(root.data);
        r.binaryRep.add(1);

        encode(root.left, r);
        encode(root.right, r);
    }

    public Node decode(Result r) {
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        return decode(r, x, y);
    }

    private Node decode(Result r, AtomicInteger x, AtomicInteger y) {
        if(r.binaryRep.get(x.get()) == 0) {
            x.getAndIncrement();
            return null;
        }

        Node root = new Node();
        root.data = r.actualData.get(y.getAndIncrement());
        x.getAndIncrement();
        root.left = decode(r, x, y);
        root.right = decode(r, x, y);
        return root;
    }

    public static void main(String args[]) {
        Node root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addNode(10, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(15, root);
        root = bt.addNode(-7, root);
        root = bt.addNode(22, root);
        root = bt.addNode(-4, root);
        root = bt.addNode(12, root);
        System.out.println("Before decoding");
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        SuccinctTree st = new SuccinctTree();
        Result r = st.encode(root);
        root = st.decode(r);
        System.out.println("After decoding");
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
    }

}
