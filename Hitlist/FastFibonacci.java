package Hitlist;

import MyImplementations.InputReader;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 20/7/16.
 */
public class FastFibonacci {
    private static Map<BigInteger, BigInteger> map;
    private static BigInteger mod = BigInteger.valueOf(1000000007);

    public static BigInteger nthFibonacci(BigInteger num) {
        if (num.compareTo(BigInteger.valueOf(2)) <= 0) return BigInteger.ONE;
        return solve(num.subtract(BigInteger.ONE)).mod(BigInteger.valueOf(10000));
    }

    public static BigInteger solve(BigInteger num) {
        if (map.get(num) != null) {
            return map.get(num);
        } else {
            BigInteger k = num.divide(BigInteger.valueOf(2));
            if (num.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0) {
                // f(2*k)
                map.put(num, (solve(k).multiply(solve(k)).mod(mod).add(solve(k.subtract(BigInteger.ONE)).multiply(solve(k.subtract(BigInteger.ONE))).mod(mod)).mod(mod)));
                return map.get(num);
            } else {
                // f(2*k + 1)
                map.put(num, (solve(k).multiply(solve(k.add(BigInteger.ONE))).mod(mod).add(solve(k).multiply(solve(k.subtract(BigInteger.ONE))).mod(mod))).mod(mod));
                return map.get(num);
            }
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        map = new HashMap<>();
        map.put(BigInteger.ZERO, BigInteger.ONE);
        map.put(BigInteger.ONE, BigInteger.ONE);
        int test = in.nextInt();
        BigInteger[] ls = new BigInteger[test];
        BigInteger[] rs = new BigInteger[test];
        for (int i = 0; i < test; i++) {
            ls[i] = new BigInteger(in.readString());
            rs[i] = new BigInteger(in.readString());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < test; i++) {
            BigInteger sumUptoL = nthFibonacci(ls[i]).subtract(BigInteger.ONE);
            BigInteger sumUptoR = nthFibonacci(rs[i].add(BigInteger.valueOf(1))).subtract(BigInteger.ONE);
            sb.append(sumUptoR.subtract(sumUptoL));
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
