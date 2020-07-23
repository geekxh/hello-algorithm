package com.interview.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Date 03/01/2016
 * @author Tushar Roy
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[] used = new boolean[numCourses];
        Neighbors[] graph = new Neighbors[numCourses];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Neighbors();
        }

        for (int[] tuple : prerequisites) {
            graph[tuple[1]].neighbor.add(tuple[0]);
        }
        Deque<Integer> stack = new LinkedList<>();
        boolean[] dfs = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (topSort(i, graph, used, stack, dfs)) {
                return new int[0];
            }
        }

        int[] output = new int[numCourses];
        int index = 0;
        while (!stack.isEmpty()) {
            output[index++] = stack.pollFirst();
        }

        return output;
    }

    class Neighbors {
        List<Integer> neighbor = new ArrayList<>();
    }

    private boolean topSort(int course, Neighbors[] graph, boolean[] used, Deque<Integer> stack, boolean[] dfs) {
        if (used[course]) {
            return false;
        }
        if (dfs[course]) {
            return true;
        }
        dfs[course] = true;
        for (int adj : graph[course].neighbor) {
            if (topSort(adj, graph, used, stack, dfs)) {
                return true;
            }
        }
        dfs[course] = false;
        used[course] = true;
        stack.offerFirst(course);
        return false;
    }
}
