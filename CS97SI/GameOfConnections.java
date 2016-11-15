package CS97SI;

import Utils.BladeReader;
import Utils.NumberUtils;

/**
 * Created by Mayur Kulkarni on 11/15/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class GameOfConnections {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int inp;
        while ((inp = in.nextInt()) != -1) {
            GameOfConnectionsSolver.solve(inp);
        }
    }
}

class GameOfConnectionsSolver {
    public static void solve(int inp) {
        System.out.println(NumberUtils.catalan(inp));
    }
}
