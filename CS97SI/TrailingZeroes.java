package CS97SI;

import Utils.BladeReader;

/**
 * Created by kulkarni_my on 11/11/2016.
 */
public class TrailingZeroes {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            new TrailingZeroSolver().solve(in.nextLong());
        }
    }
}

class TrailingZeroSolver {
    public void solve(long inp) {
        int ans = 0;
        int possible = (int) Math.floor(Math.log(inp) / Math.log(5));
        for (int i = 1; i <= possible; i++) {
            ans += Math.floor(inp / Math.pow(5, i));
        }
        System.out.println(ans);
    }
}