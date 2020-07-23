package com.interview.linklist;

import java.util.Iterator;
import java.util.List;

/**
 * Date 10/10/2016
 * @author Tushar Roy
 *
 * Implement an iterator to flatten a 2d vector.
 *
 * https://leetcode.com/problems/flatten-2d-vector/
 *
 */
public class Flatten2DList implements Iterator<Integer> {
    private List<List<Integer>> vector;
    private int currentList = 0;
    private int currentPos = 0;
    public Flatten2DList(List<List<Integer>> vec2d) {
        vector = vec2d;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IllegalArgumentException();
        }
        int data = vector.get(currentList).get(currentPos);
        currentPos++;
        if (currentPos == vector.get(currentList).size()) {
            currentPos = 0;
            currentList++;
        }
        return data;
    }

    @Override
    public boolean hasNext() {
        while (currentList < vector.size() && vector.get(currentList).size() == 0) {
            currentList++;
        }
        return currentList < vector.size();
    }
}
