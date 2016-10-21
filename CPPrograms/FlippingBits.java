package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 15/7/16.
 */
public class FlippingBits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            new FlippingBitsSolver().solve(in.nextLong());
        }
    }
}

class FlippingBitsSolver {

    public void solve(long i) {
        long maxInt = (long) Math.pow(2, 32) - 1;
        System.out.println(maxInt - i);
    }
}
