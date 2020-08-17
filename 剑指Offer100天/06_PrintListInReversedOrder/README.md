## 从尾到头打印链表

### 题目描述
输入一个链表，按链表值从尾到头的顺序返回一个 `ArrayList`。


### 解法
#### 解法一【推荐】
遍历链表，每个链表结点值 `push` 进栈，最后将栈中元素依次 `pop` 到 `list` 中。
```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/10/28
 */
public class Solution {
    /**
     * 从尾到头打印链表
     * @param listNode 链表头节点
     * @return list
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        
        return res;
    }
}
```

#### 解法二【不推荐】
利用递归方式：
- 若不是链表尾结点，继续递归；
- 若是，添加到 `list` 中。

这种方式不推荐，当递归层数过多时，容易发生 `Stack Overflow`。

```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/10/28
 */
public class Solution {
    /**
     * 从尾到头打印链表
     * @param listNode 链表头结点
     * @return list
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        
        addElement(listNode, res);
        return res;
        
    }
    
    private void addElement(ListNode listNode, ArrayList<Integer> res) {
        if (listNode.next != null) {
            // 递归调用
            addElement(listNode.next, res);
        }
        res.add(listNode.val);
    }
}
```


### 测试用例
1. 功能测试（输入的链表有多个结点；输入的链表只有一个结点）；
2. 特殊输入测试（输入的链表结点指针为空）。