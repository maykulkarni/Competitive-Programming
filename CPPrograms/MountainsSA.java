package CPPrograms;

import DataStructures.SuffixArray;
import Utils.BladeReader;

import java.util.Arrays;

/**
 * Created by kulkarni_my on 11/10/2016.
 */
public class MountainsSA {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            int size = in.nextInt();
            if (size == 1) {
                in.nextInt();
                System.out.println(0);
                continue;
            }
            char[] deltas = new char[size - 1];
            int prev = in.nextInt();
            for (int j = 0; j < size - 1; j++) {
                int curr = in.nextInt();
                deltas[j] = (char) (prev - curr + 110);
                prev = curr;
            }
            new MountainsSASolver().solve(new String(deltas));
        }
    }
}

class MountainsSASolver {
    public void solve(String input) {
        SuffixArray sa = new SuffixArray(input);
        long mod = 1_000_000_009L;
        long n = input.length();
        long total = ((n * (n + 1)) / 2) % mod;
        long[] LCPArray = Arrays.stream(sa.getLCPArray()).mapToLong(x -> x).toArray();
        for (int i = 0; i < LCPArray.length; i++) {
            total -= LCPArray[i];
        }
        if (total < 0) total += mod;
        System.out.println(total);
    }
}