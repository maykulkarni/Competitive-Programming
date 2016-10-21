import java.util.HashSet;
import java.util.Set;

public class Spy_Snippets {

    public static String answer(String document, String[] searchTerms) {
        // Your code goes here.
        // document words array
        String[] words = document.split(" ");
        // using HashSet since comparison will be the fastest
        Set<String> searchWords = new HashSet<String>();
        for (String word : searchTerms)
            searchWords.add(word);
        int numSearchWords = searchTerms.length;
        int startIndx = -1, curIndx = 0;
        // global comparison metrics
        int minSequenceSize = Integer.MAX_VALUE, minSequenceStart = 0;
        // keep track of words found, HashSet since ordering not required
        // using set since searchTerms can have a frequency > 1 in a sequence
        // don't want that to affect result
        Set<String> foundWords = new HashSet<String>();
        while (curIndx != words.length) {
            // current word
            String word = words[curIndx];
            // only concerned if this is a searchTerm
            if (searchWords.contains(word)) {
                // no words found
                // => start of sequence
                if (foundWords.size() == 0)
                    startIndx = curIndx;
                // add word
                foundWords.add(word);
                if (foundWords.size() == numSearchWords) {
                    // do comparisons
                    int curSequenceSize = curIndx - startIndx + 1;
                    if (curSequenceSize < minSequenceSize) {
                        // update global comparison metrics
                        minSequenceStart = startIndx;
                        minSequenceSize = curSequenceSize;
                    }
                    // reset curIndx to make sequence search start
                    // from the next index
                    curIndx = startIndx;
                    // clear foundWords
                    // old Set will be deleted by garbage collector
                    // new Set being initialized since old size might
                    // have been unnecessarily big
                    foundWords = new HashSet<String>();
                }
            }
            curIndx++;
        }
        StringBuilder sequence = new StringBuilder();
        // adding first word since remaining words will need spaces
        sequence.append(words[minSequenceStart]);
        for (int i = minSequenceStart + 1; i < minSequenceStart + minSequenceSize; i++)
            sequence.append(" " + words[i]);
        return sequence.toString();
    }

    public static void main(String[] args) {
        //        String doc = "many google employees can program";
        //        String[] search = {"google", "program"};
        //        String doc = "world there hello hello where world";
        //        String[] search = {"hello", "world"};
        //        String doc = "a b c d a";
        //        String[] search = {"a", "c", "d"};
        String doc = "i f a e f j z c k f q l p u f ii am x as die e";
        String[] search = {"a", "z", "x"};
        //        String doc = "a b d a c a c c d a";
        //        String[] search = {"a", "c", "d"};
        System.out.println(answer(doc, search));
    }
}
// mine
// a e f j z c k f q l p u f ii am x
// a e f j z c k f q l p u f ii am x