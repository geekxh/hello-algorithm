/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/23
 * @description
 */
public class Solution {

    public int GetUglyNumber_Solution(int index) {

        if (index <= 0)
            return 0;

        List<Integer> reList = new ArrayList<>();
        // 第一个丑数为1
        reList.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (reList.size() < index) {

            int m2 = reList.get(i2) * 2;
            int m3 = reList.get(i3) * 3;
            int m5 = reList.get(i5) * 5;

            // 求出m2、m3、m5中的最小值，该值为加入list的丑数
            int min = Math.min(m2, Math.min(m3, m5));

            if (m2 == min) {
                i2++;
            }
            if (m3 == min) {
                i3++;
            }
            if (m5 == min) {
                i5++;
            }

            reList.add(min);
        }

        // O(1)
        return reList.get(reList.size() - 1);
    }
}