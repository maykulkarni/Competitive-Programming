package CPPrograms;

import MyImplementations.InputReader;

import java.util.Arrays;

/**
 * Created by mayur on 10/7/16.
 */
public class MandragoraForest {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            int number = in.nextInt();
            int[] h = new int[number];
            long sum = 0;
            for (int j = 0; j < number; j++) {
                h[j] = in.nextInt();
                sum += h[j];
            }
            System.out.println(new MandragoraForestSolver().solve(h, sum));
        }
    }
}

class MandragoraForestSolver {

    public long solve(int[] h, long sum) {
        if (h.length == 1) return h[0];
        Arrays.parallelSort(h);
        long ans = -1;
        for (long i = 0, strength = 2; i < h.length; i++, strength++) {
            sum -= h[(int) i];
            ans = Math.max(ans, strength * sum);
        }
        return ans;
    }
}
