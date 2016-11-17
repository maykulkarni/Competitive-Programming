package Utils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 11/11/16.
 */
public class NumberUtils {
    static Map<Integer, BigInteger> chooseCache = new HashMap<>();

    private static int hash(int n, int r) {
        return 187 * n + 97 * r;
    }

    public static BigInteger nChooseR(int n, int r) {
        if (r > n) {
            return BigInteger.valueOf(0);
        }
        if ((r == 1) || ((n - r) == 1))
            return BigInteger.valueOf(n);
        if ((r == 0) || ((n - r) == 0))
            return BigInteger.valueOf(1);
        int hash = hash(n, r);
        if (chooseCache.containsKey(hash)) {
            return chooseCache.get(hash);
        }
        BigInteger N = BigInteger.valueOf(n);
        BigInteger R = BigInteger.valueOf(r);
        BigInteger ans = (N.multiply(nChooseR(n - 1, r - 1))).divide(R);
        chooseCache.put(hash, ans);
        return ans;
    }

    public static BigInteger catalan(int n) {
        return nChooseR(2 * n, n).divide(BigInteger.valueOf(n + 1));
    }

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
