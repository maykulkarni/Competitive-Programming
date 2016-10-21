package CPPrograms;

import MyImplementations.InputReader;

/**
 * Created by mayur on 9/7/16.
 */
public class Kadane {
    public static void main(String[] arg) {
        InputReader in = new InputReader(System.in);
        int cases = in.nextInt();
        for (int test = 0; test < cases; test++) {
            int len = in.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) arr[i] = in.nextInt();
            new KadaneSolver().solve(arr);
        }
    }
}

class KadaneSolver {
    public void solve(int[] arr) {
        int maxEndingHere, maxSoFar;
        maxEndingHere = maxSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], arr[i] + maxEndingHere);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        System.out.print(maxSoFar + " ");
        if (maxSoFar <= 0) System.out.println(maxSoFar);
        else {
            int sum = 0;
            for (int a : arr) {
                if (a > 0) sum += a;
            }
            System.out.println(sum);
        }
    }
}
