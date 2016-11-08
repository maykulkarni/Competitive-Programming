package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 5/11/16.
 */
public class KxKGridNew {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for (int i = 0; i < testCases; i++) {
            new KxKGridSolver().solve(in.nextInt());
        }
    }
}

class KxKGridSolver {
    public void printMat(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void solve(int k) {
        int[][] grid = new int[k][k];
        int mid = (k + 1) / 2 - 1;
        for (int i = 0; i < grid.length; i++, mid++) {
            if (mid >= grid.length) mid = 0;
            int tmid = mid;
            for (int j = 0; j < grid.length; j++, tmid++) {
                if (tmid >= grid.length) tmid = 0;
                grid[i][tmid] = j + 1;
            }
        }
        printMat(grid);
    }
}