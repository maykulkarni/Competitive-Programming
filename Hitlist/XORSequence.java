package aCurrent;

import java.util.Scanner;

/**
 * Created by mayur on 16/7/16.
 * https://www.hackerrank.com/challenges/xor-se - Implem3nt it, just bored this time
 */
public class XORSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        for (int a0 = 0; a0 < Q; a0++) {
            long L = in.nextLong();
            long R = in.nextLong();
            new XORSequenceSolver().solve(L, R);
        }
    }
}

class XORSequenceSolver {

    public void solve(long l, long r) {
    }
}