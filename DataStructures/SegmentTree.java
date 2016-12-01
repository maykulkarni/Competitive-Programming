package DataStructures;

import Utils.BitManipulation;

/**
 * Created by Mayur Kulkarni on 22/11/16.
 * E-mail : mayurkulkarni012@gmail.com
 */

public class SegmentTree {
    private long[] tree;
    private int n;
    private int[] d;
    private int h;

    public SegmentTree(int[] inp) {
        this.n = inp.length;
        tree = new long[inp.length + inp.length];
        d = new int[inp.length];
        h = BitManipulation.getHighestSetBitIndex(inp.length) + 1;
        for (int i = inp.length, j = 0; j < inp.length; i++, j++) {
            tree[i] = inp[j];
        }
        build(inp.length);
        //System.out.println(Arrays.toString(tree));
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(st.query(0, 8));
        st.update(0, 4, 2);
        System.out.println(st.query(0, 8));
    }

    public void updateParents(int p) {
        while (p > 1) {
            p >>= 1;
            tree[p] = Math.max(tree[p << 1], tree[(p << 1) | 1]) + d[p];
        }
    }

    public void propagate(int p) {
        for (int s = h; s > 0; s--) {
            int i = p >> s;
            if (d[i] != 0) {
                apply(i << 1, d[i]);
                apply((i << 1) | 1, d[i]);
                d[i] = 0;
            }
        }
    }

    public void update(int l, int r, int val) {
        l += n;
        r += n;
        int l0 = l;
        int r0 = r;
        for (; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) apply(l++, val);
            if ((r & 1) != 0) apply(--r, val);
        }
        updateParents(l0);
        updateParents(r0 - 1);
    }

    public void apply(int p, int val) {
        tree[p] += val;
        if (p < n) d[p] += val;
    }

    private void build(int inpLen) {
        for (int i = inpLen - 1; i > 0; i--) {
            tree[i] = Math.max(tree[i << 1], tree[(i << 1) | 1]);
        }
    }

    public long query(int l, int r) {
        long res = Long.MIN_VALUE;
        l += n;
        r += n;
        propagate(l);
        propagate(r - 1);
        for (; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) {
                res = Math.max(res, tree[l++]);
            }
            if ((r & 1) != 0) {
                res = Math.max(res, tree[--r]);
            }
        }
        return res;
    }
}
