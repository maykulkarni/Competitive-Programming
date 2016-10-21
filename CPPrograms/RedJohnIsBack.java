package CPPrograms;

import java.util.BitSet;
import java.util.Scanner;

/**
 * Created by mayur on 11/7/16.
 */
public class RedJohnIsBack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            int n = in.nextInt();
            new RedJohnIsBackSolver().solve(n);
        }
    }
}

class RedJohnIsBackSolver {

    private long facto(int x) {
        long ans = 1;
        for (int i = 1; i <= x; i++) {
            ans *= i;
        }
        return ans;
    }

    private int seive(long x) {
        if (x == 2) {
            return 1;
        }
        if (x < 2) {
            return 0;
        }
        BitSet bs = new BitSet();
        for (int i = 2; i < x + 1; i++) {
            bs.set(i);
        }
        for (int i = 2; i * i <= x + 1; i++) {
            int temp = i;
            int index = temp;
            while (index <= x + 1) {
                index += temp;
                bs.set(index, false);
            }
        }
        int count = 0;
        for (int i = 2; i <= x; i++) {
            if (bs.get(i)) {
                //System.out.println(i);
                count++;
            }
        }
        return count;
    }

    public void solve(int n) {
        if (n < 3) {
            System.out.println(0);
            return;
        } else if (n == 4) {
            System.out.println(1);
            return;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 4];
        System.out.println(seive(dp[n]));
    }
}