import Utils.BladeReader;

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

class GiftAndChefSolver {
    public void solve(String string, String substring) {
    }
}