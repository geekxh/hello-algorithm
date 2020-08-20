
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
     * 反转链表
     * 
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