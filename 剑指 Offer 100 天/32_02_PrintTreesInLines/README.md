## 把二叉树打印成多行

### 题目描述
从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

### 解法
与上一题类似，只不过需要用变量记录每一层要打印多少个节点。

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
     * 把二叉树打印成多行
     * @param pRoot 二叉树根节点
     * @return 结果list
     */
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int cnt = 1;
        while (cnt > 0) {
            int num = cnt;
            cnt = 0;
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < num; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    ++cnt;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    ++cnt;
                }
                res.add(node.val);
            }
            list.add(res);
        }
        return list;
    }

}
```

### 测试用例
1. 功能测试（完全二叉树；所有节点只有左/右子树）；
2. 特殊输入测试（二叉树根节点为空指针；只有一个节点的二叉树）。