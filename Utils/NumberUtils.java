package Utils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by mayur on 11/11/16.
 */
public class NumberUtils {
    static Map<Integer, BigInteger> chooseCache = new HashMap<>();

    private static int hash(int n, int r) {
        return 187 * n + 97 * r;
    }

    public static int maxInArray(int[] arr) {
        int maxIndex = 0;
        int maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static long sumLong(int[] arr) {
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
        }
        return ans;
    }

    public static int maxInArray(int[] arr, int endIndex) {
        int maxIndex = 0;
        int maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < endIndex; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
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

    public static int getMinIntIndex(Integer[] arr) {
        int min = Integer.MAX_VALUE;
        int minIndx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndx = i;
            }
        }
        return minIndx;
    }

    public static int getMaxIntIndex(int[] arr) {
        int maxIndex = -1;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> factors = new LinkedHashMap<>();
        if (n == 1) {
            factors.put(1L, 1);
            return factors;
        }
        for (long d = 2; n > 1; ) {
            int power = 0;
            while (n % d == 0) {
                ++power;
                n /= d;
            }
            if (power > 0) {
                factors.put(d, power);
            }
            ++d;
            if (d * d > n) {
                d = n;
            }
        }
        return factors;
    }

    public static int getMaxLongIndex(long[] arr) {
        int maxIndex = -1;
        long maxVal = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int[] getAllDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++)
            if (n % d == 0) {
                divisors.add(d);
                if (d * d != n)
                    divisors.add(n / d);
            }
        int[] res = new int[divisors.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = divisors.get(i);
        //Arrays.sort(res);
        return res;
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
