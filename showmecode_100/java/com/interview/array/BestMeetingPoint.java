package com.interview.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 03/24/2016
 * @author Tushar Roy
 *
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * Find the total distance that needs to be travelled to reach this meeting point.
 *
 * Time complexity O(m*n)
 * Space complexity O(m + n)
 *
 * https://leetcode.com/problems/best-meeting-point/
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        List<Integer> vertical = new ArrayList<>();
        List<Integer> horizontal = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    vertical.add(i);
                    horizontal.add(j);
                }
            }
        }

        Collections.sort(vertical);
        Collections.sort(horizontal);

        int size = vertical.size()/2;
        int x = vertical.get(size);
        int y = horizontal.get(size);
        int distance = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    distance += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }

        return distance;
    }

    public static void main(String args[]) {
        BestMeetingPoint bmp = new BestMeetingPoint();
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0},{0, 0, 1, 0, 0}};
        System.out.print(bmp.minTotalDistance(grid));
    }
}
