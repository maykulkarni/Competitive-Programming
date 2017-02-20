package Algorithms;

import DataStructures.Graph;
import DataStructures.GraphNode;
import DataStructures.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Uzumaki Naruto on 2/17/2017.
 *
 */
public class Dijkstra<T> {
    private Graph<T> graph;

    Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(4, false);
        graph.addEdge(0, 1, 101);
        graph.addEdge(0, 3, 1);
        graph.addEdge(0, 2, 101);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 3, 10);
        System.out.println(new Dijkstra<Integer>(graph).shortestPaths(0));
    }

    public Map<T, Integer> shortestPaths(T from) {
        Heap<T> heap = new Heap<T>();
        Map<T, Integer> shortestPath = new HashMap<T, Integer>();
        Set<T> verticesSet = graph.verticesSet();

        // remove the initial from node
        verticesSet.remove(from);

        // Initially all are Infinity
        for (T node : graph.verticesSet()) {
            heap.add(new GraphNode<T>(node, Integer.MAX_VALUE));
        }

        // update for initial node
        for (T adjNode : graph.adjacencyList(from)) {
            heap.alterPriority(adjNode, graph.edgeWeight(from, adjNode));
        }

        while (!verticesSet.isEmpty()) {
            GraphNode<T> curr = heap.poll();
            shortestPath.put(curr.getNodeName(), curr.getWeight());
            int minDistanceToCurrNode = curr.getWeight();
            verticesSet.remove(curr.getNodeName());
            for (T node : graph.adjacencyList(curr.getNodeName()))
                if (verticesSet.contains(node)) {
                    int currWeight = heap.getPriority(node);
                    int candidateVal = minDistanceToCurrNode + graph.edgeWeight(curr.getNodeName(), node);
                    if (currWeight > candidateVal) {
                        heap.alterPriority(node, candidateVal);
                    }
                }
        }

        return shortestPath;
    }
}
