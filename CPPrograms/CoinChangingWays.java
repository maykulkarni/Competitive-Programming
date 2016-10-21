package CPPrograms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 9/7/16.
 */
public class CoinChangingWays {
    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        int len = in.nextInt();
        long[] deno = new long[len];
        for (int i = 0; i < len; i++) {
            deno[i] = in.nextInt();
        }
        Arrays.parallelSort(deno);
        long[][] dp = new long[deno.length][total + 1];
        for (int i = 0; i < deno.length; i++) dp[i] = new long[total + 1];
        new CoinChangingSolver().sovle(dp, deno, total);
    }
}
// 15685693751
// 15685693751

class CoinChangingSolver {

    public void sovle(long[][] dp, long[] deno, int total) {
        dp[0][0] = 1;
        for (int i = 1; i < deno[0]; i++) {
            dp[0][i] = 0;
        }
        for (long i = deno[0]; i <= total; i++) {
            if (i % deno[0] == 0) dp[0][(int) i] = 1;
        }
        for (int i = 1; i < deno.length; i++) {
            for (int j = 0; j <= total; j++) {
                long currElement = deno[i];
                dp[i][0] = 1;
                if (j < currElement) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][(int) (j - currElement)];
                }
            }
        }
        System.out.print(dp[deno.length - 1][total]);
    }
}
