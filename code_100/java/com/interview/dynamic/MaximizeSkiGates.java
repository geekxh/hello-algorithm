package com.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 11/23/2015
 * @author Tushar Roy
 *
 * Given starting line and right barrier which is perpendicular to starting line. There are
 * n gates at certain distinct distance from right barrier. Starting from start line how do you ski
 * such that you can cover maximum number of gates. Changing direction is expensive so you can
 * only do limited number of direction changes.
 *
 * Idea at every gate is to either select this gate or not select this gate. Another decision to make
 * is to either change or not change direction at every gate(provided you have remainingDirectionChanges > 0).
 * Do recursion and apply memoization to make it top down recursive solution.
 */
public class MaximizeSkiGates {
    class Index {
        int remainingChanges;
        int current;
        boolean isRight;
        int prevItem;

        Index(int remainingChanges, int current, boolean isRight, int prevItem) {
            this.remainingChanges = remainingChanges;
            this.current = current;
            this.isRight = isRight;
            this.prevItem = prevItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Index index = (Index) o;

            if (remainingChanges != index.remainingChanges) return false;
            if (current != index.current) return false;
            if (isRight != index.isRight) return false;
            return prevItem == index.prevItem;

        }

        @Override
        public int hashCode() {
            int result = remainingChanges;
            result = 31 * result + current;
            result = 31 * result + (isRight ? 1 : 0);
            result = 31 * result + prevItem;
            return result;
        }
    }

    public int solution(int gates[], int totalDirectionChanges) {
        Map<Index, Integer> dpMap = new HashMap<>();
        return solution(gates, totalDirectionChanges, 0, false, -1, dpMap);
    }

    public int solution(int gates[], int remainingDirectionChanges, int current, boolean isRight, int prevItem, Map<Index, Integer> dpMap) {
        if(current >= gates.length) {
            return 0;
        }

        Index index = new Index(remainingDirectionChanges, current, isRight, prevItem);
        if(dpMap.containsKey(index)) {
            return dpMap.get(index);
        }

        int val1 = 0, val2 = 0;
        //if current gate is picked.
        if((isRight && gates[current] < prevItem) || (!isRight && gates[current] > prevItem)) {
            //if we decide to continue in same direction.
            val1 = 1 + solution(gates, remainingDirectionChanges, current + 1, isRight, gates[current], dpMap);
            if(remainingDirectionChanges > 0) {
                //if we flip direction. We can only do that if remainingDirectionChanges > 0
                val2 = 1 + solution(gates, remainingDirectionChanges - 1, current + 1, !isRight, gates[current], dpMap);
            }
        }

        //if current gate is not picked
        int val3 = solution(gates, remainingDirectionChanges, current + 1, isRight, prevItem, dpMap);

        //max of all 3 possibilities
        int max = Math.max(Math.max(val1, val2), val3);
        dpMap.put(index, max);
        return max;
    }

    public static void main(String args[]) {
        int input[] = {15, 13, 5, 7, 4, 10, 12, 8, 2, 11, 6, 9 , 3};
        MaximizeSkiGates sg = new MaximizeSkiGates();
        System.out.println(sg.solution(input, 2));
    }
}
