package CPPrograms;

import java.util.*;

/**
 * Created by mayur on 9/7/16.
 */

class TreeNode {
    int data;
    List<TreeNode> children;
}

public class EvenTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertices = in.nextInt();
        int edges = in.nextInt();
        Map<Integer, TreeNode> map = new HashMap<>(vertices);
        Map<Integer, List<Integer>> ez = new HashMap<>(vertices);
        for (int i = 0; i < edges; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            if (from > to) {
                int temp = from;
                from = to;
                to = temp;
            }
            if (ez.get(from) != null) {
                ez.get(from)
                        .add(to);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(to);
                ez.put(from, list);
            }
        }
        int ans = 0;
        for (int i = 1; i <= vertices; i++) {
            int edgeCount = countEdges(ez, i);
            if (edgeCount % 2 != 0) ans++;
        }
        System.out.println(ez);
        System.out.println(ans - 1);
    }

    private static int countEdges(Map<Integer, List<Integer>> ez, int currIndex) {
        int ans = 0;
        if (ez.get(currIndex) == null) return 0;
        for (int vertices : ez.get(currIndex)) {
            ans += 1 + countEdges(ez, vertices);
        }
        return ans;
    }
}
