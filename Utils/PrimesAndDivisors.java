package Utils;

import java.util.Arrays;

/**
 * Created by mayur on 11/11/16.
 */
public class PrimesAndDivisors {
    public static int[] getPrimesUsingSeive(int limit) {
        boolean[] prime = new boolean[limit + 1];
        Arrays.fill(prime, 2, limit + 1, true);
        for (int currentNumber = 2; currentNumber * currentNumber <= limit; currentNumber++) {
            if (prime[currentNumber]) {
                for (int i = currentNumber * currentNumber; i <= limit; i += currentNumber) {
                    prime[i] = false;
                }
            }
        }
        int[] primes = new int[limit + 1];
        int cnt = 0;
        for (int i = 0; i < prime.length; i++) {
            if (prime[i]) {
                primes[cnt++] = i;
            }
        }
        return Arrays.copyOf(primes, cnt);
    }
}
