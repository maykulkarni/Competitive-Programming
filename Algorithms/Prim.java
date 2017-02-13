package Algorithms;

import DataStructures.Graph;
import DataStructures.GraphNode;
import DataStructures.Heap;

import java.util.Scanner;
import java.util.Set;

/**
 * Created by Uzumaki Naruto on 2/9/2017.
 *
 */
public class Prim {
    public static void main(String[] args) {
        Prim p = new Prim();
        p.prims();
    }

    /**
     * 5
     * 1 2 1
     * 2 3 2
     * 1 4 100
     * 2 4 3
     * 4 3 100
     */
    public void prims() {
        Graph<Integer> graph = acceptGraph();
        Heap<Integer> heap = new Heap<>();
        for (int nodes : graph.verticesSet()) {
            heap.add(new GraphNode<>(nodes, Integer.MAX_VALUE));
        }
        System.out.println(graph);
        Set<Integer> allNodes = graph.verticesSet();
        int curr = graph.verticesSet().iterator().next();
        allNodes.remove(curr);
        while (!allNodes.isEmpty()) {
            for (int adjNodes : graph.adjacencyList(curr)) {
                if (allNodes.contains(adjNodes) && heap.getPriority(adjNodes) > graph.edgeWeight(curr, adjNodes))
                    heap.alterPriority(adjNodes, graph.edgeWeight(curr, adjNodes));
            }
            GraphNode<Integer> minNode = heap.poll();
            System.out.println("MST includes edge from " + curr + " to " + minNode.getNodeName());
            allNodes.remove(minNode.getNodeName());
            curr = minNode.getNodeName();
        }
    }

    private Graph<Integer> acceptGraph() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Graph<Integer> graph = new Graph<>(size, false);
        for (int i = 0; i < size; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        return graph;
    }
}
