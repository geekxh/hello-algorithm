
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
     * 合并两个排序链表
     * 
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