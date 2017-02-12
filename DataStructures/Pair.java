package DataStructures;

/**
 * Created by Uzumaki Naruto on 2/9/2017.
 * Unordered Pair implementation
 */

public class Pair<P, Q> {
    public P p;
    public Q q;


    public Pair(P p, Q q) {
        this.p = p;
        this.q = q;
    }

    public String toString() {
        return "< " + p + ", " + q + " >";
    }

    public int firstValue() {
        if (p instanceof Number)
            return ((Number) p).intValue();
        else
            throw new RuntimeException("First Value is not a number, can't be compared");
    }

    public int secondValue() {
        if (p instanceof Number)
            return ((Number) q).intValue();
        else
            throw new RuntimeException("Second value is not a number, can't be compared");
    }
}
