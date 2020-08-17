import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Anonymous
 * @since 2019/10/29
 */

public class Solution {

    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int node) {
        queue1.offer(node);
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new RuntimeException("Empty stack!");
        }

        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }

        int val = queue1.poll();

        Queue<Integer> t = queue1;
        queue1 = queue2;
        queue2 = t;
        return val;

    }
}