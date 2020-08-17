## 复杂链表的复制

### 题目描述
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 `head`。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

### 解法
可以分为3步：

1. 复制每个节点，并插入到被复制节点的后面。比如1->2->3 clone 1->1->2->2->3->3
2. 复制随机节点。当遍历到的当前节点存在随机节点时，则其复制节点也应该存在随机节点。比如当前节点`cur.random != null`，则`RandomListNode clone = cur.next;clone.random = cur.random.next;`
3. 分离两个链表。其中奇数链表为原链表，偶数链表为复制的链表

这道题的time complexity为O(n)。

```java
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
```

### 测试用例
1. 功能测试（结点中的 random 指针指向结点自身；两个结点的 random 形成环状结构；链表中只有一个结点）；
2. 特殊输入测试（指向链表头结点的指针为空指针）。