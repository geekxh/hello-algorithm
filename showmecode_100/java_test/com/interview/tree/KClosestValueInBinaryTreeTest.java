package com.interview.tree;

import com.interview.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class KClosestValueInBinaryTreeTest {

    @Test
    public void testDifferentCases() {
        Node root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addNode(10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(30, root);
        root = bt.addNode(0, root);
        root = bt.addNode(6, root);
        root = bt.addNode(-6, root);
        root = bt.addNode(16, root);
        root = bt.addNode(19, root);

        KClosestValueInBinaryTree kClosestValueInBinaryTree = new KClosestValueInBinaryTree();
        List<Integer> result = kClosestValueInBinaryTree.closestKValues(root, 18, 2);
        TestUtil testUtil = new TestUtil();
        testUtil.compareList(Arrays.asList(19, 20), result);

        result = kClosestValueInBinaryTree.closestKValues(root, 18, 4);
        testUtil.compareList(Arrays.asList(19, 20, 16, 10), result);
    }
}
