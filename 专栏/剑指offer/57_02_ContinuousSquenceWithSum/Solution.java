/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/02/03
 * @description
 */
public class Solution {
    
    public List<List<Integer>> findContinuousSequence(int sum) {

        List<List<Integer>> reList = new ArrayList<>();

        if (sum < 3) {
            return reList;
        }

        int left = 1;
        int right = 2;
        int mid = (sum + 1) / 2;
        int curSum = left + right;

        // left小于sum一半即可(1/2n)
        while (left < mid) {

            // 等与sum则加入列表中(2~1/2n)
            if (curSum == sum) {
                reList.add(getListFromleftToright(left, right));
                // right增加并重新寻找序列
                right++;
                curSum += right;
            } else if (curSum > sum) {
                curSum -= left;
                left++;
            } else {
                right++;
                curSum += right;
            }
        }

        return reList;
    }

    private List<Integer> getListFromleftToright(int left, int right) {

        List<Integer> tempList = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            tempList.add(i);
        }

        return tempList;
    }
}