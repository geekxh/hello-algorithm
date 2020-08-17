## 链表中环的入口结点

### 题目描述
给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出`null`。

### 解法
- 先利用快慢指针。若能相遇，说明存在环，且相遇点一定是在环上；若没有相遇，说明不存在环，返回 `null`。
- 固定当前相遇点，用一个指针继续走，同时累积结点数。计算出环的结点个数 `cnt`。
- 指针 p1 先走 `cnt` 步，p2 指向链表头部，之后 `p1`,`p2` 同时走，相遇时，相遇点一定是在环的入口处。因为 `p1` 比 `p2` 多走了环的一圈。

```java

/**
 * @author Anonymous
 * @since 2019/11/22
 */

/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {

    /**
     * 求链表环的入口，若没有环，返回null
     * @param pHead 链表头
     * @return 环的入口点
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                flag = true;
                break;
            }
        }

        // 快指针与慢指针没有相遇，说明无环，返回 null
        if (!flag) {
            return null;
        }

        ListNode cur = slow.next;
        // 求出环中结点个数
        int cnt = 1;
        while (cur != slow) {
            cur = cur.next;
            ++cnt;
        }

        // 指针p1先走cnt步
        ListNode p1 = pHead;
        for (int i = 0; i < cnt; ++i) {
            p1 = p1.next;
        }

        // p2指向链表头，然后p1/p2同时走，首次相遇的地方就是环的入口
        ListNode p2 = pHead;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
```

### 测试用例
1. 功能测试（链表中包含/不包含环；链表中有多个或者只有一个节点）；
2. 特殊输入测试（链表头节点为空指针）。