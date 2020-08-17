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