package Algorithms;

import DataStructures.Graph;

import java.util.*;

/**
 * Created by Uzumaki Naruto on 2/7/2017.
 * Tarjan's algorithm for finding SCCs
 */
public class StronglyConnectedComponents<T> {
    private Graph<T> graph;
    private long numberOfSCCs;
    private int time;

    StronglyConnectedComponents(Graph<T> graph) {
        this.graph = graph;
        numberOfSCCs = 0;
        time = 0;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(8, true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 7);
        graph.addEdge(7, 6);
        graph.addEdge(6, 4);
        System.out.println(graph);
        StronglyConnectedComponents<Integer> scc = new StronglyConnectedComponents<>(graph);
        scc.printSCCs();
        System.out.println("Number of SCCs: " + scc.numberOfSCCs);
    }

    public void printSCCs() {
        Set<T> visited = new HashSet<T>();
        Deque<T> stack = new ArrayDeque<T>();
        Map<T, Integer> lowTime = new HashMap<T, Integer>();
        Map<T, Integer> visitedTime = new HashMap<T, Integer>();
        for (T node : this.graph)
            if (!visited.contains(node)) {
                tarjanSCC(node, visited, stack, lowTime, visitedTime);
            }
    }

    public void tarjanSCC(T currNode, Set<T> visited, Deque<T> stack, Map<T, Integer> lowTime, Map<T, Integer> visitedTime) {
        lowTime.compute(currNode, (keyNode, lowTimeValue) -> time);
        visitedTime.compute(currNode, (keyNode, lowTimeValue) -> time);
        time++;
        stack.push(currNode);
        visited.add(currNode);
        for (T adjNode : graph.adjacencyList(currNode)) {
            if (!visited.contains(adjNode)) {
                tarjanSCC(adjNode, visited, stack, lowTime, visitedTime);
            }
            lowTime.compute(currNode, (keyCurrNode, currNodeLowTime) ->
                    Math.min(lowTime.get(currNode), lowTime.get(adjNode)));

        }
        if (visitedTime.get(currNode).equals(lowTime.get(currNode))) {
            this.numberOfSCCs++;
            T poppedNode;
            StringBuilder sb = new StringBuilder();
            while ((poppedNode = stack.pop()) != currNode) {
                sb.insert(0, poppedNode + " ");
            }
            sb.insert(0, "[ " + currNode + " ");
            sb.append("]");
            System.out.println(sb.toString());
        }
    }
}
