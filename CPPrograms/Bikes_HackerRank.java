//package MyProgs;
//
//import MyImplementations.InputReader;
//
//import java.util.*;
//
//
//class Point {
//    int x;
//    int y;
//
//    Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//
//    }
//}
//
//public class Bikes_HackerRank {
//    public static void main(String[] args) {
////        int[][] capacity = {{0, 3, 0, 3, 0, 0, 0},
////                {0, 0, 4, 0, 0, 0, 0},
////                {3, 0, 0, 1, 2, 0, 0},
////                {0, 0, 0, 0, 2, 6, 0},
////                {0, 1, 0, 0, 0, 0, 1},
////                {0, 0, 0, 0, 0, 0, 9},
////                {0, 0, 0, 0, 0, 0, 0}};
////        int[][] capacity = {
////                //   0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
////                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0,  0,  0,  0,  0}, //0
////                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1,  0,  0,  0,  0}, //1
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  0}, //2
////                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0,  1,  0,  0,  0}, //3
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1,  0,  0,  0,  0}, //4
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1,  1,  0,  0,  0}, //5
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  1,  0}, //6
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //7
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //8
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //9
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //10
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //11
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  1}, //12
////                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  0}  //13
////
////        };
////
////        int[][] capacity = {
////                {0, 1, 1, 1, 0, 0, 0, 0},
////                {0, 0, 0, 0, 1, 1, 0, 0},
////                {0, 0, 0, 0, 1, 1, 0, 0},
////                {0, 0, 0, 0, 1, 1, 0, 0},
////                {0, 0, 0, 0, 0, 0, 0, 1},
////                {0, 0, 0, 0, 0, 0, 0, 1},
////                {0, 0, 0, 0, 0, 0, 0, 1},
////                {0, 0, 0, 0, 0, 0, 0, 0}
////        };
////        System.out.println(new FordFulkerson().findMaxFlow(capacity, 0, 7));
//////        //int[][] capacity = {{0, 1}, {1, 0}};
//////        // Change this accordingly
////
////
//        InputReader in = new InputReader(System.in);
//        int bikers = in.nextInt();
//        int bikes = in.nextInt();
//        int k = in.nextInt();
//        Point[] bikerPoints = new Point[bikers];
//        Point[] bikePoints = new Point[bikes];
//        for (int i = 0; i < bikers; i++)
//            bikerPoints[i] = new Point(in.nextInt(), in.nextInt());
//
//
//        for (int i = 0; i < bikes; i++)
//            bikePoints[i] = new Point(in.nextInt(), in.nextInt());
//
//        new CoinChangingSolver().solve(bikerPoints, bikePoints, k);
//
//    }
//}
//
//class CoinChangingSolver {
//    int currindex = -1;
//    Point[] bikePoints;
//    Point[] bikerPoints;
//    int k;
//
//    public void solve(Point[] bikerPoints, Point[] bikePoints, int k) {
////        int low = 0;
////        int high =
////        int[][] capacity = getCapcityMatrix(currDistance, bikePoints, bikerPoints);
//        this.k = k;
//        this.bikePoints = bikePoints;
//        this.bikerPoints = bikerPoints;
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        // System.out.println(binSearch(5, 0, 5, arr));
//        System.out.println(Math.pow(binSearch(0, 10000000, -1), 2));
//    }
//
//    public int binSearch(int low, int high, int maxFlow) {
//        if (low == high) {
//            return low;
//        } else {
//            int currentDistance = (low + high) / 2;
//            int[][] capacity = getCapcityMatrix(currentDistance);
//            maxFlow = new FordFulkerson().findMaxFlow(capacity, 0, bikePoints.length + bikerPoints.length + 1);
//            if (maxFlow >= k)
//                return binSearch(low, currentDistance, maxFlow);
//            else
//                return binSearch(currentDistance + 1, high, maxFlow);
//        }
//    }
//
//    private int[][] getCapcityMatrix(int currDistance) {
//        int[][] capacity = new int[bikerPoints.length + bikePoints.length + 2][bikerPoints.length + bikePoints.length + 2];
//        for (int i = 1; i < bikePoints.length + 1; i++) {
//            capacity[0][i] = 1;
//        }
//
//        for (int i = bikePoints.length + 1; i < bikerPoints.length + bikePoints.length + 1; i++) {
//            capacity[i][bikePoints.length + bikerPoints.length + 1] = 1;
//        }
//
//        for (int i = 0; i < bikerPoints.length; i++) {
//            for (int j = 0; j < bikePoints.length; j++) {
//                int eudis = getEuclideanDistance(bikerPoints[i], bikePoints[j]);
//                if (eudis <= currDistance) {
//                    capacity[i + 1][bikePoints.length + j + 1] = 1;
//                }
//            }
//        }
//        return capacity;
//    }
//
//    private int getEuclideanDistance(Point bikePoint, Point bikerPoint) {
//        return (int) Math.sqrt(Math.pow(bikerPoint.x - bikePoint.x, 2) + Math.pow(bikerPoint.y - bikePoint.y, 2));
//    }
//
//
//}
//
//class FordFulkerson {
//    public int findMaxFlow(int[][] capacity, int source, int destination) {
//        Map<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
//        int[][] residualCapacity = new int[capacity.length][capacity[0].length];
//        for (int i = 0; i < capacity.length; i++) {
//            for (int j = 0; j < capacity[0].length; j++) {
//                residualCapacity[i][j] = capacity[i][j];
//            }
//        }
//        int maxFlow = 0;
//        int currFlow, current, parent, minFlow;
//        while (BFS(residualCapacity, parentMap, source, destination)) {
//            current = destination;
//            minFlow = Integer.MAX_VALUE;
//            while (current != source) {
//                parent = parentMap.get(current);
//                currFlow = residualCapacity[parent][current];
//                if (minFlow > currFlow) {
//                    minFlow = currFlow;
//                }
//                current = parent;
//            }
//
//            current = destination;
//            while (current != source) {
//                parent = parentMap.get(current);
//                residualCapacity[parent][current] -= minFlow;
//                residualCapacity[current][parent] += minFlow;
//                current = parent;
//            }
//
//            maxFlow += minFlow;
//        }
//
//        return maxFlow;
//    }
//
//    private boolean BFS(int[][] residualCapacity, Map<Integer, Integer> parentMap, int source, int destination) {
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.add(source);
//        Set<Integer> visited = new HashSet<>();
//        visited.add(source);
//
//        while (!queue.isEmpty()) {
//            int parent = queue.poll();
//
//            for (int child = 0; child < residualCapacity.length; child++) {
//                if (!visited.contains(child) && residualCapacity[parent][child] > 0) {
//                    visited.add(child);
//                    queue.add(child);
//                    parentMap.put(child, parent);
//
//                    if (child == destination) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//}