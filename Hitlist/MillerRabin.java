package Hitlist;

import java.util.Scanner;

/**
 * Created by mayur on 17/8/16.
 */
public class MillerRabin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int iterations = in.nextInt();
        System.out.println(new MillerRabinSolver().isProbablePrime(number, iterations));
    }
}

class MillerRabinSolver {

    // Computer base ^ exp % mod
    public long modPow(long base, long exp, long mod) {
        long x = 1;
        long y = base;
        while (exp > 0) {
            if (exp % 2 == 1) {
                x = (x * y) % mod;
            }
            y = (y * y) % mod;
            exp >>= 2;
        }
        return x % mod;
    }

    // Computes (a * b) % mod
    public long modMul(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    public boolean isProbablePrime(long number, int iterations) {
        if (number < 2 || number % 2 == 0) {
            return false;
        }
        long s = number - 1;
        while (s % 2 == 0) {
            s = s >> 1;
        }
        System.out.println("s = " + s);
        for (int i = 0; i < iterations; i++) {
            long a = (int) (Math.random() * (number - 1)) + 1;
            long mod = modPow(a, s, number);
            long temp = s;
            while (temp != number - 1 && mod != 1 && mod != number - 1) {
                mod = modMul(mod, mod, number);
                System.out.println("mod :" + mod);
                temp <<= 1;
            }
            if (mod == 1) {
                return false;
            }
        }
        return true;
    }
}