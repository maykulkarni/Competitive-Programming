package MyImplementations;

import Utils.BladeReader;
import Utils.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mayur Kulkarni on 12/9/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class LCADP {
    static Map<Integer, List<Integer>> map;
    static int[] level;
    static int[] T;
    static int[][] P;
    static int maxH;
    static int nr;
    static int N;

    public static void main(String[] args) {
        map = new HashMap<>();
        BladeReader in = new BladeReader(System.in);
        int nodes = in.nextInt();
        N = nodes + 1;
        level = new int[nodes + 1];
        P = new int[N][NumberUtils.log2(N)];
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
            if (map.containsKey(a)) {
                List<Integer> temp = map.get(a);
                temp.add(b);
                map.put(a, temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(b);
                map.put(a, temp);
            }
        }
        int L = in.nextInt();
        int R = in.nextInt();
        // Warning: starting node is 1
        updateLevel(1, 0);
        maxH = NumberUtils.maxInArray(level);
        nr = (int) Math.sqrt(maxH);

    }

    private static void updateLevel(int node, int lev) {
        if (map.containsKey(node)) for (int nodes : map.get(node)) {
            level[nodes] = lev + 1;
            updateLevel(nodes, lev + 1);
        }
    }

    private static void process() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                P[i][j] = -1;
            }
        }
        for (int i = 1; i < N; i++) {
            P[i][0] = T[i];
        }
        for (int j = 0; 1 << j < N; j++) {
            for (int i = 1; i < N; i++) {
                if (P[i][j - 1] != -1) {
                    P[i][j] = P[P[i][j - 1]][j - 1];
                }
            }
        }
    }

    public static void lca(int p, int q) {

    }
}





















