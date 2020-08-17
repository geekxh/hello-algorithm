import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Anonymous
 * @since 2019/12/7
 */

public class Solution {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    /**
     * 插入一个数
     *
     * @param num 数
     */
    public void Insert(Integer num) {

        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.offer(num);
            if (maxHeap.size() - minHeap.size() > 1) {
                minHeap.offer(maxHeap.poll());
            }

        } else {
            minHeap.offer(num);
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    /**
     * 获取中位数
     *
     * @return 中位数
     */
    public Double GetMedian() {
        int size1 = maxHeap.size();
        int size2 = minHeap.size();
        if (size1 > size2) {
            return (double) maxHeap.peek();
        }
        if (size1 < size2) {
            return (double) minHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }


}