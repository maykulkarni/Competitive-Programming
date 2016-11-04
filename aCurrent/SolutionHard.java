package aCurrent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 2/11/16.
 */
public class SolutionHard {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        int distance = in.nextInt();
        int[][] graph = new int[nodes][2];
        for (int i = 0; i < graph.length; i++) Arrays.fill(graph[i], -1);
        for (int i = 0; i < edges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int label = in.nextInt();
            graph[from][label] = to;
            graph[to][label] = from;
        }
        new SolutionHardSolver().solve(graph, distance);
    }
}

class SolutionHardSolver {
    public void solve(int[][] graph, int distance) {
        int limit = (int) Math.pow(2, distance);
        for (int i = 1; i <= limit; i++) {
            String binStr = Integer.toBinaryString(i);
        }
    }
}
