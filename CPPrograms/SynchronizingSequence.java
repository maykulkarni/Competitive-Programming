package CPPrograms;

import java.util.*;

public class SynchronizingSequence {
    public static void main(String[] args) {
        int[][] test = {{1, 2}, {1, 1}, {2, 2}};
        System.out.println(Answer.answer(test));
    }
}

class Answer {
    private static Set<List<Integer>> reachableStates;
    private static List<List<List<Integer>>> gPrime;
    private static Set<Set<Integer>> alreadySeen;
    private static int ctr = 0;

    public static int nChooseTwo(int n) {
        return (n * (n - 1)) >> 1;
    }

    private static int[][] closeStation(int[][] subway, int close) {
        int[][] newSubway = new int[subway.length][subway[0].length];
        for (int i = 0; i < subway.length; i++) {
            for (int j = 0; j < subway[0].length; j++) {
                if (i == close) {
                    newSubway[i][j] = -1;
                } else {
                    newSubway[i][j] = subway[i][j];
                }
            }
        }
        int[] closedStation = subway[close];
        for (int i = 0; i < newSubway.length; i++) {
            for (int j = 0; j < newSubway[0].length; j++) {
                if (newSubway[i][j] == close) {
                    if (closedStation[j] != close) {
                        newSubway[i][j] = closedStation[j];
                    } else {
                        newSubway[i][j] = i;
                    }
                }
            }
        }
        return newSubway;
    }

    private static void addCombinationToSet(Set<Integer> nodeSet) {
        List<Integer> nodes = new ArrayList<>(nodeSet);
        if (nodes.size() == 2) {
            reachableStates.add(Arrays.asList(nodes.get(0), nodes.get(1)));
        } else {
            for (int i = 0; i < nodes.size() - 1; i++) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    reachableStates.add(Arrays.asList(nodes.get(i), nodes.get(j)));
                }
            }
        }
    }

    private static void BFS(int node, int maxLines, int subwayLen, boolean isSomeStationClosed) {
        int nChooseTwo = isSomeStationClosed ? nChooseTwo(subwayLen - 1) : nChooseTwo(subwayLen);
        try {
            if (reachableStates.size() >= nChooseTwo || gPrime.get(node).get(0).get(0) == -1) {
                return;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        LinkedList<EntryTreeSet> queue = new LinkedList<>();
        for (List<Integer> ints : gPrime.get(node)) {
            Set<Integer> temp = new TreeSet<>(ints);
            if (temp.size() > 1) {
                queue.add(new EntryTreeSet(temp, 0));
            }
        }
        for (EntryTreeSet added : queue) {
            alreadySeen.add(added.getTreeSet());
            addCombinationToSet(added.getTreeSet());
        }
        int x = 0;
        while (!queue.isEmpty()) {
            //TreeSet
            if (x == 20) System.out.println("q size : " + queue.size() + " reachable states  : " + reachableStates.size());
            EntryTreeSet currentSet = queue.poll();
            for (int i = 0; i < maxLines; i++) {
                Set<Integer> tset = new TreeSet<>();
                for (int ints : currentSet.getTreeSet()) {
                    for (int j : gPrime.get(ints).get(i)) {
                        tset.add(j);
                    }
                }
                EntryTreeSet ets = new EntryTreeSet(tset, currentSet.getCtr() + 1);
                if (tset.size() > 1 && !alreadySeen.contains(tset) && ets.getCtr() < subwayLen) {
                    alreadySeen.add(tset);
                    queue.add(ets);
                    addCombinationToSet(tset);
                }
                if (reachableStates.size() >= nChooseTwo) {
                    return;
                }
            }
        }
    }

    public static int tryClosingStation(int[][] subway) {
        int[][] closedSubway;
        alreadySeen = new HashSet<>();
        reachableStates = new HashSet<>();
        for (int i = 0; i < subway.length; i++) {
            System.out.println("Closing station " + i);
            closedSubway = closeStation(subway, i);
            if (checkIfResetWordExists(closedSubway, true)) {
                return i;
            }
        }
        return -2;
    }

    private static boolean checkIfResetWordExists(int[][] subway, boolean isSomeStationClosed) {
        createGraphGprime(subway);
        for (int i = 0; i < subway.length; i++) {
            alreadySeen = new HashSet<>(); // init a new hashset for every time
            BFS(i, subway[0].length, subway.length, isSomeStationClosed);
        }
        return reachableStates.size() == (isSomeStationClosed ? nChooseTwo(subway.length - 1) : nChooseTwo(subway.length));
    }

    public static int answer(int[][] subway) {
        alreadySeen = new HashSet<>();
        reachableStates = new HashSet<>();
        if (checkIfResetWordExists(subway, false)) {
            return -1;
        } else {
            return tryClosingStation(subway);
        }
    }

    private static void createGraphGprime(int[][] subway) {
        gPrime = new ArrayList<>();
        // create blank list
        for (int i = 0; i < subway.length; i++) {
            List<List<Integer>> blankList = new ArrayList<>();
            for (int j = 0; j < subway[0].length; j++) {
                List<Integer> internalBlankList = new ArrayList<>();
                blankList.add(internalBlankList);
            }
            gPrime.add(blankList);
        }
        for (int i = 0; i < subway.length; i++) {
            for (int j = 0; j < subway[0].length; j++) {
                if (subway[i][0] == -1) {
                    gPrime.get(i).get(j).add(-1);
                } else {
                    gPrime.get(subway[i][j]).get(j).add(i);
                }
            }
        }
    }
}

class EntryTreeSet {
    Set<Integer> tset;
    int ctr;

    EntryTreeSet(Set<Integer> tset, int ctr) {
        this.tset = tset;
        this.ctr = ctr;
    }

    public Set<Integer> getTreeSet() {
        return tset;
    }

    public int getCtr() {
        return ctr;
    }
}




/*
Test case 1 : {{2, 1}, {2, 0}, {3, 1}, {1, 0}}
Test case 2 : {{1, 2}, {1, 1}, {2, 2}}
Test case 5 : { {2, 3, 0}, {2, 4, 1}, {0, 5, 2}, {4, 6, 3}, {5, 7, 4}, {3, 8, 5}, {7, 9, 6}, {8, 10, 7}, {6, 11, 8}, {10, 12, 9}
                {11, 13, 10}, {9, 14, 11}, {13, 15, 12}, {14, 16, 13}, {12, 17, 14}, {16, 18, 15}, {17, 19, 16}, {15, 20, 17}, {19, 21, 18}, {20, 22, 19}
                {18, 23, 20}, {22, 24, 21}, {23, 25, 22}, {21, 26, 23}, {25, 27, 24}, {26, 28, 25}, {24, 29, 26}, {28, 30, 27}, {29, 31, 28}, {27, 32, 29}
                {31, 33, 30}, {32, 34, 31}, {30, 35, 32}, {34, 36, 33}, {35, 37, 34}, {33, 38, 35}, {37, 39, 36}, {38, 40, 37}, {36, 41, 38}, {40, 42, 39}
                {41, 43, 40}, {39, 44, 41}, {43, 45, 42}, {44, 46, 43}, {42, 47, 44}, {46, 0, 46}, {47, 1, 45}, {45, 2, 47}
                }
 */





















