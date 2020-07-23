package com.interview.linklist;

import org.junit.Test;

public class DeleteDuplicateNodesTest {

    @Test
    public void testDifferentCases() {
        DeleteDuplicateNodes deleteDuplicateNodes = new DeleteDuplicateNodes();
        LinkList linkList = new LinkList();
        Node node = null;
        node = linkList.addNode(1, node);
        node = linkList.addNode(2, node);
        node = linkList.addNode(2, node);
        deleteDuplicateNodes.deleteDuplicates(node);
    }
}
