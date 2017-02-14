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
     * Computes MST cost using Prims algorithm.
     * Maintains a heap of vertices starting from start node
     * with inf weight edges, then updates the weights in heap
     * if newer edges yield lower weights
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
        long mstCost = 0;
        while (!allNodes.isEmpty()) {
            for (int adjNodes : graph.adjacencyList(curr)) {
                if (allNodes.contains(adjNodes) && heap.getPriority(adjNodes) > graph.edgeWeight(curr, adjNodes))
                    heap.alterPriority(adjNodes, graph.edgeWeight(curr, adjNodes));
            }
            GraphNode<Integer> minNode = heap.poll();
            mstCost += minNode.getWeight();
            allNodes.remove(minNode.getNodeName());
            curr = minNode.getNodeName();
        }
        System.out.println(mstCost);
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
