## 合并两个排序的链表

### 题目描述
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

### 解法
#### 解法一
同时遍历两链表进行 `merge`。

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
     * 合并两个排序链表
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的单调不递减链表
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                ListNode t = p1.next;
                cur.next = p1;
                p1.next = null;
                p1 = t;
            } else {
                ListNode t = p2.next;
                cur.next = p2;
                p2.next = null;
                p2 = t;
            }
            cur = cur.next;
        }

        cur.next = p1 == null ? p2 : p1;
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
    /**
     * 合并两个排序链表
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的单调不递减链表
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        }

        list2.next = Merge(list1, list2.next);
        return list2;
    }
}
```

### 测试用例
1. 功能测试（输入的两个链表有多个节点；节点的值互不相同或者存在值相等的多个节点）；
2. 特殊输入测试（两个链表的一个或者两个头节点为空指针；两个链表中只有一个节点）。