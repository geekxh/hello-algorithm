### 包含min函数的栈

### 题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为`O(1)`）。

### 解法
定义两个`stack`。

压栈时，先将元素`node`压入`stack1`。然后判断`stack2`的情况：
- `stack2`栈为空或者栈顶元素大于`node`，则将`node`压入`stack2`中。
- `stack2`栈不为空且栈定元素小于`node`，则重复压入栈顶元素。

获取最小元素时，从`stack2`中获取栈顶元素即可。

```java
import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/11/22
 */


public class Solution {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * 压栈
     * @param node 待压入的元素
     */
    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty() || stack2.peek() >= node) {
            stack2.push(node);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack2.peek();
    }

    /**
     * O(1)获取栈中最小值
     * @return 最小值
     */
    public int min() {
        return stack2.peek();
    }
}
```

### 测试用例
1. 新压入栈的数字比之前的最小值大/小。
2. 弹出栈的数字是/不是最小元素。