package com.interview.multiarray;

/**
 * Iterate through matrix and get all subsquare and subrectangle matrix and print their sum
 */
public class MatrixFindAllSubSquareRectangleMatrix {

    public void printSumOfAllSquareMatrix(int input[][]){
        
        for(int len = 1; len <= input.length; len++){
            for(int i=0; i < input.length - len + 1; i++){
                for(int j=0; j < input[i].length - len + 1; j++){
                    int sum = 0;
                    for(int k=i;k < i+len; k++){
                        for(int m = j; m < j+len; m++){
                            sum += input[k][m];
                        }
                    }
                    System.out.println("Start " + i + " " + j + " End " + len + " sum " + sum);
                }
            }
        }
    }
    
    public void printSumOfAllRectangleMatrix(int input[][]){
        for(int rlen = 1 ; rlen <= input.length; rlen++){
            for(int clen = 1; clen <= input[0].length; clen++){
                for(int i=0; i < input.length - rlen + 1; i++){
                    for(int j=0; j < input[i].length - clen + 1; j++){
                        int sum = 0;
                        for(int k=i;k < i+rlen; k++){
                            for(int m = j; m < j+clen; m++){
                                sum += input[k][m];
                            }
                        }
                        System.out.println("Start " + i + " " + j + " End " + (i + rlen-1) + " "  + (j + clen-1) + " sum " + sum);
                    }
                }
            }
        }
    }
    public static void main(String args[]){
        int input[][] = {{1,2,3},
                         {4,5,6},
                         {7,8,9}};
        
        MatrixFindAllSubSquareRectangleMatrix mal = new MatrixFindAllSubSquareRectangleMatrix();
        mal.printSumOfAllRectangleMatrix(input);
    }
}
