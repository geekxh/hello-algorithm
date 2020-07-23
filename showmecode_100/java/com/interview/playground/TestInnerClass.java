package com.interview.playground;

import java.util.Random;

/**
 * Created by tushar_v_roy on 5/18/16.
 */
public class TestInnerClass {

    int t = 20;
    Random random;
    public Okay test() {
        final int r = 10;
        return
                 random::nextInt;

    }

    public void test1() {
        random = new Random();
        Okay o1 = test();
        System.out.print(o1.next());
        Okay o2 = test();
        random = null;
        System.out.print(o2.next());
    }

    public static void main(String args[]) {
        TestInnerClass testInnerClass = new TestInnerClass();
        testInnerClass.test1();
    }
}

interface Okay {
    int next();
}
