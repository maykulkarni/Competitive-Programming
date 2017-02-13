package Random;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String args[]) {
        List<BigInteger> x = new ArrayList<>();
        x.add(new BigInteger("2"));
        x.add(new BigInteger("3"));
        BigInteger two = x.get(0);
        BigInteger three = x.get(1);
        System.out.println(two);
        x.set(0, new BigInteger("123"));
        System.out.println(two);
    }
}

