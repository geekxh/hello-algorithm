## [二叉树的深度](https://www.acwing.com/problem/content/67/)

### 题目描述
输入一棵二叉树的根结点，求该树的深度。

从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

**样例**
```
输入：二叉树[8, 12, 2, null, null, 6, 4, null, null, null, null]如下图所示：
    8
   / \
  12  2
     / \
    6   4

输出：3
```

### 解法
递归即可。


```java
/**
 * @author Anonymous
 * @since 2019/12/10
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 求二叉树的深度
     * 
     * @param root 二叉树根结点
     * @return 深度
     */
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = treeDepth(root.left);
        int rDepth = treeDepth(root.right);
        return 1 + Math.max(lDepth, rDepth);
    }
}
```

### 测试用例
1. 功能测试（输入普通的二叉树；二叉树中所有节点都没有左/右子树）；
2. 特殊输入测试（二叉树只有一个节点；二叉树的头节点为空指针）。