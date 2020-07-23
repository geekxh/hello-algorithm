package com.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Read question at https://leetcode.com/problems/scramble-string/
 */
public class ScrambledString {

    /**
     * Index for memoization.
     */
    private static class Index {
        int start1;
        int end1;
        int start2;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Index index = (Index) o;

            if (start1 != index.start1) return false;
            if (end1 != index.end1) return false;
            if (start2 != index.start2) return false;
            return end2 == index.end2;

        }

        @Override
        public int hashCode() {
            int result = start1;
            result = 31 * result + end1;
            result = 31 * result + start2;
            result = 31 * result + end2;
            return result;
        }

        int end2;
    }

    boolean isScrambled(char input1[], char input2[], int start1, int end1, int start2, int end2, Map<Index, Boolean> memMap) {

        //return false conditions
        if(start1 > end1 || start2 > end2) {
            return false;
        }
        if(end1 - start1 != end2 - start2) {
            return false;
        }

        Index index = new Index();
        index.start1 = start1;
        index.end1 = end1;
        index.start2 = start2;
        index.end2 = end2;

        //if we have already calculated value for index then lets use it instead of recalculating again.
        if(memMap.containsKey(index)) {
            return memMap.get(index);
        }

        //if both input from their respective start to end are same then return true.
        boolean isSame = true;
        for(int i= start1, j = start2; i <= end1 && j <= end2; i++, j++) {
            if(input1[i] != input2[j]) {
                isSame = false;
                break;
            }
        }

        if(isSame) {
            memMap.put(index, true);
            return true;
        }


        //check if both input from their respective start to end have same characters. If not then return false.
        Map<Character, Integer> countMap = new HashMap<>();
        for(int i= start1; i <= end1; i++) {
            countMap.compute(input1[i], (ch, val) -> {
                    if(val == null) {
                        return 1;
                    } else {
                        return val + 1;
                    }
            });
        }

        for(int i= start2; i <= end2; i++) {
            countMap.compute(input2[i], (ch, val) -> {
                if(val == null) {
                    return -1;
                } else {
                    return val - 1;
                }
            });
        }

        //all values in map should have value 0 otherwise there is character mismatch. Return false in that case.
        long nonZeroCount = countMap.values().stream().filter(val -> val !=0 ).count();
        if(nonZeroCount > 0) {
            memMap.put(index, false);
            return false;
        }

        //for values from input range try splitting into 2 and check recursively if they match or not.
        for(int len = 0; len < end1 - start1; len++) {
            //e.g great gtear so match g ,g and reat, tear
            if(isScrambled(input1, input2, start1, start1 + len, start2, start2 + len, memMap) &&
                    isScrambled(input1, input2, start1 + len +1, end1, start2 + len + 1, end2, memMap)) {
                memMap.put(index, true);
                return true;
            }
            //e.g great reatg so match g,g and reat,reat
            if(isScrambled(input1, input2, start1, start1 + len, end2 - len, end2, memMap) &&
                    isScrambled(input1, input2, start1 + len +1, end1, start2, end2 - len -1, memMap)) {
                memMap.put(index, true);
                return true;
            }
       }
       memMap.put(index, false);
       return false;
    }

    public static void main(String args[]) {
        ScrambledString ss = new ScrambledString();
        String str1 = "great";
        String str2 = "rgtae";
        Map<Index, Boolean> memMap = new HashMap<>();
        boolean result = ss.isScrambled(str1.toCharArray(), str2.toCharArray(), 0, str1.length() - 1, 0, str2.length() -1, memMap);
        System.out.println(result);
    }

}
