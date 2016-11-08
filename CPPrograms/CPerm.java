package CPPrograms;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by mayur on 4/11/16.
 */

public class CPerm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            solve(in.next());
        }
    }

    private static void solve(String next) {
        BigInteger mod = new BigInteger("1000000007");
        long input = Long.parseLong(next);
        if (input == 1) {
            System.out.println(0);
            return;
        }
        input--;
        BigInteger n = BigInteger.valueOf(input);
        BigInteger two = BigInteger.valueOf(2).modPow(n, mod);
        two = two.subtract(BigInteger.valueOf(2));
        System.out.println(two);
    }
}
