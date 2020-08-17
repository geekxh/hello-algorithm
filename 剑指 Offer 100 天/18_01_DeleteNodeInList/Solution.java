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
     * 
     * @param head       链表头节点
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