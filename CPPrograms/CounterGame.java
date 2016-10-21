package CPPrograms;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by mayur on 15/7/16.
 */
public class CounterGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            BigInteger ip = in.nextBigInteger();
            new CounterGameSolver().solve(ip);
            //    new CounterGameSolver().largestPowOf2LessThanN(ip);
        }
    }
}

class CounterGameSolver {
    public void solve(BigInteger ip) {
        int cnt = 0;
        String bigint = ip.toString(2);
        boolean found = false;
        System.out.println(bigint);
        for (int i = bigint.length() - 1; i >= 0; --i) {
            if (found) {
                if (bigint.charAt(i) == '1') {
                    cnt++;
                }
            } else {
                if (bigint.charAt(i) == '1') {
                    found = true;
                }
                cnt++;
            }
        }
        System.out.println(cnt - 1);
        System.out.println((cnt - 1) % 2 == 0 ? "Richard" : "Louis");
    }
}