package Algorithms;

import Utils.BladeReader;
import Utils.NumberUtils;
import Utils.PrintMat;
import Utils.SearchUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mayur Kulkarni on 12/9/2016.
 * Email : mayurkulkarni012@gmail.com
 * Assumptions :
 * 1. "1" is always considered as root
 * 2. Base index is 1
 */

/*
9
1 2
1 3
2 4
2 5
3 6
4 7
5 8
6 9
-1 -1
 */
public class LCADP {
    static Map<Integer, List<Integer>> map;
    static int[] level;
    static int[] T;
    static int[][] P;
    static int maxH;
    static int logN;
    static int N;

    public static void main(String[] args) {
        map = new HashMap<>();
        BladeReader in = new BladeReader(System.in);
        int nodes = in.nextInt();
        N = nodes + 1;
        level = new int[N];
        logN = NumberUtils.log2(N) + 1;
        P = new int[N][logN];
        T = new int[N];
        for (int i = 0; i < nodes - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (b < a) {
                int temp = a;
                a = b;
                b = temp;
            }
            T[b] = a;
//            if (map.containsKey(a)) {
//                List<Integer> temp = map.get(a);
//                temp.add(b);
//                map.put(a, temp);
//            } else {
//                List<Integer> temp = new ArrayList<>();
//                temp.add(b);
//                map.put(a, temp);
//            }
        }
        // ****************IMP**************************
        // Considering 1 is the root, this may change!!
        T[1] = -1;
        int L = in.nextInt();
        int R = in.nextInt();
        // Warning: starting node is 1
        updateLevel(1, 0);
        maxH = SearchUtils.maxInArray(level);
        process();
        new PrintMat(P);
    }

    private static void updateLevel(int node, int lev) {
        if (map.containsKey(node)) for (int nodes : map.get(node)) {
            level[nodes] = lev + 1;
            updateLevel(nodes, lev + 1);
        }
    }

    private static void process() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < logN; j++) {
                P[i][j] = -1;
            }
        }
        for (int i = 1; i < N; i++) {
            P[i][0] = T[i];
        }
        for (int j = 1; (1 << j) < N; j++) {
            // Start node is 1
            for (int i = 1; i < N; i++) {
                System.out.println(i + " " + j);
                if (P[i][j - 1] != -1) {
                    P[i][j] = P[P[i][j - 1]][j - 1];
                }
            }
        }
    }

    public static int lca(int p, int q) {
        int log;
        // Note: lower levels are indicated by higher numbers
        // Eg. 7 is a lower level than 2.
        // It should always be the case that p is on a
        // higher level than q
        if (level[p] < level[q]) {
            int tmp = p;
            p = q;
            q = tmp;
        }
        // We need to compute Log2(p]
        // This is the maximum power of 2
        // i.e. the level number of level it can go up
        for (log = 1; 1 << log <= level[p]; log++) ;
        // There'll be an extra increment in the end
        log--;
        // We need to find an ancestor of p that is on the same
        // level of q
        // It is similar to expressing a number in power of 2.
        // So answer is guaranteed
        for (int i = log; i >= 0; i--) {
            if (level[p] - (1 << i) >= level[q]) {
                p = P[p][i];
            }
        }
        // Now both are on the same level
        if (p == q) return p;
        return -1;
    }
}





















