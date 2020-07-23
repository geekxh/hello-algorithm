package com.interview.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 01/01/2016
 * @author Tushar Roy
 *
 * Given 3 sorted array find common elements in these 3 sorted array.
 *
 * Time complexity is O(m + n + k)
 *
 * http://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
 */
public class CommonThreeSortedArray {

    public List<Integer> commonElements(int input1[], int input2[], int input3[]) {
        int i = 0;
        int j = 0;
        int k = 0;
        List<Integer> result = new ArrayList<>();
        while (i < input1.length && j < input2.length && k < input3.length) {
            if (input1[i] == input2[j] && input2[j] == input3[k]) {
                result.add(input1[i]);
                i++;
                j++;
                k++;
            } else if (input1[i] < input2[j]) {
                i++;
            } else if (input2[j] < input3[k]) {
                j++;
            } else {
                k++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int input1[] = {1, 5, 10, 20, 40, 80};
        int input2[] = {6, 7, 20, 80, 100};
        int input3[] = {3, 4, 15, 20, 30, 70, 80, 120};

        CommonThreeSortedArray cts = new CommonThreeSortedArray();
        List<Integer> result = cts.commonElements(input1, input2, input3);
        result.forEach(i -> System.out.print(i + " "));
    }
}
