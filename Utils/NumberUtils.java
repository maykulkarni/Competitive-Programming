package Utils;

import java.math.BigInteger;
import java.util.*;

public class NumberUtils {
    private static Map<Integer, BigInteger> chooseCache = new HashMap<>();

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

    public static int getMinIntIndex(int[] arr, int endIndex) {
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

    public static int binSearchFirstOccurence(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low - 1 : ans;
    }

    public static int binSearchNumberOfItemsLessThan(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low : ans;
    }

    public static int binSearchLastOccurence(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low - 1 : ans;
    }

    private static int binSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low - 1;
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

    public static int getMinLongIndex(long[] arr) {
        int minIndex = -1;
        long minVal = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
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
