package Hitlist;

import java.util.Scanner;

/**
 * Created by mayur on 26/7/16.
 */
public class SquareSubsequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int casea = in.nextInt();
        for (int i = 0; i < casea; i++) {
            String ip = in.next();
            System.out.println(new SquareSubsequenceSolver().solve(ip));
        }
    }
}

class SquareSubsequenceSolver {

    public long longestCommonSubsequence(String one, String two) {
        long[][] dp = new long[one.length() + 1][two.length() + 1];
        long max = -1;
        for (int i = 1; i < one.length() + 1; i++) {
            for (int j = 1; j < two.length() + 1; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    if (j > 1 && (dp[i - 1][j - 1] >= one.length() || dp[i][j - 1] >= one.length()))
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, one.charAt(i - 1) == two.charAt(j - 2) ? dp[i][j - 1] + 1 : 0);
                    else
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public long solve(String ip) {
        long ans = 0;
        for (int i = 1; i < ip.length(); i++) {
            ans += longestCommonSubsequence(ip.substring(0, i), ip.substring(i, ip.length()));
        }
        return ans;
    }

    private long countUniqueSubsequences(String substring1, String substring2) {
        int k = 0;
        while (substring1.charAt(k) != substring2.charAt(0)) {
            k++;
        }
        return longestCommonSubsequence(substring1.substring(k, substring1.length()), substring2);
    }
}