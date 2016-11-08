package Utils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 3/9/16.
 */
public class Choose {
    Map<Integer, BigInteger> chooseCache = new HashMap<>();

    private static int hash(int n, int r) {
        return 187 * n + 97 * r;
    }

    public BigInteger nChooseR(int n, int r) {
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
}
