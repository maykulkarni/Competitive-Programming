package DataStructures;

/**
 * Created by Mayur Kulkarni on 12/7/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class DisjointSetUnion {
    int[] parent;
    int[] rank;
    int N;

    public DisjointSetUnion(int maxSize) {
        parent = new int[maxSize + 1];
        rank = new int[maxSize + 1];
        N = maxSize;
        for (int i = 1; i <= maxSize; i++) {
            make(i);
        }
    }

    public void make(int x) {
        parent[x] = x;
        rank[x] = 0;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int parx = find(x);
        int pary = find(y);
        if (parx != pary) {
            if (rank[parx] > rank[pary]) {
                parent[pary] = parx;
                rank[parx]++;
            } else {
                parent[parx] = pary;
                rank[pary]++;
            }
            N--;
        }
    }

    public long numberOfParents() {
        return N;
    }

}