import java.util.Stack;

/**
 * @author Anonymous
 * @since 2019/11/22
 */


public class Solution {
    /**
     * 判断是否是弹出序列
     * 
     * @param pushA 压栈序列
     * @param popA 弹栈序列
     * @return 是否是弹出序列
     */
    public boolean IsPopOrder(int[] pushA,int[] popA) {
        if (pushA == null || popA == null || pushA.length != popA.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int n = pushA.length;
        boolean flag = false;
        for (int val : popA) {
            while (stack.isEmpty() || stack.peek() != val) {
                if (i >= n) {
                    flag = true;
                    break;
                }
                stack.push(pushA[i++]);
            }
            if (flag) {
                break;
            }
            stack.pop();
        }

        return stack.isEmpty();
    }
}