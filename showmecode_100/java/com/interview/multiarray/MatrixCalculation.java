package com.interview.multiarray;

public class MatrixCalculation {

    private int r = 0;
    
    public String[][] crossMultiply(String[][] str){
        
        int cols = 1;
        for(int i=1; i < str.length; i++){
            cols = cols*str[i].length;
        }
        String[][] result = new String[str[0].length][cols];
        for(int i=0; i < str[0].length ; i++){
            StringBuffer buffer = new StringBuffer();
            r=0;
            buffer.append(str[0][i]);
            recur(buffer,result,str,1,i);
        }
        
        return result;
        
    }
    
    private void recur(StringBuffer buffer,String[][] result,String[][] str,int currentRow,int mainCol){
        
        if(currentRow == str.length){
            result[mainCol][r++] = buffer.toString(); 
            return;
        }
        
        for(int i=0; i < str[currentRow].length; i++){
            StringBuffer tempBuffer = new StringBuffer(buffer);
            buffer.append(str[currentRow][i]);
            recur(buffer,result,str,currentRow+1,mainCol);
            buffer = tempBuffer;
        }
    }
    
    public static void main(String args[]){
        MatrixCalculation mc = new MatrixCalculation();
        String[][] str = {{"abc","def","gh"},{"l","m"},{"p","q","r"},{"x","y"}};
        String[][] result = mc.crossMultiply(str);
        for(int i=0; i < result.length; i++){
            for(int j=0; j < result[i].length; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
