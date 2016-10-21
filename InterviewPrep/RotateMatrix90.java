package InterviewPrep;

import java.util.Scanner;

/**
 * Created by mayur on 25/8/16.
 */
public class RotateMatrix90 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] ip = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ip[i][j] = in.nextInt();
            }
        }
        int[][] ans = new int[3][3];
        for (int currCol = 0, i = 0; currCol < 3; currCol++, i++) {
            for (int currRow = 2, j = 0; currRow >= 0; currRow--, j++) {
                ans[i][j] = ip[currRow][currCol];
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
