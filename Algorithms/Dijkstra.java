package Algorithms;

import DataStructures.Graph;
import DataStructures.GraphNode;
import DataStructures.Heap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Uzumaki Naruto on 2/17/2017.
 */
public class Dijkstra<T> {
    private Graph<T> graph;

    Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public Map<T, Integer> shortestPaths(T from) {
        Heap<T> heap = new Heap<T>();
        Map<T, Integer> shortestPath = new HashMap<T, Integer>();
        for (T node : graph.verticesSet()) {
            heap.add(new GraphNode<T>(node, Integer.MAX_VALUE));
        }
        for (T adjNode : graph.adjacencyList(from)) {
            heap.alterPriority(adjNode, graph.edgeWeight(from, adjNode));
        }
        return null;
    }
}
