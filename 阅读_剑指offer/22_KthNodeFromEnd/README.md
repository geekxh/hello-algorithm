## 链表中倒数第k个结点

### 题目描述
输入一个链表，输出该链表中倒数第k个结点。

### 解法
pre 指针走 `k-1` 步。之后 cur 指针指向 phead，然后两个指针同时走，直至 pre 指针到达尾结点。

> 当用一个指针遍历链表不能解决问题的时候，可以尝试用两个指针来遍历链表。可以让其中一个指针遍历的速度快一些。

此题需要考虑一些特殊情况。比如 k 的值小于 0 或者大于链表长度。

```java
/**
 * @author Anonymous
 * @since 2019/11/21
 */

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    /**
     * 找出链表倒数第k个节点，k从1开始
     * @param head 链表头部
     * @param k 第k个节点
     * @return 倒数第k个节点
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k < 1) {
            return null;
        }

        ListNode pre = head;
        for (int i = 0; i < k - 1; ++i) {
            if (pre.next != null) {
                pre = pre.next;
            } else {
                return null;
            }
        }

        ListNode cur = head;
        while (pre.next != null) {
            pre = pre.next;
            cur = cur.next;
        }
        return cur;
    }
}
```

### 测试用例
1. 功能测试（第 k 个节点在链表的中间/头部/尾部）；
2. 特殊输入测试（输入空指针；链表的节点总数小于 k；k=0）。