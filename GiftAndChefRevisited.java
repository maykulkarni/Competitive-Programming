import Utils.BladeReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    int substringLength;
    Map<Integer, List<Integer>> listMap;

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
        this.substringLength = substring.length();

        listMap = new HashMap<>();

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
            } else {
                insertIntoMap(depth, currentStartIndex + substringLength - 1);
            }
            i = currentStartIndex;
        }
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