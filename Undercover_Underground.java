import java.math.BigInteger;
import java.util.*;

public class Undercover_Underground {

    static Map<List<Integer>, BigInteger> nCrMap = new HashMap<List<Integer>, BigInteger>();
    /**
     * Idea:
     * An edge is defined to be a connection between two nodes
     * for our purposes. For reference, we have N nodes and K
     * edges in our desired graph(s).
     * <p>
     * With N nodes, we can have {N}C{2} graphs (that many possible edges).
     * For our K, this set is reduced to { {N}C{2} }C{K}
     * <p>
     * We are only interested in the simply connected graphs. Lets call this
     * set of graphs C[N,K].
     * <p>
     * To transform a graph c in C[N,K], to a graph in C[N,K+1], we take
     * 2 unconnected nodes u,v: u,v ∈ c, and connect them.
     * <p>
     * To transform a graph c in C[N,K] to a graph in C[N+1,K], it is a
     * little more work. We take 2 nodes in c, that are either directly
     * or indirectly connected. Either way, in order to keep the number of edges
     * constant, there would have to 2 at least 2 paths connecting the 2 nodes.
     * We would get rid of one of these edge connections and add our new node
     * to either of the 2 nodes to create 2 separate graphs.
     * <p>
     * This is the general concept. To implement, I decided to start with our max
     * possible number of graphs and remove all graphs that have either unconnected
     * nodes, self loops, incomplete graphs and complex graphs (i think that is all)
     */

    static Map<List<Integer>, String> resultMap = new HashMap<List<Integer>, String>();
    private static Map<Integer, BigInteger> factorials = new HashMap<Integer, BigInteger>();

    // formula: nCr = n! / [r! * (n-r)!]
    private static BigInteger nChooseR(int n, int r) {
        // check if key is present
        List<Integer> tuple = Arrays.asList(n, r);
        if (nCrMap.containsKey(tuple))
            return nCrMap.get(tuple);
        // covering some basic cases using
        // if statements to prevent unnecessary
        // calculations and memory wastage
        // given 5 objects, there are 0 ways to choose 6
        if (r > n)
            return BigInteger.valueOf(0);
        // given 5 objects, there are 5 ways of choosing 1
        // given 5 objects, there are 5 ways of choosing 4
        if ((r == 1) || ((n - r) == 1))
            return BigInteger.valueOf(n);
        // given 5 objects, there is 1 way of choosing 5 objects
        // given 5 objects, there is 1 way of choosing 0 objects
        if ((r == 0) || ((n - r) == 0))
            return BigInteger.valueOf(1);
        BigInteger diff = getFactorial(n - r);
        BigInteger numerator = getFactorial(n);
        BigInteger denominator = getFactorial(r);
        denominator = denominator.multiply(diff);
        // unmodifiable so key cannot change hash code
        nCrMap.put(Collections.unmodifiableList(Arrays.asList(n, r)), numerator.divide(denominator));
        return nCrMap.get(tuple);
    }

    private static BigInteger getFactorial(int n) {
        if (factorials.containsKey(n))
            return factorials.get(n);
        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        factorials.put(n, fact);
        return fact;
    }

    public static void main(String[] args) {
        Undercover_Underground unit = new Undercover_Underground();
        for (int n = 1; n <= 20; n++) {
            for (int k = 1; k <= n * (n - 1) / 2; k++) {
                String ans = unit.answer(n, k);
                System.out.println("N = " + n + " , K = " + k + " , num = " + ans);
            }
        }
    }

    // loose reference: http://math.stackexchange.com/questions/689526/how-many-connected-graphs-over-v-vertices-and-e-edges
    public String answer(int N, int K) {

            /* for the case where K < N-1 */
        if (K < N - 1)
            return BigInteger.ZERO.toString();

            /* for the case where K = N-1 */
        // Cayley's formula applies [https://en.wikipedia.org/wiki/Cayley's_formula].
        // number of trees on n labeled vertices is n^{n-2}.
        if (K == N - 1) {
            if (N < 2)
                return BigInteger.valueOf((long) Math.pow(N, N - 2)).toString();
            // multiply N to itself N-2 times
            BigInteger val = BigInteger.ONE;
            int count = 0;
            while (count++ != N - 2)
                val = val.multiply(BigInteger.valueOf((long) N));
            return val.toString();
        }

            /* for the case where K > N-1 */
        // check if key is present in the map
        List<Integer> tuple = Arrays.asList(N, K);
        if (resultMap.containsKey(tuple))
            return resultMap.get(tuple);
        // maximum number of edges in a simply
        // connected undirected unweighted graph
        // with n nodes = |N| * |N-1| / 2
        int maxEdges = N * (N - 1) / 2;

            /* for the case where K = N(N-1)/2 */
        // if K is the maximum possible
        // number of edges for the number of
        // nodes, then there is only one way is
        // to make a graph (connect each node
        // to all other nodes)
        if (K == maxEdges)
            return BigInteger.ONE.toString();

            /* for the case where K > N(N-1)/2 */
        if (K > maxEdges)
            return BigInteger.ZERO.toString();
        // get the universal set
        BigInteger allPossible = nChooseR(maxEdges, K);
        BigInteger repeats = BigInteger.ZERO;
        // now, to remove duplicates, or incomplete graphs
        // when can these cases occur? [note: 2 edges kept in hand]
        for (int n = 0; n <= N - 2; n++) {
            BigInteger choose_n_from_rem_nodes = nChooseR(N - 1, n);
            // how many ways are there to keep 2 edges in hand?
            // this is essentially choosing N-3-n nodes from N-1-n
            // which is (N-1-n) choose (N-3-n)
            // = {N-1-n}! / { {N-3-n}! {(N-1-n)-(N-3-n)}!
            // = {N-1-n}{N-2-n}{N-3-n}! / { {N-3-n}! * 2}!
            // = {N-1-n}{N-2-n} / 2
            int chooseN = (N - 1 - n) * (N - 2 - n) / 2;
            BigInteger repeatedEdges = BigInteger.ZERO;
            for (int k = 0; k <= K; k++) {
                BigInteger combinations = nChooseR(chooseN, k);
                BigInteger recurse = new BigInteger(answer(n + 1, K - k));
                repeatedEdges = repeatedEdges.add(combinations.multiply(recurse));
            }
            repeats = repeats.add(choose_n_from_rem_nodes.multiply(repeatedEdges));
        }
        // remove repeats
        allPossible = allPossible.subtract(repeats);
        // add to cache
        resultMap.put(Collections.unmodifiableList(Arrays.asList(N, K)), allPossible.toString());
        return resultMap.get(tuple);
    }
}