## 二叉树的下一个结点

### 题目描述
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。


### 解法
对于结点 `pNode`：
- 如果它有右子树，则**右子树的最左结点**就是它的下一个结点；
- 如果它没有右子树，判断它与父结点 `pNode.next` 的位置情况：
    - 如果它是父结点的左孩子，那么父结点 `pNode.next` 就是它的下一个结点；
    - 如果它是父结点的右孩子，一直向上寻找，直到找到某个结点，它是它父结点的左孩子，那么该父结点就是 `pNode` 的下一个结点。

```java
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/

/**
 * @author Anonymous
 * @since 2019/10/28
 */

public class Solution {
    /**
     * 获取中序遍历结点的下一个结点
     * @param pNode 某个结点
     * @return pNode的下一个结点
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        
        if (pNode.right != null) {
            TreeLinkNode t = pNode.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        }
        
        // 须保证 pNode.next 不为空，否则会出现 NPE
        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }
        
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        
        return null;
        
    }
}
```


### 测试用例
1. 普通二叉树（完全二叉树；不完全二叉树）；
2. 特殊二叉树（所有结点都没有左/右子结点；只有一个结点的二叉树；二叉树的根结点为空）；
3. 不同位置的结点的下一个结点（下一个结点为当前结点的右子结点、右子树的最左子结点、父结点、跨层的父结点等；当前结点没有下一个结点）。