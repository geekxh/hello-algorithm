package com.interview.multiarray;

/**
 * http://www.geeksforgeeks.org/a-boolean-matrix-question/
 */
public class Fill2DMatrixWith1 {

	public void fill(int input[][]){
		boolean row[] = new boolean[input.length];
		boolean col[] = new boolean[input[0].length];
		for(int i=0; i < input.length; i++){
			for(int j=0; j < input[i].length; j++){
				if(input[i][j] == 1){
					row[i] = true;
					col[j] = true;
				}
			}
		}
		for(int i=0; i < input.length; i++){
			for(int j=0; j < input[i].length; j++){
				if(row[i] || col[j]){
					input[i][j] = 1;
				}
			}
		}
	}
	
	public static void main(String args[]){
		int input[][] = {{0,0,1,0,0,0},{0,0,0,0,0,0},{1,0,0,0,0,0}};
		Fill2DMatrixWith1 fd = new Fill2DMatrixWith1();
		fd.fill(input);
		for(int i=0; i < input.length; i++){
			for(int j=0; j < input[i].length; j++){
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}
	}
}
