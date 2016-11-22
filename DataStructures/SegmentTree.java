package DataStructures;

/**
 * Created by Mayur Kulkarni on 22/11/16.
 * E-mail : mayurkulkarni012@gmail.com
 */

public class SegmentTree {
    private long[] tree;
    private int k;
    private int[] t;
    private int n;

    public SegmentTree(int[] inp, int k) {
        this.n = inp.length;
        tree = new long[inp.length + inp.length];
        this.k = k;
        t = new int[inp.length + inp.length];
        for (int i = inp.length, j = 0; j < inp.length; i++, j++) {
            tree[i] = inp[j] % k;
            // tree[i] = inp[j];
        }
        build(inp.length);
        //System.out.println(Arrays.toString(tree));
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[]{1, 2, 3}, 2);
        //System.out.println(st.query(0, 1));
    }


    private void build(int inpLen) {
        for (int i = inpLen - 1; i > 0; i--) {
            tree[i] = (tree[i << 1] * tree[(i << 1) | 1]) % k;
            //tree[i] = tree[i << 1] + tree[(i << 1) | 1];
        }
    }

    public long query(int l, int r) {

        long resL = 1;//Long.MIN_VALUE;
        long resR = 1;//Long.MIN_VALUE;
        boolean firstL = true;
        boolean firstR = true;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) {
                if (firstL) {
                    resL = tree[l++];
                    firstL = false;
                } else {
                    resL = (resL * tree[l++]) % k;
                }
            }
            if ((r & 1) != 0) {
                if (firstR) {
                    resR = tree[--r];
                    firstR = false;
                } else {
                    resR = (resR * tree[--r]) % k;
                }
            }
        }

        if (!firstL && !firstR)
            return (resL * resR) % k;
        if (!firstL)
            return resL % k;
        if (!firstR)
            return resR % k;

        throw new RuntimeException();
    }
}
