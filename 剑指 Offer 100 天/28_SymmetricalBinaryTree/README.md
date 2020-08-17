## 对称的二叉树

### 题目描述
请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

### 解法
比较二叉树的前序遍历序列和对称前序遍历序列是否一样，若是，说明是对称的。

```java

/**
 * @author Anonymous
 * @since 2019/11/22
 */

/*
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
     * 判断是否是对称二叉树
     * @param pRoot 二叉树的根结点
     * @return 是否为对称二叉树
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2) {
        if (pRoot1 == null && pRoot2 == null) {
            return true;
        }
        if (pRoot1 == null || pRoot2 == null) {
            return false;
        }
        if (pRoot1.val != pRoot2.val) {
            return false;
        }

        return isSymmetrical(pRoot1.left, pRoot2.right) && isSymmetrical(pRoot1.right, pRoot2.left);

    }
}
```

### 测试用例
1. 功能测试（对称的二叉树；因结构而不对称的二叉树；结构对称但节点的值不对称的二叉树）；
2. 特殊输入测试（二叉树的根结点为空指针；只有一个节点的二叉树；所有节点的值都相同的二叉树）。