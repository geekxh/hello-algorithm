## 二叉搜索树的后序遍历序列

### 题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出`Yes`,否则输出`No`。假设输入的数组的任意两个数字都互不相同。

### 解法
序列的最后一个元素是二叉搜索树的根节点。

在序列中从左到右找到根节点的左子树(比根节点小)、右子树(比根节点大)。
- 如果右子树中出现比根节点小的元素，那么为 false。
- 否则递归左右子树。


```java
/**
 * @author Anonymous
 * @since 2019/11/23
 */

public class Solution {
    /**
     * 判断数组是否是某个二叉搜索树的后序遍历序列
     *
     * @param sequence 数组
     * @return 是否属于某二叉搜索树的后序遍历序列
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length < 1) {
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int val = sequence[end];
        int i = start;
        for (; i <= end; ++i) {
            if (sequence[i] >= val) {
                break;
            }
        }

        for (int j = i; j < end; ++j) {
            if (sequence[j] < val) {
                return false;
            }
        }

        return verify(sequence, start, i - 1) && verify(sequence, i, end - 1);

    }
}
```

### 测试用例
1. 功能测试（输入的后序遍历序列对应一棵二叉树，包括完全二叉树、所有节点都没有左/右子树的二叉树、只有一个节点的二叉树；输入的后续遍历序列没有对应一棵二叉树）；
2. 特殊输入测试（后序遍历序列为空指针）。