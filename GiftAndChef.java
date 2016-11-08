import Utils.BladeReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mayur on 8/11/16.
 */
public class GiftAndChef {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            new GiftAndChefSolver().solve(in.nextString(), in.nextString());
        }
    }
}

class Pipe implements Comparable<Pipe> {
    static int substringLength;
    int startIndex;
    int endIndex;
    int level;

    Pipe(int startIndex, int level) {
        this.startIndex = startIndex;
        this.level = level;
    }

    @Override
    public int compareTo(Pipe other) {
        return this.startIndex - other.startIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pipe pipe = (Pipe) o;
        if (startIndex != pipe.startIndex) return false;
        return level == pipe.level;
    }

    @Override
    public int hashCode() {
        int result = startIndex;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "StrtIndex : " + startIndex + " level : " + level + "\n";
    }
}

class GiftAndChefSolver {
    private int substringLength = -1;
    private List<Pipe> occurences;
    private List<Integer> levelOnePipes;
    private Map<Integer, Integer> index;

    private void generatePipes(String string, String substring) {
        occurences = new ArrayList<>();
        levelOnePipes = new ArrayList<>();
        int zeroDepthIndex = -1;
        for (int i = 0, currentDepth = -1; i < string.length(); i++) {
            int currentStartIndex = string.indexOf(substring, i);
            if (currentStartIndex == -1) break;
            int depth = getDepth(zeroDepthIndex, currentStartIndex, currentDepth);
            currentDepth = depth;
            if (depth == 1) {
                zeroDepthIndex = currentStartIndex;
                levelOnePipes.add(currentStartIndex);
                currentDepth = 1;
            }
            occurences.add(new Pipe(currentStartIndex, depth));
            i = currentStartIndex;
        }
    }

    public void solve(String string, String substring) {
        substringLength = substring.length();
        generatePipes(string, substring);
        if (occurences.size() == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(occurences);
        System.out.println(levelOnePipes);
        long[][] dp = new long[levelOnePipes.size()][occurences.size() + 1];
        fillIndicesInMap(occurences);
        for (int i = 1; i <= levelOnePipes.size(); i++) {
            // 1 = 2
            // 2 = 3 etc..
            dp[index.get(levelOnePipes.get(i))][i - 1] = 1;
            for (int steps = 0; steps < occurences.size() - i; steps++) {

            }
        }
        System.out.println(index);
    }

    private void fillIndicesInMap(List<Pipe> occurences) {
        index = new HashMap<>();
        int i = 0;
        for (Pipe p : occurences) {
            index.put(p.startIndex, i);
            i++;
        }
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

    private boolean inRange(int zeroDepthIndex, int currentStartIndex) {
        return currentStartIndex <= zeroDepthIndex + substringLength - 1;
    }
}