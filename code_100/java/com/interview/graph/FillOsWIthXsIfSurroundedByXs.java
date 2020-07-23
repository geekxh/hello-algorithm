package com.interview.graph;

/**
 * Date 04/17/2016
 * @author Tushar Roy
 *
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Reference
 * https://leetcode.com/problems/surrounded-regions/
 */
public class FillOsWIthXsIfSurroundedByXs {

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }

        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = '1';
        if (i < board.length - 2) {
            dfs(board, i + 1, j);
        }
        if (i > 1) {
            dfs(board, i - 1, j);
        }
        if (j < board[0].length - 2) {
            dfs(board, i, j + 1);
        }
        if (j > 1) {
            dfs(board, i, j - 1);
        }
    }
    
    public static void main(String args[]){
        FillOsWIthXsIfSurroundedByXs fo = new FillOsWIthXsIfSurroundedByXs();
        char board[][] = {{'X','X','X','X'},
                          {'X','X','O','X'},
                          {'X','O','X','X'},
                          {'X','X','O','X'}};
        
        fo.solve(board);
    }
}
