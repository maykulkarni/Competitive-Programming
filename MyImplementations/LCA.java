package MyImplementations;

import Utils.BladeReader;
import Utils.NumberUtils;

import java.util.*;

/**
 * Created by Mayur Kulkarni on 12/8/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class LCA {
    static Map<Integer, List<Integer>> map;
    static int[] level;
    static int[] T;
    static int[] P;
    static int maxH;
    static int nr;
    int[][] M;

    public LCA() {

    }


    public static void main(String[] args) {
        map = new HashMap<>();
        BladeReader in = new BladeReader(System.in);
        int nodes = in.nextInt();
        level = new int[nodes + 1];
        P = new int[nodes + 1];
        T = new int[nodes + 1];
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
        dfs(1);
        System.out.println("level");
        System.out.println(Arrays.toString(level));
        System.out.println("T");
        System.out.println(Arrays.toString(T));
        System.out.println("P");
        System.out.println(Arrays.toString(P));
        System.out.println("LCA for " + L + " " + " " + R + " is : " + lca(L, R));
    }

    public static int lca(int i, int j) {
        while (P[i] != P[j]) {
            if (level[i] > level[j]) {
                i = P[i];
            } else {
                j = P[j];
            }
        }

        while (i != j) {
            if (level[i] > level[j]) {
                i = T[i];
            } else {
                j = T[j];
            }
        }

        return i;
    }

    public static void dfs(int currNode) {
        if (level[currNode] < nr) {
            P[currNode] = 1;
        } else {
            if (level[currNode] % nr == 0) {
                P[currNode] = T[currNode];
            } else {
                P[currNode] = P[T[currNode]];
            }
        }
        if (map.containsKey(currNode)) for (int nodes : map.get(currNode)) {
            dfs(nodes);
        }
    }

    private static void updateLevel(int node, int lev) {
        if (map.containsKey(node)) for (int nodes : map.get(node)) {
            level[nodes] = lev + 1;
            updateLevel(nodes, lev + 1);
        }
    }
}