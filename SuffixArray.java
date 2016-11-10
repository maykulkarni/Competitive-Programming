import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by mayur on 10/11/16.
 */
public class SuffixArray {
    public static int[] suffixArray(CharSequence s) {
        int n = s.length();
        Integer[] sa = IntStream.range(0, n).mapToObj(Integer::valueOf).toArray(Integer[]::new);
        int[] rank = s.chars().toArray();
        for (int len = 1; len < n; len *= 2) {
            long[] rank2 = new long[n];
            for (int i = 0; i < n; i++)
                rank2[i] = ((long) rank[i]) + (i + len < n ? rank[i + len] + 1 : 0);
            Arrays.sort(sa, (a, b) -> Long.compare(rank2[a], rank2[b]));
            for (int i = 0; i < n; i++)
                rank[sa[i]] = i > 0 && rank2[sa[i - 1]] == rank2[sa[i]] ? rank[sa[i - 1]] : i;
        }
        return Arrays.stream(sa).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        suffixArray(in.nextString());
    }
}
