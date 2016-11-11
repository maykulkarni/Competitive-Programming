package CS97SI;

import Utils.BladeReader;

/**
 * Created by kulkarni_my on 11/11/2016.
 */
public class Yeeha {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            new YeehaSolver().solve(in.nextDouble(), in.nextInt(), i + 1);
        }
    }
}

class YeehaSolver {
    public void solve(double R, int N, int i) {
        System.out.printf("Scenario #%d:\n%.3f\n\n", i, (R * Math.sin(Math.PI / N)) / (1.0 + Math.sin(Math.PI / N)));
    }
}
