package InterviewPrep;

/**
 * Created by mayur on 22/10/16.
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        new KMP().solve();
    }
}

class KMP {
    public int[] KMPArray;

    public void createKMPArray(char[] pattern) {
        KMPArray = new int[pattern.length];
        int i = 1, j = 0;
        while (i != pattern.length && j >= 0) {
            if (pattern[i] == pattern[j]) {
                i++;
                j++;
                KMPArray[i - 1] = j;
            } else {
                if (j > 0) {
                    j = KMPArray[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public void compareString(char[] stringToCompare, char[] pattern) {
        createKMPArray(pattern);
        int stringToComparePointer = 0;
        int patternPointer = 0;
        boolean isPatternFound = false;
        while (!isPatternFound || stringToComparePointer == stringToCompare.length) {
            if (stringToCompare[stringToComparePointer] != pattern[patternPointer]) stringToComparePointer++;
            while (pattern[patternPointer] == stringToCompare[stringToComparePointer]) {
                if (patternPointer == pattern.length - 1) {
                    isPatternFound = true;
                    break;
                }
                patternPointer++;
                stringToComparePointer++;
            }
            patternPointer = KMPArray[patternPointer - 1];
        }
        if (isPatternFound)
            System.out.println("Found : " + (stringToComparePointer - pattern.length + 1));
        else
            System.out.println("Not found");
    }

    public void solve() {
        compareString("abcbcglx".toCharArray(), "mm".toCharArray());
    }
}