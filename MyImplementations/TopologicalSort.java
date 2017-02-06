package MyImplementations;

import java.util.*;

/**
 * Created by Uzumaki Naruto on 2/2/2017.
 */
public class TopologicalSort {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(3, 5));
        graph.put(3, Arrays.asList(4));
        graph.put(4, new ArrayList<>());
        graph.put(5, new ArrayList<>());
        graph.put(6, new ArrayList<>());
        graph.put(7, Arrays.asList(6));
        List<Integer> ansList = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        for (int i : ansList) {
            System.out.print(i + " ");
        }
    }

    private static void topologicalSort(Map<Integer, List<Integer>> graph, int currVertex, boolean[] visited, List<Integer> ans) {
        if (visited[currVertex]) return;
        visited[currVertex] = true;
        for (Integer node : graph.get(currVertex)) {
            if (!visited[node])
                topologicalSort(graph, node, visited, ans);
        }

        ((LinkedList) ans).addFirst(currVertex);
    }

    private static void checkBipartite(Map<Integer, List<Integer>> graph) {
        int N = graph.size();
        char[] color = new char[N];
        boolean[] visited = new boolean[N];
        color(graph, color, visited, 0, 'R');
        System.out.println(Arrays.toString(color));
    }

    private static char compl(char c) {
        if (c == 'R') return 'B';
        return 'R';
    }

    private static void color(Map<Integer, List<Integer>> graph, char[] color, boolean[] vis, int curr, char prevColor) {
        if (vis[curr]) return;
        vis[curr] = true;
        color[curr] = compl(prevColor);
        for (int node : graph.get(curr)) {
            if (!vis[node]) {
                color(graph, color, vis, node, compl(prevColor));
            }
        }
    }
}
