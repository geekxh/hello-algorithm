## 二叉树的镜像

### 题目描述
操作给定的二叉树，将其变换为源二叉树的镜像。

```
源二叉树 
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11

镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
```

### 解法
将根结点的左右孩子互换，之后递归左右孩子。

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
     * 将二叉树转换为它的镜像
     * @param root 二叉树的根结点
     */
    public void Mirror(TreeNode root) {
        if (root == null || !hasChild(root)) {
            return;
        }

        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        Mirror(root.left);
        Mirror(root.right);
    }

    private boolean hasChild(TreeNode root) {
        return root.left != null || root.right != null;
    }
}
```

### 测试用例
1. 功能测试（普通的二叉树；二叉树的所有结点都没有左/右子树；只有一个结点的二叉树）；
2. 特殊输入测试（二叉树的根结点为空指针）。