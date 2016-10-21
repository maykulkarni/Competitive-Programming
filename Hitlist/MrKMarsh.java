package Hitlist;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by mayur on 11/7/16.
 * https://www.hackerrank.com/challenges/mr-k-marsh
 */

public class MrKMarsh {

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
        String[] mat = new String[row];
        for (int i = 0; i < row; i++) {
            mat[i] = in.next();
        }
        new MrKMarshSolver().sovle(convertTo2D(mat), row, col);
    }
}

class MrKMarshSolver {

    public void sovle(char[][] mat, int row, int col) {
        int maxXY = 0, maxAns = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] != 'x') {
                    Set<Integer> xs = new HashSet<>();
                    Set<Integer> ys = new HashSet<>();
                    int[][] leftMat = getLeftMat(mat, row, col, i, j);
                    int[][] upMat = getUpMat(mat, row, col, i, j);
                    for (int x = 0; x < leftMat.length; x++) {
                        for (int y = 0; y < leftMat[0].length; y++) {
                            if (leftMat[x][y] == -1 || upMat[x][y] == -1) {
                                xs.add(x);
                                ys.add(y);
                            }
                            if (!xs.contains(x) && !ys.contains(y)) {
                                if (leftMat[x][y] * upMat[x][y] > 0) {
                                    maxAns = Math.max(maxAns, (2 * leftMat[x][y]) + (2 * upMat[x][y]));
                                }
                                maxXY = Math.max(maxXY, Math.max(leftMat[x][y], upMat[x][y]));
                            }
                        }
                    }
                }
            }
        }
        if (maxAns <= 1 && maxXY > 1) {
            System.out.println(maxXY + 1);
            // System.out.println("maxxy");
        } else if (maxAns > 1) {
            System.out.println(maxAns);
            // System.out.println("ans");
        } else {
            System.out.println("impossible");
        }
    }

    private int[][] getUpMat(char[][] mat, int row, int col, int i, int j) {
        int[][] ans = new int[row - i][col - j];
        for (int x = i; x < row; x++) {
            for (int y = j; y < col; y++) {
                if (mat[x][y] == '.') {
                    if (x == i) {
                        ans[x - i][y - j] = 0;
                    } else {
                        ans[x - i][y - j] = ans[x - i - 1][y - j] + 1;
                    }
                } else {
                    ans[x - i][y - j] = -1;
                }
            }
        }
        return ans;
    }

    private int[][] getLeftMat(char[][] mat, int row, int col, int i, int j) {
        int[][] ans = new int[row - i][col - j];
        for (int x = i; x < row; x++) {
            for (int y = j; y < col; y++) {
                if (mat[x][y] == '.') {
                    if (y == j) {
                        ans[x - i][y - j] = 0;
                    } else {
                        ans[x - i][y - j] = ans[x - i][y - j - 1] + 1;
                    }
                } else {
                    ans[x - i][y - j] = -1;
                }
            }
        }
        return ans;
    }
}




















