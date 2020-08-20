## 重建二叉树

### 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列 `{1,2,4,7,3,5,6,8}` 和中序遍历序列 `{4,7,2,1,5,3,8,6}`，则重建二叉树并返回。


### 解法
在二叉树的前序遍历序列中，第一个数字总是根结点的值。在中序遍历序列中，根结点的值在序列的中间，左子树的结点位于根结点左侧，而右子树的结点位于根结点值的右侧。

遍历中序序列，找到根结点，递归构建左子树与右子树。

注意添加特殊情况的 `if` 判断。

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * @author Anonymous
 * @since 2019/10/28
 */

public class Solution {
    /**
     * 重建二叉树
     * 
     * @param pre 先序序列
     * @param in  中序序列
     * @return 二叉树根结点
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int n = pre.length;
        return constructBinaryTree(pre, 0, n - 1, in, 0, n - 1);
    }

    private TreeNode constructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        TreeNode node = new TreeNode(pre[startPre]);
        if (startPre == endPre) {
            if (startIn == endIn) {
                return node;
            }
            throw new IllegalArgumentException("Invalid input!");
        }

        int inOrder = startIn;
        while (in[inOrder] != pre[startPre]) {
            ++inOrder;
            if (inOrder > endIn) {
                new IllegalArgumentException("Invalid input!");
            }
        }
        int len = inOrder - startIn;
        if (len > 0) {
            // 递归构建左子树
            node.left = constructBinaryTree(pre, startPre + 1, startPre + len, in, startIn, inOrder - 1);
        }

        if (inOrder < endIn) {
            // 递归构建右子树
            node.right = constructBinaryTree(pre, startPre + len + 1, endPre, in, inOrder + 1, endIn);
        }
        return node;

    }
}
```


### 测试用例
1. 普通二叉树（完全二叉树；不完全二叉树）；
2. 特殊二叉树（所有结点都没有左/右子结点；只有一个结点的二叉树）；
3. 特殊输入测试（二叉树根结点为空；输入的前序序列和中序序列不匹配）。