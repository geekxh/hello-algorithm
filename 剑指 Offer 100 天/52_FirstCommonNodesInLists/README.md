## 两个链表的第一个公共结点

### 题目描述
输入两个链表，找出它们的第一个公共结点。

**样例**
```
给出两个链表如下所示：
A：        a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

输出第一个公共节点c1
```

### 解法
先遍历两链表，求出两链表的长度，再求长度差 `|n1 - n2|`。

较长的链表先走 `|n1 - n2|` 步，之后两链表再同时走，首次相遇时的节点即为两链表的第一个公共节点。


```java
/**
 * @author Anonymous
 * @since 2019/12/8
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {

    /**
     * 求两链表第一个公共节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 第一个公共节点
     */
    public ListNode findFirstCommonNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int n1 = len(headA), n2 = len(headB);
        ListNode p1 = headA, p2 = headB;
        if (n1 > n2) {
            for (int i = 0; i < n1 - n2; ++i) {
                p1 = p1.next;
            }
        } else if (n1 < n2) {
            for (int i = 0; i < n2 - n1; ++i) {
                p2 = p2.next;
            }
        }
        while (p1 != p2 && p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return (p1 == null || p2 == null) ? null : p1;
    }

    private int len(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        return n;
    }
}
```

### 测试用例
1. 功能测试（输入的两个链表有公共节点；第一个公共节点在链表的中间，第一个公共节点在链表的末尾，第一个公共节点是链表的头节点；输入的两个链表没有公共节点）；
2. 特殊输入测试（输入的链表头节点是空指针）。