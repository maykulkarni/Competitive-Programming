import MyImplementations.PrintMat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kulkarni_my on 11/8/2016.
 */
class BladeReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public BladeReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public String nextString() {
        return next();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public int[] readIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = nextInt();
        }
        return array;
    }

    public long[] readLongArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = nextLong();
        }
        return array;
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
public class GiftAndChefRevisited {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            new GiftAndChefRevisitedSolver().solve(in.nextString(), in.nextString());
        }
    }
}

class GiftAndChefRevisitedSolver {
    private int substringLength;
    private Map<Integer, List<Integer>> listMap;
    private int totalMatches = 0;
    private List<Integer> allOccurencesStartIndices;
    private int maxDepth = -1;
    private Map<Integer, Integer> indexMap;

    private boolean inRange(int zeroDepthIndex, int currentStartIndex) {
        return currentStartIndex <= zeroDepthIndex + substringLength - 1;
    }

    private int getDepth(int zeroDepthIndex, int currentStartIndex, int currentDepth) {
        if (currentDepth == -1) {
            // First Time
            return 1;
        } else {
            if (inRange(zeroDepthIndex, currentStartIndex)) {
                return currentDepth + 1;
            } else {
                return 1;
            }
        }
    }

    public void solve(String string, String substring) {
        long mod = 1_000_000_007L;
        prepareListMap(string, substring);
        prepareIndexMap();
        if (maxDepth == 1) {
            System.out.println("This is a very fast answer");
        }
        long[][] dp = new long[listMap.get(1).size()][totalMatches];
        for (int i = 0; i < totalMatches; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < listMap.get(1).size(); i++) {
            int indexOfStartPointOneDepth = allOccurencesStartIndices.indexOf(listMap.get(1).get(i) - substringLength + 1);
            for (int indexOfCurrentStartPoint = indexOfStartPointOneDepth; indexOfCurrentStartPoint < allOccurencesStartIndices.size(); indexOfCurrentStartPoint++) {
                int currentStartPoint = allOccurencesStartIndices.get(indexOfCurrentStartPoint);


                for (int depths = 1; depths <= maxDepth; depths++) {
                    List<Integer> currentDepthListOfEndIndices = listMap.get(depths);
                    int nearestEndIndex = Collections.binarySearch(currentDepthListOfEndIndices, currentStartPoint - 1);
                    if (nearestEndIndex < 0) {
                        // Insertion Point
                        if (nearestEndIndex == -1)
                            continue; // not present! i.e. index < 0
                        nearestEndIndex = Math.abs(nearestEndIndex);
                        nearestEndIndex -= 2;
                    }
                    int startIndexOfConnectionToAdd = currentDepthListOfEndIndices.get(nearestEndIndex) - substringLength + 1;
                    int a = i;
                    int b = indexMap.get(currentStartPoint);
                    int c = i - 1;
                    int d = indexMap.get(startIndexOfConnectionToAdd);
                    dp[a][b] += dp[c][d];
                }
            }
        }
        System.out.println("Ans");
        new PrintMat(dp);
    }

    private void prepareIndexMap() {
        indexMap = new HashMap<>();
        int index = 0;
        for (int i : allOccurencesStartIndices) {
            indexMap.put(i, index);
            index++;
        }
        System.out.println("Prepared Index Map");
        System.out.println(indexMap);
    }


    public void prepareListMap(String string, String substring) {
        this.substringLength = substring.length();
        listMap = new HashMap<>();
        allOccurencesStartIndices = new ArrayList<>();
        int zeroDepthIndex = -1;
        for (int i = 0, currentDepth = -1; i < string.length(); i++) {
            int currentStartIndex = string.indexOf(substring, i);
            if (currentStartIndex == -1) break;
            int depth = getDepth(zeroDepthIndex, currentStartIndex, currentDepth);
            currentDepth = depth;
            if (depth == 1) {
                zeroDepthIndex = currentStartIndex;
                insertIntoMap(1, currentStartIndex + substringLength - 1);
                currentDepth = 1;
                allOccurencesStartIndices.add(currentStartIndex);
            } else {

                insertIntoMap(depth, currentStartIndex + substringLength - 1);
                allOccurencesStartIndices.add(currentStartIndex);
            }
            if (currentDepth > maxDepth) maxDepth = currentDepth;
            totalMatches++;
            i = currentStartIndex;
        }
        System.out.println("Prepared List Map :");
        System.out.println(listMap);
    }

    private void insertIntoMap(int depth, int endIndex) {
        if (listMap.containsKey(depth)) {
            listMap.get(depth).add(endIndex);
        } else {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(endIndex);
            listMap.put(depth, tmp);
        }
    }
}