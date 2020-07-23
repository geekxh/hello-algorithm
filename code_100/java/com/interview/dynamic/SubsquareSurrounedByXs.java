package com.interview.dynamic;

/**
 * Date 09/15/2014
 * @author tusroy
 * 
 * Find maximum subsquare in a matrix made up of Xs and Os such that all four sides of subsquare are Xs. It does not matter what is inside
 * the subsquare. All 4 sides should be made up entirely of Xs
 * 
 * e.g 
 * 0 0 0 0 0 X         0,0  0,0  0,0  0,0  0,0  1,1
 * 0 X 0 X X X         0,0  1,1  0,0  1,1  1,2  2,3 
 * 0 X 0 X 0 X         0,0  2,1  0,0  2,1  0,0  3,1
 * 0 X X X X X         0,0  3,1  1,2  3,3  1,4  4,5
 * 0 0 0 0 0 0         0,0  0,0  0,0  0,0  0,0  0,0   
 * 
 * Output of above program should be 3
 * 
 * Solution
 * Have another matrix which is capable of holding 2 values hori and ver. 
 * Ver stores how far vertically you can see Xs. Hori stores how far horizontally you can see Xs.
 * Once this matrix is build look for biggest subsquare by getting min of hori and ver at each point and checking
 * if subsquare can be formed from value min to 1.
 * 
 * Test cases:
 * Matrix entirely made up of Xs
 * Matrix entirely made up of Os
 * Matrix with Xs and Os but maximum subsquare is length 1
 */
public class SubsquareSurrounedByXs {

    class Cell{
        int ver;
        int hori;
    }
    public int findSubSquare(char input[][]){
        Cell T[][] = new Cell[input.length][input[0].length];
        for(int i=0; i < T.length; i++){
            for(int j=0; j < T[0].length; j++){
                T[i][j] = new Cell();
            }
        }
    
        for(int i=0; i < input.length; i++){
            for(int j=0; j < input[0].length; j++){
                if(input[i][j] == 'X'){
                    if(i == 0 && j == 0){
                        T[i][j].hori = 1;
                        T[i][j].ver = 1;
                    }
                    else if(i == 0){
                        T[i][j].hori = T[i][j-1].hori + 1;
                        T[i][j].ver = 1;
                    }else if(j == 0){
                        T[i][j].ver = T[i-1][j].ver +1;
                        T[i][j].hori = 1;
                    }else{
                        T[i][j].hori = T[i][j-1].hori +1;
                        T[i][j].ver = T[i-1][j].ver + 1;
                    }
                }
            }
        }
        for(int i=0; i < T.length; i++){
            for(int j=0; j < T[0].length; j++){
                System.out.print(T[i][j].ver + "," + T[i][j].hori+ "  ");
            }
            System.out.println();
        }
        
        //start iterating from bottom right corner and find min of hori or ver at every cell.
        //If this is greater than 1 then see if you can find a number between this min and 1
        //such that on left's ver and top's hori is greater greater than or equal to k.
        int max = 1;
        for(int i=T.length -1; i >=0 ; i--){
            for(int j= T[0].length-1 ; j >=0; j--){
                if(T[i][j].ver == 0 || T[i][j].ver == 1 || T[i][j].hori ==1 ){
                    continue;
                }
                int min = Math.min(T[i][j].ver, T[i][j].hori);
                int k = 0;
                for(k=min; k > 1; k--){
                    if(T[i][j-k+1].ver >= k && T[i-k+1][j].hori >= k){
                        break;
                    }
                }
                if(max < k){
                    max = k;
                }
            }
        }
        
        return max;
    }
    
    public static void main(String args[]){
        char[][] input = {{'X','O','O','O','O','O'},
                          {'O','O','O','O','O','O'},
                          {'X','X','X','X','O','O'},
                          {'X','X','X','X','X','O'},
                          {'X','O','O','X','X','O'},
                          {'X','O','X','X','X','O'}};
        
        char [][] input1 = {{'O', 'O', 'O', 'O', 'O', 'X'},
                            {'O', 'X', 'O', 'X', 'X', 'X'},
                            {'O', 'X', 'O', 'X', 'O', 'X'},
                            {'O', 'X', 'X', 'X', 'X', 'X'},
                            {'O', 'O', 'O', 'O', 'O', 'O'},
        };
        
        char [][] input2 = {{'O', 'O', 'X', 'O', 'X'},
                            {'O', 'X', 'X', 'O', 'X'},
                            {'O', 'X', 'O', 'X', 'X'},
                            {'X', 'X', 'X', 'X', 'X'},
                            {'O', 'X', 'X', 'X', 'O'},
                           };

        SubsquareSurrounedByXs ss = new SubsquareSurrounedByXs();
        System.out.println(ss.findSubSquare(input));
        System.out.println(ss.findSubSquare(input1));
        System.out.println(ss.findSubSquare(input2));
    }
    
}
