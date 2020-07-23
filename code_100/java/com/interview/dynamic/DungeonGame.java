package com.interview.dynamic;

/**
 * Date 03/21/2016
 * @author Tushar Roy
 *
 * Minimum life needed for knight to reach princess in 2D matrix.
 * 
 * Time complexity O(n^2)
 * Space complexity O(n^2)
 *
 * https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int[][] health = new int[dungeon.length][dungeon[0].length];

        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;

        health[m][n] = Math.max(1 - dungeon[m][n] , 1);

        for (int i = m - 1; i >= 0; i--) {
            health[i][n] = Math.max(health[i + 1][n] - dungeon[i][n], 1);
        }

        for (int i = n - 1; i >= 0; i--) {
            health[m][i] = Math.max(health[m][i+1] - dungeon[m][i], 1);
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                health[i][j] = Math.min(Math.max(health[i + 1][j] - dungeon[i][j], 1), Math.max(health[i][j + 1] - dungeon[i][j], 1));
            }
        }

        return health[0][0];
    }

    public static void main(String args[]) {
        DungeonGame dg = new DungeonGame();
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -30}};
        System.out.print(dg.calculateMinimumHP(dungeon));
    }
}
