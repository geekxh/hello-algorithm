package com.interview.multiarray;

/**
 * http://www.geeksforgeeks.org/print-matrix-diagonally/
 */
public class MatrixInDiagonalOrder {

	public void printMatrix(int [][]matrix){
		for(int i=0; i < matrix.length; i++){
			int start =i;
			int end =0;
			while(start >= 0 && end < matrix[0].length){
				System.out.print(matrix[start][end] + " ");
				start--;
				end++;
			}
			System.out.print("\n");
		}
		
		for(int i=1; i < matrix[0].length; i++){
			int start = matrix.length-1;
			int end =i;
			while(start >= 0 && end < matrix[0].length){
				System.out.print(matrix[start][end] + " ");
				start--;
				end++;
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String args[]){
		int arr[][] = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
		MatrixInDiagonalOrder mdo = new MatrixInDiagonalOrder();
		mdo.printMatrix(arr);
	}
	
}
