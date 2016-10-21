package MyImplementations;

public class Dijkstra {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int numberOfEdges = in.nextInt();
        int size = in.nextInt();
        UnidirectedGraph graph = new UnidirectedGraph(size);
        for (int i = 0; i < numberOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        System.out.println("SP from to ");
        int from = in.nextInt();
        int to = in.nextInt();
        new ShortestPath().findShortestPath(graph, from, to);
    }
}

class ShortestPath {

    private UnidirectedGraph graph;

    public void findShortestPath(UnidirectedGraph graph, int from, int to) {
        // QHeap heap = new QHeap();
        int numberOfAdjs = 0;
        for (AdjNode adjNode : graph.getAdjList(from)) {
            numberOfAdjs++;
        }
    }
}