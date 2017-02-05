package DataStructures;

import java.util.*;

/**
 * Created by Mayur Kulkarni on 2/5/2017.
 */
public class Graph<T> {
    private int size;
    private Map<T, List<T>> graph;
    private boolean isUniDirectional;
    private int time = 0;

    public Graph(int size, boolean isUniDirectional) {
        this.size = size;
        this.isUniDirectional = isUniDirectional;
        graph = new HashMap<>();
    }

    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>(5, false);
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'A');
        graph.printArticulationPoints();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        graph.forEach((node, adjacencyList) ->
                sb.append(node)
                        .append(" -> ")
                        .append(adjacencyList).append("\n"));
        return sb.toString();
    }

    private void insToMapGraph(T from, T to) {
        graph.compute(from, (key, value) -> {
            if (value == null) {
                List<T> adjList = new ArrayList<>();
                adjList.add(to);
                return adjList;
            } else {
                value.add(to);
                return value;
            }
        });
    }

    public void addEdge(T from, T to) {
        insToMapGraph(from, to);
        if (!isUniDirectional) {
            insToMapGraph(to, from);
        }
    }

    public List<T> adjacencyList(T key) {
        if (graph.containsKey(key)) {
            return graph.get(key);
        } else {
            throw new RuntimeException("Node: " + key + " not present in graph");
        }
    }

    public void printArticulationPoints() {
        Set<T> visited = new HashSet<T>();
        Map<T, Integer> visitedTime = new HashMap<T, Integer>();
        Map<T, Integer> lowTime = new HashMap<T, Integer>();
        Map<T, T> parent = new HashMap<T, T>();
        Set<T> articulationPoints = new HashSet<T>();
        for (T currNode : graph.keySet())
            if (!visited.contains(currNode))
                DFS(visited, visitedTime, currNode, lowTime, parent, articulationPoints);
        System.out.println(articulationPoints);
    }

    public void DFS(Set<T> visited,
                    Map<T, Integer> visitedTime,
                    T currNode, Map<T, Integer> lowTime,
                    Map<T, T> parent, Set<T> articulationPoints) {
        visited.add(currNode);
        visitedTime.put(currNode, time);
        lowTime.put(currNode, time);
        time++;
        int childCount = 0;
        boolean isArticulationPoint = false;
        for (T adjNode : this.adjacencyList(currNode)) {

            if (adjNode.equals(parent.get(currNode))) continue;

            if (!visited.contains(adjNode)) {
                parent.put(adjNode, currNode);
                childCount++;
                DFS(visited, visitedTime, adjNode, lowTime, parent, articulationPoints);
                if (visitedTime.get(currNode) <= lowTime.get(adjNode)) {
                    isArticulationPoint = true;
                } else {
                    lowTime.compute(currNode, (node, nodeLowTime) -> Math.min(lowTime.get(currNode), lowTime.get(adjNode)));
                }
            } else {
                lowTime.compute(currNode, (node, lowtime) -> Math.min(lowTime.get(currNode), lowTime.get(adjNode)));
            }
        }
        if ((isParent(currNode, parent) && childCount >= 2)
                || (!isParent(currNode, parent) && isArticulationPoint)) {
            articulationPoints.add(currNode);
        }
    }

    private boolean isParent(T currNode, Map<T, T> parent) {
        return parent.get(currNode) == null;
    }
}
