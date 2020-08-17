## 序列化二叉树

### 题目描述
请实现两个函数，分别用来序列化和反序列化二叉树。使用前序遍历实现，空节点使用字符`#` 表示。

比如有如下二叉树：

```java
			1
		2	 	3
	4	  #	 5		6
 #	  #	   #   #  #   #	
```

序列化的结果为 `1,2,4,#,#,#,3,5,#,#,6,#,#` 

反序列化的结果为上述二叉树

### 解法
使用前序遍历进行序列化和反序列化。对格式没有要求，只要序列化得到的结果，再反序列化后能与原树相同即可。
```java
/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2019/1/12
 */
public class Solution {


    public String Serialize(TreeNode root) {

        StringBuilder res = new StringBuilder();
        if (root == null) {
            return res.toString();
        }

        serializeHelper(root, res);
        // 移除最后一个的符号","
        res.deleteCharAt(res.lastIndexOf(","));
        return res.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder res) {

        if (root == null) {
            res.append("#");
            res.append(",");
            return;
        }

        res.append(root.val);
        res.append(",");
        serializeHelper(root.left, res);
        serializeHelper(root.right, res);
    }

    private int index = -1;

    public TreeNode Deserialize(String str) {

        if (str == null || str.length() == 0) {
            return null;
        }

        String[] treeNodeStr = str.split(",");
        return deserializeHelper(treeNodeStr);
    }

    private TreeNode deserializeHelper(String[] treeNodeStr) {

        index++;
        TreeNode node = null;

        // index不越界并且当前节点不为#
        if (index < treeNodeStr.length && !"#".equals(treeNodeStr[index])) {
            node = new TreeNode(Integer.valueOf(treeNodeStr[index]));
            node.left = deserializeHelper(treeNodeStr);
            node.right = deserializeHelper(treeNodeStr);
        }

        return node;
    }
}
```

### 测试用例
1. 功能测试（输入的二叉树是完全二叉树；所有节点都没有左/右子树的二叉树；只有一个节点的二叉树；所有节点的值都相同的二叉树）；
2. 特殊输入测试（指向二叉树根结点的指针为空指针）。