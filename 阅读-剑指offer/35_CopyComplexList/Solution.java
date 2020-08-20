/**
 * @author Anonymous
 * @since 2019/11/24
 */

/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    /**
     * 复杂链表的复制
     * 
     * @param pHead 链表头结点
     * @return 复制的链表
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }

        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }

        cur = pHead;
        RandomListNode cloneHead = pHead.next;
        while (cur.next != null) {
            RandomListNode clone = cur.next;
            cur.next = clone.next;
            cur = clone;
        }
        return cloneHead;
    }
}