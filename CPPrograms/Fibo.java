public final class Fibo {

    static int dp[];

    public static int GF(int a, int b, int n) {
        dp = new int[n + 1];
        dp[0] = a;
        dp[1] = b;
        int i;
        for (i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(GF(3, 4, 5));
    }
}