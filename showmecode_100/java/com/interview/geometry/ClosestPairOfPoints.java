package com.interview.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/closest-pair-of-points-onlogn-implementation/
 * https://www.youtube.com/watch?v=_pSl90jq-m0 another good explanation
 * Given coordinates of points find closest pair points distance.
 * 
 * Test cases:
 * Number of points should be more than 1
 * Test along positive and negative axis
 * 
 * The way it works is
 * 1) Sort by x coordinate
 * 2) Recursively divide into two halves.
 * 3) Find min distance between points in each half. Say this distance is d.
 * 4) Find points which are in either half and closer than d. To find this point sort by y
 * axis and find distance to next 6 points. Magic about 6 points is we can create a rectangular
 * area and only 6 points can fit in that rectangular area. So we need to check only 7 points
 * for each point in rectangular area.
 * @author tusroy
 */


class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class XCoordinatorSorter implements Comparator<Point>{
    @Override
    public int compare(Point o1, Point o2) {
        if(o1.x < o2.x){
            return -1;
        }else{
            return 1;
        }
    }
}

class YCoordinatorSorter implements Comparator<Point>{
    @Override
    public int compare(Point o1, Point o2) {
        if(o1.y < o2.y){
            return -1;
        }else{
            return 1;
        }
    }
}

public class ClosestPairOfPoints {

    private static final int SIZE = 7;
    public double closestPairOfPoints(Point[] points){
        if(points.length < 2){
            throw new IllegalArgumentException();
        }
        Point[] px = new Point[points.length];
        Point[] py = new Point[points.length];
        int i=0;
        for(Point point : points){
            px[i++] = point;
        }
        i=0;
        for(Point point : points){
            py[i++] = point;
        }
        XCoordinatorSorter xSorter = new XCoordinatorSorter();
        YCoordinatorSorter ySorter = new YCoordinatorSorter();
        Arrays.sort(px, xSorter);
        Arrays.sort(py, ySorter);
        int minDistance = closestPairOfPoints(px, py, 0, points.length-1);
        return Math.sqrt(minDistance);
    }
    
    private int closestPairOfPoints(Point[] px, Point[] py,int start, int end){
        if(end - start < 3){
            return computeMinDistance(px, start, end);
        }
        
        int mid = (start + end)/2;
        Point[] pyLeft = new Point[mid-start+1];
        Point[] pyRight = new Point[end-mid];
        int i=0, j=0;
        for(Point p : px){
            if(p.x <= px[mid].x){
                pyLeft[i++] = p;
            }else{
                pyRight[j++] = p;
            }
        }
        int d1 = closestPairOfPoints(px,pyLeft,start,mid);
        int d2 = closestPairOfPoints(px, pyRight, mid+1, end);
        int d = Math.min(d1, d2);
        
        List<Point> deltaPoints = new ArrayList<Point>();
        for(Point p : px){
            if(Math.sqrt(distance(p,px[mid])) < Math.sqrt(d)){
                deltaPoints.add(p);
            }
        }
        int d3 = closest(deltaPoints);
        return Math.min(d3, d);
    }
    
    private int closest(List<Point> deltaPoints){
        int minDistance = Integer.MAX_VALUE;
        for(int i=0; i < deltaPoints.size(); i++){
            for(int j=i+1; j <= i + SIZE && j < deltaPoints.size() ; j++){
                int distance = distance(deltaPoints.get(i), deltaPoints.get(j));
                if(minDistance < distance){
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }
    
    private int distance(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
    }
    
    private int computeMinDistance(Point[] points, int start, int end){
        if(end - start == 1){
            Point p1 = points[start];
            Point p2 = points[end];
            return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
        }else if(end - start == 2){
            Point p1 = points[start];
            Point p2 = points[end-1];
            Point p3 = points[end];
            return Math.min(Math.min((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y),
                    (p1.x - p3.x)*(p1.x - p3.x) + (p1.y - p3.y)*(p1.y - p3.y)),
                    (p2.x - p3.x)*(p2.x - p3.x) + (p2.y - p3.y)*(p2.y - p3.y));
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public static void main(String args[]){
        ClosestPairOfPoints cpp = new ClosestPairOfPoints();
        Point[] points = {new Point(6,2),new Point(4,6),new Point(5,4),new Point(-8,2),new Point(0,2)};
        double minDistance = cpp.closestPairOfPoints(points);
        System.out.println(minDistance);
    }
}
