## 按之字形打印二叉树

### 题目描述
请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

如二叉树：
```
            1
    	   /  \
    	  2    3
    	 / \  / \
    	4  5 6  7
```

打印结果为：
```
1
3 2
4 5 6 7
```

### 解法
对于上述二叉树：

首先访问根结点，之后把2、3存入某结构。打印的时候，先打印3、2。这不就是栈？

依次弹出栈元素，分别是3、2。弹出时需要把3、2的子结点存入结构。由于访问时顺序是`4 5 6 7`。所以也需要用栈来存放。而且，此时需要先存放右孩子，再存放左孩子。（奇数层/偶数层存放左右孩子的顺序不同）

这里需要用两个栈来实现。如果只用一个栈，那么当弹出3、2 时，先将 3 的孩子节点压入栈。之后弹栈的时候不是先弹出 2，而是弹出了 3 的 孩子节点，就错了。


```java
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/11/23
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
     * 按之字形打印二叉树
     * @param pRoot 二叉树的根节点
     * @return 结果list
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        int i = 1;
        Stack<TreeNode> stack = stack1;
        while (!stack.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if (i % 2 == 1) {
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        stack1.push(node.right);
                    }
                    if (node.left != null) {
                        stack1.push(node.left);
                    }
                }
            }
            res.add(list);
            ++i;
            stack = stack1.isEmpty() ? stack2 : stack1;
        }

        return res;
    }

}
```

### 测试用例
1. 功能测试（完全二叉树；所有节点只有左/右子树）；
2. 特殊输入测试（二叉树根节点为空指针；只有一个节点的二叉树）。