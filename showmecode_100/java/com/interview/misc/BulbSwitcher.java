package com.interview.misc;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's
 * off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only
 * toggle the last bulb. Find how many bulbs are on after n rounds.
 *
 * https://leetcode.com/problems/bulb-switcher/
 */
public class BulbSwitcher {
    public int bulbSwitch(int n) {
        int count = 0;
        while (count*count <= n) {
            count++;
        }
        return count - 1;
    }
}
