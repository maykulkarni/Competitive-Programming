import Utils.BladeReader;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

class Pipe implements Comparator<Pipe> {
    static int substringLength;
    int startIndex;
    int endIndex;
    int level;

    Pipe(int startIndex, int level) {
        this.startIndex = startIndex;
        this.level = level;
    }

    @Override
    public int compare(Pipe one, Pipe other) {
        return one.startIndex - other.startIndex;
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
        return "StrtIndex : " + startIndex + " level : " + level;
    }
}

class GiftAndChefSolver {
    public void solve(String string, String substring) {
        Set<Pipe> occurences = new TreeSet<>();
        boolean isDepthZero = false;
        int zeroDepthIndex = -1;
        for (int i = 0, currentDepth = -1; i < string.length() - substring.length(); i++) {
            int currentStartIndex = string.indexOf(substring, i);
            if (currentStartIndex < zeroDepthIndex + substring.length()) {
                currentDepth++;
            }
            if (currentDepth == 0) {
                zeroDepthIndex = currentStartIndex;
            }
            occurences.add(new Pipe(currentStartIndex, currentDepth));
        }
        System.out.println(occurences);
    }
}