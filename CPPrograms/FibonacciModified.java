package CPPrograms;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by mayur on 10/7/16.
 */
public class FibonacciModified {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int firstTerm = in.nextInt();
        int secTerm = in.nextInt();
        int nThTerm = in.nextInt();
        new FibonacciSolver().solve(firstTerm, secTerm, nThTerm);
    }
}

class FibonacciSolver {

    public void solve(int firstTerm, int secTerm, int nThTerm) {
        BigInteger[] ans = new BigInteger[nThTerm];
        ans[0] = BigInteger.valueOf(firstTerm);
        ans[1] = BigInteger.valueOf(secTerm);
        for (int i = 2; i < nThTerm; i++) {
            ans[i] = ans[i - 1].multiply(ans[i - 1]).add(ans[i - 2]);
        }
        System.out.println(ans[nThTerm - 1]);
    }
}
