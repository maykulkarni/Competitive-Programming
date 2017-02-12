package Algorithms;

/**
 * Created by mayur on 21/6/16.
 */
public class FenwickTree {

    private int[] fenwickTree;

    public FenwickTree(int size) {
        fenwickTree = new int[size + 10];
    }

    private int getParent(int x) {
        return x & (x - 1);
    }

    private int getNextIndex(int x) {
        return 2 * x - getParent(x);
    }

    public int getSize() {
        return fenwickTree.length - 1;
    }

    void increase(int id, int value) {
        id++;
        while (id < fenwickTree.length) {
            fenwickTree[id] += value;
            id = getNextIndex(id);
        }
    }

    long find(int id) {
        id++;
        id = Math.min(fenwickTree.length - 1, id);
        long res = 0;
        if (id <= 0) {
            return 0;
        }
        while (id > 0) {
            res += fenwickTree[id];
            id = getParent(id);
        }
        return res;
    }
}
