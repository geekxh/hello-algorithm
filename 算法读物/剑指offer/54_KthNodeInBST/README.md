## 二叉搜索树的第k个结点

### 题目描述
给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4

### 解法
因为BST的中序遍历得到的是一个升序的列表，所以在进行中序遍历行进行判断即可。所以该算法的时间复杂度为O(logn)


```java
/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/28
 */
class Solution {
    
    private int count = 0;

    public TreeNode KthNode(TreeNode pRoot, int k) {

        if (pRoot == null || k == 0) {
            return null;
        }

        // 左递归
        TreeNode retNode = KthNode(pRoot.left, k);

        if (retNode != null) {
            return retNode;
        }

        // 符合条件则返回
        count++;
        if (count == k) {
            return pRoot;
        }

        // 右递归
        retNode = KthNode(pRoot.right, k);
        if (retNode != null) {
            return retNode;
        }

        return null;
    }
}
```

### 测试用例
1. 功能测试（各种形态不同的二叉搜索树）；
2. 边界值测试（输入k为0、1、二叉搜索树的结点数、二叉搜索树的结点数+1）；
3. 特殊输入测试（指向二叉搜索树的节点的指针为空指针）。