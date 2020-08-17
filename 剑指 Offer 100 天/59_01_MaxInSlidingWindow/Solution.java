/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/02/05
 * @description
 */
class Solution {
    
    public ArrayList<Integer> maxInWindows(int[] num, int size) {

        ArrayList<Integer> reList = new ArrayList<>();
        if (num == null || num.length < size || size < 1) {
            return reList;
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {

            // 队尾元素比要入队的元素小，则把其移除（因为不可能成为窗口最大值）
            while (!deque.isEmpty() && num[deque.getLast()] <= num[i]) {
                deque.pollLast();
            }
            // 队首下标对应的元素不在窗口内（即窗口最大值），将其从队列中移除
            while (!deque.isEmpty() && (i - deque.getFirst() + 1 > size)) {
                deque.pollFirst();
            }
            // 把每次滑动的值加入到队列中
            deque.add(i);
            // 滑动窗口的首地址i大于size就写入窗口最大值
            if (!deque.isEmpty() && i + 1 >= size) {
                reList.add(num[deque.getFirst()]);
            }
        }

        return reList;
    }
}