
/**
 * @author Anonymous
 * @since 2019/11/22
 */

/*
 * public class ListNode { int val; ListNode next = null;
 * 
 * ListNode(int val) { this.val = val; } }
 */
public class Solution {

    /**
     * 求链表环的入口，若没有环，返回null
     * 
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