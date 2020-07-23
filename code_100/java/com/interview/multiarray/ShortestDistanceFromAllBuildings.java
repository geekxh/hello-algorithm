package com.interview.multiarray;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Date 03/25/2016
 * @author Tushar Roy
 *
 * Shortest Distance from All Buildings
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {
    private class Point {
        int x, y, dist = 0;
        Point(int row, int col, int dist) {
            x = row;
            y = col;
            this.dist = dist;
        }
    }

    public int shortestDistance(int[][] grid) {
        int totalBuild = 0;
        int[][] reach = new int[grid.length][grid[0].length];
        int[][] distance = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.offer(new Point(i, j, 0));
                    totalBuild++;
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    while (!queue.isEmpty()) {
                        List<Point> neighbors = getNeighbors(queue.poll(), grid, visited, grid.length, grid[0].length);
                        for (Point neigh : neighbors) {
                            visited[neigh.x][neigh.y] = true;
                            reach[neigh.x][neigh.y]++;
                            distance[neigh.x][neigh.y] += neigh.dist;
                            queue.offer(neigh);
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < reach.length; i++) {
            for (int j = 0; j < reach[0].length; j++) {
                if (reach[i][j] == totalBuild) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private List<Point> getNeighbors(Point p, int[][] grid, boolean[][] visited, int n, int m) {
        List<Point> resultList = new ArrayList<>();
        if(p.x > 0 && grid[p.x -1][p.y] == 0 && !visited[p.x - 1][p.y])
            resultList.add(new Point(p.x -1, p.y, p.dist + 1));
        if(p.x < n - 1 && grid[p.x + 1][p.y] == 0 && !visited[p.x + 1][p.y])
            resultList.add(new Point(p.x + 1, p.y, p.dist + 1));
        if(p.y > 0 && grid[p.x][p.y - 1] == 0 && !visited[p.x][p.y - 1])
            resultList.add(new Point(p.x, p.y - 1, p.dist + 1));
        if(p.y < m - 1 && grid[p.x][p.y + 1] == 0 && !visited[p.x][p.y + 1])
            resultList.add(new Point(p.x, p.y + 1, p.dist + 1));

        return resultList;
    }

    public static void main(String args[]) {
        int[][] grid = {{1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};
        int[][] grid1 = {{1,1},{0,1}};
        ShortestDistanceFromAllBuildings shortestDistanceFromAllBuildings = new ShortestDistanceFromAllBuildings();
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance(grid));
    }

}
