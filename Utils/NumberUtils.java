package Utils;

import java.math.BigInteger;
import java.util.*;

public class NumberUtils {
    private static Map<Integer, BigInteger> chooseCache = new HashMap<>();

    public static long naiveFactorial(int n) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    private static int hash(int n, int r) {
        return 187 * n + 97 * r;
    }


    public static long sumLong(int[] arr) {
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
        }
        return ans;
    }

    public static long modpow(long a, long b, long mod) {
        if (b == 1) {
            return a;
        }
        if (b == 0) {
            return 1;
        }
        long ans = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return ans % mod;

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

    public static int log2(int i) {
        return (int) (Math.log10(i) / Math.log10(2));
    }

    // a^b
    public static BigInteger pow(int a, int b) {
        if (b == 0) return BigInteger.ONE;
        if (b == 1) return BigInteger.valueOf(a);
        BigInteger ans = BigInteger.ONE;
        BigInteger a_ = BigInteger.valueOf(a);
        BigInteger b_ = BigInteger.valueOf(b);
        BigInteger two = BigInteger.valueOf(2);
        while (b_.compareTo(BigInteger.ONE) >= 0) {
            if (((b_.and(BigInteger.ONE)).compareTo(BigInteger.ZERO)) != 0)
                ans = ans.multiply(a_);
            a_ = a_.multiply(a_);
            b_ = b_.divide(two);
        }
        return ans;
    }


    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        for (long i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return true;
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

    public static long[] getAllDivisors(long n) {
        List<Long> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++)
            if (n % d == 0) {
                divisors.add((long) d);
                if (d * d != n)
                    divisors.add(n / d);
            }
        long[] res = new long[divisors.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = divisors.get(i);
        return res;
    }

    public static long gcd(long i, long j) {
        return (j == 0) ? i : gcd(j, i % j);
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

    public static int[][] matrixMult(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            System.out.println("Matrix cannot be multiplied");
            throw new RuntimeException();
        }
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < a[0].length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }

    public static int[][] matrixExp(int[][] a, int exp) {
        if (exp == 1) {
            return a;
        }
        int[][] tmp = identity(a.length, a[0].length);
        while (exp > 0) {
            if ((exp & 1) == 1)
                tmp = NumberUtils.matrixMult(tmp, a);
            a = NumberUtils.matrixMult(a, a);
            exp >>= 1;
        }
        return tmp;
    }
//
//    public static long gcd(long a, long b) {
//        return b == 0 ? Math.abs(a) : gcd(b, a % b);
//    }

    public static int[][] identity(int n, int m) {
        int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            tmp[i][i] = 1;
        }
        return tmp;
    }

    public static double[][] identityDouble(int n, int m) {
        double[][] tmp = new double[n][m];
        for (int i = 0; i < n; i++) {
            tmp[i][i] = 1;
        }
        return tmp;
    }

    public double euclideanDist(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2)
                + Math.abs(y1 - y2) * Math.abs(y1 - y2));
    }
}
