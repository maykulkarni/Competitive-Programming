package Algorithms;

import DataStructures.Graph;
import DataStructures.GraphNode;
import DataStructures.Heap;

import java.util.Scanner;

/**
 * Created by Uzumaki Naruto on 2/9/2017.
 *
 */
public class Prim {
    public static void main(String[] args) {
        Prim p = new Prim();
        p.prims();
    }

    public void prims() {
        Graph<Integer> graph = acceptGraph();
        Heap<Integer> heap = new Heap<>();
        for (int nodes : graph.verticesSet()) {
            heap.add(new GraphNode<>(nodes, -1));
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
