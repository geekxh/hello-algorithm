package com.interview.tree;

import com.interview.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerticalOrderTest {

    @Test
    public void testDifferentCases() {
        SerializeDeserializeBinaryTree serializeDeserializeBinaryTree = new SerializeDeserializeBinaryTree();
        Node root = serializeDeserializeBinaryTree.deserializeLevelOrder("5,1,6,%,3,%,%,2,4");
        VerticalOrder verticalOrder = new VerticalOrder();
        List<List<Integer>> result = verticalOrder.verticalOrder(root);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(5, 3));
        expected.add(Arrays.asList(6, 4));

        int index = 0;
        TestUtil<Integer> t = new TestUtil<>();
        for (List<Integer> r : result) {
            t.compareList(expected.get(index++), r);
        }

        expected = new ArrayList<>();
        expected.add(Arrays.asList(9));
        expected.add(Arrays.asList(3, 15));
        expected.add(Arrays.asList(20));
        expected.add(Arrays.asList(7));

        root = serializeDeserializeBinaryTree.deserializeLevelOrder("3,9,20,%,%,15,7");
        result = verticalOrder.verticalOrder(root);
        index = 0;
        for (List<Integer> r : result) {
            t.compareList(expected.get(index++), r);
        }
    }

}
