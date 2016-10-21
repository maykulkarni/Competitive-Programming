package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 7/7/16.
 * Poorest piece of code I've ever written, goodluck
 * trying to understand it :)
 */
public class CountingLuck {

    public static void main(String[] ar) {
        Scanner in = new Scanner(System.in);
        for (int test = in.nextInt(); test > 0; test--) {
            int row = in.nextInt();
            int col = in.nextInt();
            char[][] mat = new char[row][col];
            for (int i = 0; i < row; i++) {
                mat[i] = in.next().toCharArray();
            }
            new CountingLuckSolver().solve(mat, row, col, in.nextInt());
        }
    }
}

class CountingLuckSolver {

    public int row, col;
    int k;
    private boolean[][] visited;

    public static void printMat(char[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMat(boolean[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solve(char[][] mat, int row, int col, int k) {
        this.k = k;
        this.row = row;
        this.col = col;
        visited = new boolean[row][col];
        initVisited(visited, mat);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 'M') {
                    dfs(mat, i, j, countPaths(mat, i, j));
                    break;
                }
            }
        }
    }

    private void initVisited(boolean[][] visited, char[][] mat) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 'X') visited[i][j] = true;
            }
        }
    }

    private void dfs(char[][] mat, int i, int j, int pathCount) {
        if (inBounds(i, j) && visited[i][j] || !inBounds(i, j)) return;
        visited[i][j] = true;
        //System.out.println(i + " " + j + " " + pathCount);
        //printMat(visited);
        if (inBounds(i, j) && mat[i][j] == '*') {
            //System.out.print("PathCount : " + pathCount + " k: " + k + " ");
            if (pathCount == k) System.out.println("Impressed");
            else System.out.println("Oops!");
            return;
        }
        if (inBounds(i, j) && mat[i][j] != 'X') {
            if (inBounds(i + 1, j) && mat[i + 1][j] != 'X' && !visited[i + 1][j])
                dfs(mat, i + 1, j, pathCount + countPaths(mat, i + 1, j));
            if (inBounds(i - 1, j) && mat[i - 1][j] != 'X' && !visited[i - 1][j])
                dfs(mat, i - 1, j, pathCount + countPaths(mat, i - 1, j));
            if (inBounds(i, j + 1) && mat[i][j + 1] != 'X' && !visited[i][j + 1])
                dfs(mat, i, j + 1, pathCount + countPaths(mat, i, j + 1));
            if (inBounds(i, j - 1) && mat[i][j - 1] != 'X' && !visited[i][j - 1])
                dfs(mat, i, j - 1, pathCount + countPaths(mat, i, j - 1));
        } else {
            return;
        }
    }

    private int countPaths(char[][] mat, int i, int j) {
        if (inBounds(i, j) && mat[i][j] == 'X' || !inBounds(i, j) || mat[i][j] == '*') return 0;
        int count = 0;
        if (inBounds(i + 1, j) && mat[i + 1][j] != 'X' && !visited[i + 1][j])
            count++;
        if (inBounds(i - 1, j) && mat[i - 1][j] != 'X' && !visited[i - 1][j])
            count++;
        if (inBounds(i, j + 1) && mat[i][j + 1] != 'X' && !visited[i][j + 1])
            count++;
        if (inBounds(i, j - 1) && mat[i][j - 1] != 'X' && !visited[i][j - 1])
            count++;
        if (count <= 1) return 0;
        else return 1;
    }

    private boolean inBounds(int i, int j) {
        if (i >= 0 && i < row && j >= 0 && j < col)
            return true;
        return false;
    }
}
