package CPPrograms;

import MyImplementations.InputReader;

/**
 * Created by mayur on 13/7/16.
 */
public class BricksGame {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int len = in.nextInt();
            long[] stack = new long[len];
            for (int j = len - 1; j >= 0; j--) {
                stack[j] = in.nextInt();
            }
            new BricksGameSolver().solve(stack);
        }
    }
}

class BricksGameSolver {

    public void solve(long[] stack) {
        long[] sum = new long[stack.length];
        sum[0] = stack[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + stack[i];
        }
        long[] dp = new long[sum.length];
        dp[0] = sum[0];
        dp[1] = sum[1];
        dp[2] = sum[2];
        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(sum[i] - dp[i - 1], Math.max(sum[i] - dp[i - 2], sum[i] - dp[i - 3]));
        }
        System.out.println(dp[dp.length - 1]);
    }
//    private long max(long l, long l1, long l2) {
//        if (l > l1) {
//            if (l > l2) {
//                return l;
//            }
//        }
//        if (l1 > l) {
//            if (l1 > l2) {
//                return l1;
//            }
//        }
//        if (l2 > l) {
//            if (l2 > l1) {
//                return l2;
//            }
//        }
//        return Long.MAX_VALUE;
//    }
}