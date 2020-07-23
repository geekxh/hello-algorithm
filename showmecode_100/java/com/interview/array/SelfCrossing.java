package com.interview.array;

/**
 * Created by tushar_v_roy on 3/10/16.
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] x) {
        if (x.length < 4) {
            return false;
        }
        int v1 = -x[0];
        int v2 = -x[1];

        int i = 2;
        while (i < x.length) {
            if (i % 2 == 0) {
                if (i % 4 == 0) {
                    v1 -= x[i];
                } else {
                    v1 += x[i];
                }
            } else {
                if ((i + 1) % 4 == 0) {
                    v2 += x[i];
                } else {
                    v2 -= x[i];
                }
            }
            if (i % 2 != 0) {
                if ((v1 >= 0 && v2 <= 0) || (v1 <= 0 && v2 >= 0)) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void main(String args[]) {
        SelfCrossing sc = new SelfCrossing();
        int input[] = {3, 3, 4, 2, 2};
        System.out.print(sc.isSelfCrossing(input));
    }
}
