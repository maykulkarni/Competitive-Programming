package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 19/7/16.
 */
public class CoinOnTheTable {

    private static char[][] convertTo2D(String[] grid) {
        char[][] twod = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                twod[i][j] = grid[i].charAt(j);
            }
        }
        return twod;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int k = in.nextInt();
        String[] grid = new String[row];
        for (int i = 0; i < row; i++) grid[i] = in.next();
        new CoinOnTheTableSolver().solve(convertTo2D(grid), k);
    }
}

class CoinOnTheTableSolver {
    private final int INF = 99_999_999;

    public int delta(char[][] grid, int fromI, int fromJ, int toI, int toJ) {
        if (fromI == toI + 1 && fromJ == toJ) {
            if (grid[fromI][fromJ] == 'U') {
                return 0;
            } else {
                return 1;
            }
        } else if (fromI == toI - 1 && fromJ == toJ) {
            if (grid[fromI][fromJ] == 'D') {
                return 0;
            } else {
                return 1;
            }
        } else if (fromI == toI && fromJ == toJ + 1) {
            if (grid[fromI][fromJ] == 'L') {
                return 0;
            } else {
                return 1;
            }
        } else if (fromI == toI && fromJ == toJ - 1) {
            if (grid[fromI][fromJ] == 'R') {
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    public void solve(char[][] grid, int K) {
        long[][][] dp = new long[K + 1][grid.length][grid[0].length];
        int x = -1, y = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') {
                    x = i;
                    y = j;
                }
            }
        }
        for (int k = 0; k <= K; k++) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (k == 0) {
                        dp[k][i][j] = i == 0 & j == 0 ? 0 : INF;
                    } else {
                        dp[k][i][j] = INF;
                        if (i > 0)
                            dp[k][i][j] = Math.min(dp[k][i][j], dp[k - 1][i - 1][j] + delta(grid, i - 1, j, i, j));
                        if (i < grid.length - 1)
                            dp[k][i][j] = Math.min(dp[k][i][j], dp[k - 1][i + 1][j] + delta(grid, i + 1, j, i, j));
                        if (j > 0)
                            dp[k][i][j] = Math.min(dp[k][i][j], dp[k - 1][i][j - 1] + delta(grid, i, j - 1, i, j));
                        if (j < grid[0].length - 1)
                            dp[k][i][j] = Math.min(dp[k][i][j], dp[k - 1][i][j + 1] + delta(grid, i, j + 1, i, j));
                    }
                }
            }
        }
        long ans = INF;
        for (int i = 0; i <= K; i++) {
            ans = Math.min(ans, dp[i][x][y]);
        }
        System.out.println(ans == INF ? 0 : ans);
    }
}














































