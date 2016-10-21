package CPPrograms;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Uzumaki Naruto on 7/25/2016.
 */
public class LongestCommonSubsequence {
    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);
        int first = in.nextInt();
        int sec = in.nextInt();
        int[] firArray = new int[first];
        int[] secArray = new int[sec];
        for (int i = 0; i < first; i++) firArray[i] = in.nextInt();
        for (int i = 0; i < sec; i++) secArray[i] = in.nextInt();
        new LongestCommonSubsequenceSolver().solve(firArray, secArray);
    }
}

class LongestCommonSubsequenceSolver {

    private void printAnswer(int[][] dp, int[] first, int[] sec) {
        LinkedList<Integer> ans = new LinkedList<>();
        int i = dp.length - 1;
        int j = dp[0].length - 1;
        while (dp[i][j] != 0) {
            if (dp[i - 1][j - 1] < dp[i][j] && dp[i - 1][j] < dp[i][j] && dp[i][j - 1] < dp[i][j]) {
                ans.addFirst(first[i - 1]);
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        ans.forEach(x -> System.out.print(x + " "));
    }

    public void solve(int[] first, int[] second) {
        int[][] dp = new int[first.length + 1][second.length + 1];
        for (int i = 1; i < first.length + 1; i++) {
            for (int j = 1; j < second.length + 1; j++) {
                if (first[i - 1] == second[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
//        for (int i = 0; i < first.length + 1; i++) {
//            for (int j = 0; j < second.length + 1; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        //       System.out.println("******");
        printAnswer(dp, first, second);
    }
}