package com.interview.graph;

import java.util.LinkedList;
import java.util.Queue;

enum Cell{
	SPACE,
	BLOCK,
	GUARD
}

class Point{
	int x;
	int y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

/**
 * @Date 07/05/2015
 * @author Tushar Roy
 * 
 * Given a 2 D floor plan with empty space, block and multiple exits. Find distance of every empty space
 * from nearest exits in case of fire emergency.
 * 
 * Idea is to start from every exit position and do BFS search in all 4 directions and maintain the distance of every
 * space from exit. If another exit in future iterator is closer than already calculated exit then update
 * the distance.
 * 
 * Space complexity is O(n*m)
 * Time complexity is O(number of exit * m * n);
 *
 */
public class ShortestDistanceFromExit {

	public int[][] findShortest(Cell input[][]){
		int distance[][] = new int[input.length][input[0].length];
		for(int i=0; i < input.length; i++){
			for(int j=0; j < input[0].length; j++){
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=0; i < input.length; i++){
			for(int j =0; j < input[i].length; j++){
				//for every exit location do a BFS starting with this exit as the origin
				if(input[i][j] == Cell.GUARD){
					distance[i][j] = 0;
					setDistance(input, i, j, distance);
				}
			}
		}
		return distance;
		
	}
	
	private void setDistance(Cell input[][], int x, int y, int distance[][]){
		
		boolean visited[][] = new boolean[input.length][input[0].length];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x,y));
		//Do a BFS at keep updating distance.
		while(!q.isEmpty()){
			Point p = q.poll();
			setDistanceUtil(q, input, p, getNeighbor(input, p.x+1, p.y), distance, visited);
			setDistanceUtil(q, input, p, getNeighbor(input, p.x, p.y+1), distance, visited);
			setDistanceUtil(q, input, p, getNeighbor(input, p.x-1, p.y), distance, visited);
			setDistanceUtil(q, input, p, getNeighbor(input, p.x, p.y-1), distance, visited);
		}
	}

	private void setDistanceUtil(Queue<Point> q, Cell input[][], Point p, Point newPoint, int distance[][], boolean visited[][]){
		if(newPoint != null && !visited[newPoint.x][newPoint.y]){
			if(input[newPoint.x][newPoint.y] != Cell.GUARD && input[newPoint.x][newPoint.y] != Cell.BLOCK){
				distance[newPoint.x][newPoint.y] = Math.min(distance[newPoint.x][newPoint.y], 1 + distance[p.x][p.y]);
				visited[newPoint.x][newPoint.y] = true;
				q.offer(newPoint);
			}
		}
	}
	
	private Point getNeighbor(Cell input[][], int x, int y){
		if(x < 0 || x >= input.length || y < 0 || y >= input[0].length ) {
			return null;
		}
		return new Point(x,y);
	}
	public static void main(String args[]){
		ShortestDistanceFromExit sdg = new ShortestDistanceFromExit();
		Cell input[][] = {{Cell.SPACE, Cell.SPACE, Cell.BLOCK, Cell.BLOCK},
						  {Cell.SPACE, Cell.SPACE, Cell.GUARD, Cell.SPACE},
						  {Cell.SPACE, Cell.SPACE, Cell.BLOCK, Cell.SPACE},
						  {Cell.SPACE, Cell.GUARD, Cell.BLOCK, Cell.SPACE}
						 };		
		int result[][] = sdg.findShortest(input);
		for(int i=0; i < result.length; i++) {
			for(int j=0; j < result[0].length; j++){
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
}
