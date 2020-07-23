package com.interview.geometry;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 03/21/2016
 * @author Tushar Roy
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Time complexity O(n^2) 
 * Space complexity O(n)
 *
 * https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaximumPointsOnSameLine {

    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    class Pair {
        int a;
        int b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (a != pair.a) return false;
            return b == pair.b;

        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }

    public int maxPoints(Point[] points) {
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Pair, Integer> map = new HashMap<>();
            int verticalLine = 1;
            int onePointMax = 1;
            int samePoint = 0;
            for (int j = i + 1; j < points.length; j++) {
                //same point repeated again. Just increment samePoint count
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    samePoint++;
                } else if (points[i].x == points[j].x) { //vertical line
                    verticalLine++;
                    onePointMax = Math.max(onePointMax, verticalLine);
                } else {
                    int gcd = gcd(points[i].y - points[j].y, points[i].x - points[j].x);
                    Pair p = new Pair();
                    p.a = (points[i].y - points[j].y)/gcd;
                    p.b = (points[i].x - points[j].x)/gcd;
                    int count = 2;
                    if (map.containsKey(p)) {
                        count = map.get(p);
                        count++;
                        map.put(p, count);
                    } else {
                        map.put(p, count);
                    }
                    onePointMax = Math.max(onePointMax, count);
                }
            }
            result = Math.max(result, onePointMax + samePoint);
        }
        return result;
    }

    int gcd(int a, int b) {
        if(b==0) return a;
        else return gcd(b, a % b);
    }
}
