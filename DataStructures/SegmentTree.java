package DataStructures;

import java.util.Arrays;

/**
 * Created by Mayur Kulkarni on 22/11/16.
 * E-mail : mayurkulkarni012@gmail.com
 */

public class SegmentTree {
    private long[] tree;
    private int k;
    private int[] t;
    private int n;

    SegmentTree(int[] inp, int k) {
        this.n = inp.length;
        tree = new long[inp.length + inp.length];
        this.k = k;
        t = new int[inp.length + inp.length];
        //Arrays.fill(tree, -1);
        for (int i = inp.length, j = 0; j < inp.length; i++, j++) {
            tree[i] = inp[j];
        }
        build(inp.length);
        System.out.println(Arrays.toString(tree));
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 7);
    }

    public static void add(int[] t, int i, int value) {
        i += t.length / 2;
        t[i] += value;
        for (; i > 1; i >>= 1)
            t[i >> 1] = Math.max(t[i], t[i ^ 1]);
    }

    private void build(int inpLen) {
        for (int i = inpLen - 1; i >= 0; i--) {
            tree[i] = (tree[i << 1] + tree[i << 1 | 1]);
        }
    }

    private long query(int l, int r) {
        long res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) res += tree[l++];
            if ((r & 1) != 0) res += tree[--r];
        }
        return 1L;
    }
}
