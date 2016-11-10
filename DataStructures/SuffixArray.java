package DataStructures;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by mayur on 10/11/16.
 */
public class SuffixArray {
    private int[] suffixArray;
    private CharSequence s;
    private int[] LCPArray;

    public SuffixArray(CharSequence input) {
        this.s = input;
    }

    // Creates Suffix array in O(N*(LogN)^2)
    public int[] getSuffixArray() {
        if (suffixArray != null) {
            return suffixArray;
        }
        int n = s.length();
        // Integer array [0...N] telling which character corresponds to which rank
        Integer[] sa = IntStream.range(0, n).mapToObj(Integer::valueOf).toArray(Integer[]::new);
        // Initial ranks of all characters (ASCII values)
        int[] rank = s.chars().toArray();

        // len is a power of two, so the initial point where len = 1
        // actually compares all the suffixes of len 2, and so on
        // upto the last power of 2 smaller than string
        for (int len = 1; len < n; len *= 2) {
            // current ranking
            long[] rank2 = new long[n];
            // compute the ranking of len + 1 th step
            // using the rankings at the previous step
            for (int i = 0; i < n; i++)
                // raise the previous one to 32 to differentiate between unequal suffixes
                rank2[i] = ((long) rank[i] << 32) + (i + len < n ? rank[i + len] + 1 : 0);
            // Sort the arrays with respect to this rankings
            Arrays.sort(sa, (a, b) -> Long.compare(rank2[a], rank2[b]));

            // If there are 2 suffixes which have same ranking, then
            // give their previous ranking to both of them, else update
            // them with respect to rank2

            for (int i = 0; i < n; i++) {
                rank[sa[i]] = i > 0 && rank2[sa[i - 1]] == rank2[sa[i]] ? rank[sa[i - 1]] : i;
            }
        }
        // return the answer
        suffixArray = Arrays.stream(sa).mapToInt(Integer::intValue).toArray();
        return suffixArray;
    }

    public int[] getLCPArray() {
        if (LCPArray != null) return LCPArray;

        LCPArray = lcpArray(getSuffixArray(), s);
        return LCPArray;
    }

    private int[] lcpArray(int[] sa, CharSequence s) {
        int n = sa.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;
        int[] lcp = new int[n - 1];
        for (int i = 0, h = 0; i < n; i++) {
            if (rank[i] < n - 1) {
                for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h)
                    ;
                lcp[rank[i]] = h;
                if (h > 0)
                    --h;
            }
        }
        return lcp;
    }

    public String getLCP() {
        int maxLCPIndex = -1;
        if (LCPArray == null)
            getLCPArray();
        int maxLCPLength = -1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (LCPArray[i] > maxLCPLength) {
                maxLCPLength = LCPArray[i];
                maxLCPIndex = i;
            }
        }
        return s.subSequence(maxLCPIndex, maxLCPLength).toString();
    }
}
