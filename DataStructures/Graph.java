package DataStructures;

import java.util.*;

/**
 * Created by Mayur Kulkarni on 2/5/2017.
 */
public class Graph<T> implements Iterable<T> {
    /**
     * Creates a generic Graph
     *
     * Attributes:
     * size: represents size of the graph
     * graph: HashMap with key as node and value as their adjacency list
     * isUnidirectional: true if graph is unidirectional
     * time: used for articulation point, starts from 0, increments after
     *       passing every vertex.
     * numberOfSCCs: counts the number of strongly connected components
     */
    private int size;
    private Map<T, List<T>> graph;
    private boolean isUniDirectional;
    private int time;

    /**
     * Constructor of graph
     * @param size size of graph
     * @param isUniDirectional whether or not it is bidirectional.
     */
    public Graph(int size, boolean isUniDirectional) {
        this.size = size;
        this.isUniDirectional = isUniDirectional;
        graph = new HashMap<>();
        time = 0;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>(5, true);
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'A');
//        graph.articulationPoints();
        for (List<Character> scc : graph.stronglyConnectedComponents()) {
            System.out.println(scc);
        }
    }

    /**
     * Pretty printing
     * @return nice representation of graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        graph.forEach((node, adjacencyList) ->
                sb.append(node)
                        .append(" -> ")
                        .append(adjacencyList).append("\n"));
        return sb.toString();
    }

    /**
     * Inserts toNode to the adjacenct list of fromNode
     *
     * @param fromNode from node
     * @param toNode   to node
     */
    private void insToMapGraph(T fromNode, T toNode) {
        graph.compute(fromNode, (key, value) -> {
            if (value == null) {
                List<T> adjList = new ArrayList<>();
                adjList.add(toNode);
                return adjList;
            } else {
                value.add(toNode);
                return value;
            }
        });
    }

    /**
     * Adds an edge in graph. If current graph is bidirectional,
     * also adds edge from toNode to fromNode
     *
     * @param fromNode from node
     * @param toNode   to node
     */
    public void addEdge(T fromNode, T toNode) {
        insToMapGraph(fromNode, toNode);
        if (!isUniDirectional) {
            insToMapGraph(toNode, fromNode);
        }
    }

    /**
     * Returns the adjacency list of the graph
     * @param key the index of node you're interested in
     * @return adjacency list of node specifief in key
     */
    public List<T> adjacencyList(T key) {
        if (graph.containsKey(key)) {
            return graph.get(key);
        } else {
            throw new RuntimeException("Node: " + key + " not present in graph");
        }
    }

    /**
     * Prints the articulation points of current graph
     */
    public Set<T> articulationPoints() {
        Set<T> visited = new HashSet<T>();
        Map<T, Integer> visitedTime = new HashMap<T, Integer>();
        Map<T, Integer> lowTime = new HashMap<T, Integer>();
        Map<T, T> parent = new HashMap<T, T>();
        Set<T> articulationPoints = new HashSet<T>();
        for (T currNode : graph.keySet())
            if (!visited.contains(currNode))
                DFSArticulationPoint(visited, visitedTime, currNode, lowTime, parent, articulationPoints);
        return articulationPoints;
    }

    /**
     * Tarjan's algorithm to find articulation point.
     * Criteria for a node for qualifying as an articulation point:
     *  1. visitedTime of current node <= lowTime of any adjacent node
     *  2. if current node is root node of DFS, and has more than 2 independent children
     * for more: https://adventinprogramming.wordpress.com/2017/02/05/articulation-points-in-graph/
     *
     * @param visited denotes the nodes which are already visited
     * @param visitedTime denotes the order in which nodes are visited
     * @param currNode the current node of this traversa;
     * @param lowTime denotes the minimum lowTime of adjacent vertices
     * @param parent parent of the current node
     * @param articulationPoints articulation points of the current graph
     */
    public void DFSArticulationPoint(Set<T> visited,
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
            // remember, we ignore the parents of current node
            if (adjNode.equals(parent.get(currNode))) continue;

            if (!visited.contains(adjNode)) {
                parent.put(adjNode, currNode);
                childCount++;
                DFSArticulationPoint(visited, visitedTime, adjNode, lowTime, parent, articulationPoints);
                if (visitedTime.get(currNode) <= lowTime.get(adjNode)) {
                    isArticulationPoint = true;
                } else {
                    lowTime.compute(currNode, (node, nodeLowTime) -> Math.min(lowTime.get(currNode), lowTime.get(adjNode)));
                }
            } else {
                lowTime.compute(currNode, (node, lowtime) -> Math.min(lowTime.get(currNode), lowTime.get(adjNode)));
            }
        }
        // first condition is satisfied only by root nodes of tarjanSCC, for rest of them there's
        // the second one
        if ((isRoot(currNode, parent) && childCount >= 2)
                || (!isRoot(currNode, parent) && isArticulationPoint)) {
            articulationPoints.add(currNode);
        }
    }

    /**
     * Tell whether the currNode is the root of the tarjanSCC or not.
     * The root of the tarjanSCC will have null as it's parent
     *
     * @param currNode The node you're interested in
     * @param parent   parent HashMap who's key is node and value is it's parent
     * @return
     */
    private boolean isRoot(T currNode, Map<T, T> parent) {
        return parent.get(currNode) == null;
    }


    @Override
    public Iterator<T> iterator() {
        return graph.keySet().iterator();
    }

    public List<List<T>> stronglyConnectedComponents() {
        Set<T> visited = new HashSet<T>();
        Deque<T> stack = new ArrayDeque<T>();
        Map<T, Integer> lowTime = new HashMap<T, Integer>();
        Map<T, Integer> visitedTime = new HashMap<T, Integer>();
        List<List<T>> SCCs = new ArrayList<>();
        for (T node : this)
            if (!visited.contains(node)) {
                tarjanSCC(node, visited, stack, lowTime, visitedTime, SCCs);
            }
        return SCCs;
    }

    /**
     * Uses Tarjan's algorithm to find the number of Strongly Connected Components.
     * Criteria for node for qualifying to become a SCC root : lowTime(node) == visitedTime(node)
     * Perform DFS and update lowTime, visitedTime. Whenever there's back edge set
     * lowTime(currNode) = min(lowTime(currNode), lowTime(adjNode)
     * lowTime(currNode) = visitedTime(currNode) indicates that this node is not dependent on
     * any other node for connectivity
     *
     * @param currNode    current node of traversal
     * @param visited     indicates whether or not visited
     * @param stack       FILO stack inserting nodes in visiting order in DFS spanning tree
     * @param lowTime     minimum low time of adjacent vertices
     * @param visitedTime time when currNode was visited
     * @param SCCs        list of list of nodes indicating list of SCCs
     */
    public void tarjanSCC(T currNode,
                          Set<T> visited, Deque<T> stack,
                          Map<T, Integer> lowTime,
                          Map<T, Integer> visitedTime,
                          List<List<T>> SCCs) {
        lowTime.compute(currNode, (keyNode, lowTimeValue) -> time);
        visitedTime.compute(currNode, (keyNode, lowTimeValue) -> time);
        time++;
        stack.push(currNode);
        visited.add(currNode);
        for (T adjNode : this.adjacencyList(currNode)) {
            if (!visited.contains(adjNode)) {
                tarjanSCC(adjNode, visited, stack, lowTime, visitedTime, SCCs);
            }
            lowTime.compute(currNode, (keyCurrNode, currNodeLowTime) ->
                    Math.min(lowTime.get(currNode), lowTime.get(adjNode)));

        }
        if (visitedTime.get(currNode).equals(lowTime.get(currNode))) {
            T poppedNode;
            StringBuilder sb = new StringBuilder();
            List<T> SCC = new LinkedList<T>();
            while ((poppedNode = stack.pop()) != currNode) {
                ((LinkedList<T>) SCC).offerFirst(poppedNode);
            }
            ((LinkedList<T>) SCC).offerFirst(currNode);
            SCCs.add(SCC);
        }
    }


}
