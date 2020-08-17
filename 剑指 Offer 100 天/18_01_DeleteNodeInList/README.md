## 在O(1)时间内删除链表节点

### 题目描述
给定单向链表的头指针和一个节点指针，定义一个函数在 O(1) 时间内删除该节点。

### 解法
判断要删除的节点是否是尾节点，若是，直接删除；若不是，把要删除节点的下一个节点赋给要删除的节点即可。

### ```进行n次操作，平均时间复杂度为：( (n-1) * O(1) + O(n) ) / n = O(1)，所以符合题目上说的O(1)```

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {

    class ListNode {
        int val;
        ListNode next;
    }

    /**
     * 删除链表的节点
     * @param head 链表头节点
     * @param tobeDelete 要删除的节点
     */
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null) {
            return head;
        }

        // 删除的不是尾节点
        if (tobeDelete.next != null) {
            tobeDelete.val = tobeDelete.next.val;
            tobeDelete.next = tobeDelete.next.next;
        }
        // 链表中仅有一个节点
        else if (head == tobeDelete) {
            head = null;
        }
        // 删除的是尾节点
        else {
            ListNode ptr = head;
            while (ptr.next != tobeDelete) {
                ptr = ptr.next;	
          	}
            ptr.next = null;
        }

        return head;
    }
}
```

### 测试用例
1. 功能测试（从有多个节点的链表的中间/头部/尾部删除一个节点；从只有一个节点的链表中删除唯一的节点）；
2. 特殊输入测试（指向链表头节点的为空指针；指向要删除节点的为空指针）。
