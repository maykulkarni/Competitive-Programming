package CS97SI;

import Utils.BitManipulation;
import Utils.BladeReader;

/**
 * Created by Mayur Kulkarni on 11/14/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class BST {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            new BSTSolver().solve(in.nextInt());
        }
    }
}

class BSTSolver {
    public void solve(int inp) {
        int lowestSetBit = BitManipulation.getLowestSetBitIndex(inp);
        int low = inp;
        int high = inp;
        for (int i = lowestSetBit - 1; i >= 0; i--) {
            low -= Math.pow(2, i);
            high += Math.pow(2, i);
        }
        System.out.println(low + " " + high);
    }
}
