import DataStructures.SuffixArray;

import java.util.Arrays;

/**
 * Created by kulkarni_my on 11/10/2016.
 */
public class SuffixArrayTest {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        new SuffixArrayTestSolver().solve(in.next());
    }
}

class SuffixArrayTestSolver {
    public void solve(String input) {
        SuffixArray sa = new SuffixArray(input);
        int[] suffixArray = sa.getSuffixArray();
        System.out.println(Arrays.toString(suffixArray));
        System.out.println(Arrays.toString(sa.getLCPArray()));
        System.out.println(sa.getLCP());
    }
}