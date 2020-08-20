## 不分行从上到下打印二叉树

### 题目描述
从上往下打印出二叉树的每个节点，同层节点从左至右打印。

### 解法
先将根节点进入队列。

队头元素出队，将值存入 list，判断该元素是否有左/右子树，有的话依次进入队列中。队列为空时结束。

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Anonymous
 * @since 2019/11/23
 */

/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    /**
     * 从上到下打印二叉树
     * @param root 二叉树根节点
     * @return 结果list
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            list.add(node.val);
        }
        return list;
    }
}
```

### 测试用例
1. 功能测试（完全二叉树；所有节点只有左/右子树）；
2. 特殊输入测试（二叉树根节点为空指针；只有一个节点的二叉树）。