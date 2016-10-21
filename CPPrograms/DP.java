package CPPrograms;

/**
 * Created by mayur on 18/6/16.
 */
public class DP {
    public static int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    public static int[] dp;

    public static int cutrod(int n) {
        if (n == 0)
            return 0;
        if (dp[n] != 0)
            return dp[n];
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i] + cutrod(n - i));
        }
        dp[n] = q;
        return q;
    }

    public static void main(String[] args) {
        dp = new int[p.length];
        System.out.println(cutrod(4));
    }
}
