package Hitlist;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Uzumaki Naruto on 7/25/2016.
 */
public class XORSum {
    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);
        // replace by InputReader
        StringBuilder ip1 = new StringBuilder(in.next()).reverse();
        StringBuilder ip2 = new StringBuilder(in.next()).reverse();
        for (int i = ip1.length(); i <= Math.pow(10, 6); i++) {
            ip1.append("0");
        }
        for (int i = ip2.length(); i <= Math.pow(10, 6); i++) {
            ip2.append("0");
        }
        System.out.println(new XORSumSolver().xor(ip1.toString(), ip2.toString()));
    }
}

class XORSumSolver {
    public int[] dp;

    public BigInteger xor(String one, String two) {
        final int n = 9;
        int numberOfOnes = 0, numberOfZeros = 0;
        int ti = -1;
        BigInteger ans = BigInteger.ZERO;
        int back = -1;
        BigInteger mod = BigInteger.valueOf(1_000_000_007);
        for (int i = 0; i < 2; i++) {
            if (two.charAt(i) == '1') {
                numberOfOnes++;
            }
            if (i == n) back = 0;
            if (back > 0) {
                if (two.charAt(back - 1) == '1') {
                    numberOfOnes--;
                }
            }
            if (one.charAt(i) == '1') {
                ti = n + 1 - numberOfOnes;
            } else {
                ti = n + 1 - (n - numberOfOnes);
            }
            if (back >= 0) back++;
            ans = ans.add((BigInteger.valueOf(2).modPow(BigInteger.valueOf(i), mod)).multiply(BigInteger.valueOf(ti))).mod(mod);
            System.out.println(i + " " + (BigInteger.valueOf(2).modPow(BigInteger.valueOf(i), mod)).multiply(BigInteger.valueOf(ti)).toString(2));
        }
        return ans;
    }
}

/*
 *  0010 = a
 *  1010 = b
 *
 */