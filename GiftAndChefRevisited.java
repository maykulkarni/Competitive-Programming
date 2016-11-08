import Utils.BladeReader;

import java.util.*;

/**
 * Created by kulkarni_my on 11/8/2016.
 */
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
    private int totalMatches = -1;
    private List<Integer> allOccurencesStartIndices;
    private int maxDepth = -1;
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
        prepareListMap(string, substring);
        long[][] dp = new long[listMap.get(1).size()][totalMatches];
        for (int i = 1; i <= listMap.get(1).size(); i++) {
            int startIndex = allOccurencesStartIndices.indexOf(listMap.get(1).get(i) - substringLength + 1);
            for (int currStartIndex = startIndex; currStartIndex < allOccurencesStartIndices.size(); currStartIndex++) {
                int currentStartPoint = allOccurencesStartIndices.get(currStartIndex);
                for (int depths = 1; depths <= maxDepth; depths++) {
                    List<Integer> currentDepthList = listMap.get(depths);
                    int nearestEndIndex = Collections.binarySearch(currentDepthList, currentStartPoint - 1);
                    if (nearestEndIndex < 0) {
                        // Insertion Point
                        nearestEndIndex = Math.abs(nearestEndIndex);
                        nearestEndIndex--;
                        dp[startIndex][depths] += dp[nearestEndIndex - substringLength + 1][depths - 1];
                    } else {
                        dp[startIndex][depths] += dp[nearestEndIndex - substringLength + 1][depths - 1];
                        // Found it
                    }
                }
            }
        }
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
                if (currentDepth > maxDepth) maxDepth = currentDepth;
                insertIntoMap(depth, currentStartIndex + substringLength - 1);
                allOccurencesStartIndices.add(currentStartIndex);
            }
            totalMatches++;
            i = currentStartIndex;
        }
        //System.out.println(listMap);
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