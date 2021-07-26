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
     * 
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
     * 
     * @return 最小值
     */
    public int min() {
        return stack2.peek();
    }
}