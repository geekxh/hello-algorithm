## 反转链表

### 题目描述
输入一个链表，反转链表后，输出新链表的表头。

### 解法
#### 解法一
利用头插法解决。

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
}*/
public class Solution {
    /**
     * 反转链表
     * @param head 链表头部
     * @return 反转后的链表
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = null;
        ListNode p1 = head;
        ListNode p2 = p1.next;
        while (p1 != null) {
            p1.next = dummy.next;
            dummy.next = p1;
            p1 = p2;
            if (p1 == null) {
                break;
            }
            p2 = p1.next;
        }

        return dummy.next;
    }
}
```

#### 解法二：递归

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
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode next = ReverseList(head.next);
        ListNode cur = next;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        head.next = null;
        return next;
    }
}
```

### 测试用例
1. 功能测试（链表中有多个结点/只有一个节点）；
2. 特殊输入测试（链表头节点为空指针）。