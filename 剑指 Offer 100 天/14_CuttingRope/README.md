## 剪绳子

### 题目描述
给你一根长度为`n`绳子，请把绳子剪成`m`段（`m`、`n`都是整数，`n>1`并且`m≥1`）。每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]*k[1]*…*k[m]可能的最大乘积是多少？例如当绳子的长度是 8 时，我们把它剪成长度分别为 `2、3、3` 的三段，此时得到最大的乘积`18`。

### 解法
#### 解法一：动态规划法
时间复杂度`O(n²)`，空间复杂度`O(n)`。

- 长度为 2，只可能剪成长度为 1 的两段，因此 f(2)=1
- 长度为 3，剪成长度分别为 1 和 2 的两段，乘积比较大，因此 f(3) = 2
- 长度为 n，在剪第一刀的时候，有 n-1 种可能的选择，剪出来的绳子又可以继续剪，可以看出，原问题可以划分为子问题，子问题又有重复子问题。

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {

    /**
     * 剪绳子求最大乘积
     * @param length 绳子长度
     * @return 乘积最大值
     */
    public int maxProductAfterCutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length < 4) {
            return length - 1;
        }

        // res[i] 表示当长度为i时的最大乘积
        int[] res = new int[length + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        // 从长度为4开始计算
        for (int i = 4; i <= length; ++i) {
            int max = 0;
            for (int j = 1; j <= i / 2; ++j) {
                max = Math.max(max, res[j] * res[i - j]);
            }
            res[i] = max;
        }

        return res[length];

    }
}

```

#### 贪心算法
时间复杂度`O(1)`，空间复杂度`O(1)`。

贪心策略：
- 当 n>=5 时，尽可能多地剪长度为 3 的绳子
- 当剩下的绳子长度为 4 时，就把绳子剪成两段长度为 2 的绳子。

**证明：**
- 当 n>=5 时，可以证明 2(n-2)>n，并且 3(n-3)>n。也就是说，当绳子剩下长度大于或者等于 5 的时候，可以把它剪成长度为 3 或者 2 的绳子段。
- 当 n>=5 时，3(n-3)>=2(n-2)，因此，应该尽可能多地剪长度为 3 的绳子段。
- 当 n=4 时，剪成两根长度为 2 的绳子，其实没必要剪，只是题目的要求是至少要剪一刀。

```java
/**
 * @author Anonymous
 * @since 2019/11/20
 */

public class Solution {

    /**
     * 剪绳子求最大乘积
     * @param length 绳子长度
     * @return 乘积最大值
     */
    public int maxProductAfterCutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length < 4) {
            return length - 1;
        }

        int timesOf3 = length / 3;
        if (length % 3 == 1) {
            --timesOf3;
        }
        int timesOf2 = (length - timesOf3 * 3) >> 1;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }
}

```

### 测试用例
1. 功能测试（绳子的初始长度大于 5）；
2. 边界值测试（绳子的初始长度分别为 0、1、2、3、4）。