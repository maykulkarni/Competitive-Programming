package CS97SI;

import Utils.BladeReader;
import Utils.PrimesAndDivisors;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mayur on 11/11/16.
 */
public class GoldbachConjecture {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int[] primes = PrimesAndDivisors.getPrimesUsingSeive(1000000);
        Set<Integer> set = new TreeSet<>();
        Arrays.stream(primes).forEach(set::add);
        int inp;
        while ((inp = in.nextInt()) != 0) {
            boolean done = false;
            for (int i = 0; i < primes.length && primes[i] < inp; i++) {
                if (set.contains(inp - primes[i])) {
                    System.out.println(inp + " = " + primes[i] + " + " + (inp - primes[i]));
                    done = true;
                    break;
                }
            }
            if (!done) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}
