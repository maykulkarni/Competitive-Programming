package aCurrent;

import java.math.BigInteger;

/**
 * Created by mayur on 1/11/16.
 */
public class demo {
    public static void main(String[] args) {
        BigInteger x = BigInteger.ONE;
        BigInteger y = x;
        x = x.add(BigInteger.ONE);
        System.out.println(x);
        System.out.println(y);
    }
}
