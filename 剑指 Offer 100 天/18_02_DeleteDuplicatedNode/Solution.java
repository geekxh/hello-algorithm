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
}
*/
public class Solution {
    /**
     * 删除链表重复的节点
     * @param pHead 链表头节点
     * @return 删除节点后的链表
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        if (pHead.val == pHead.next.val) {
            if (pHead.next.next == null) {
                return null;
            }
            if (pHead.next.next.val == pHead.val) {
                return deleteDuplication(pHead.next);
            }
            return deleteDuplication(pHead.next.next);
        }
        pHead.next = deleteDuplication(pHead.next);
        return pHead;
    }
}