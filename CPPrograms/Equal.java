package CPPrograms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 13/7/16.
 */
public class Equal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            int intern = in.nextInt();
            int[] arr = new int[intern];
            for (int j = 0; j < intern; j++) arr[j] = in.nextInt();
            new EqualSolver().solve(arr);
        }
    }
}

class EqualSolver {

    public void solve(int[] arr) {
        int min = Arrays.stream(arr)
                .min()
                .getAsInt();
        int ops = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            //System.out.println(solveFor( Math.max(0, min-i), arr));
            ops = Math.min(ops, solveFor(min - i, arr.clone()));
        }
        System.out.println(ops);
    }

    private int solveFor(int min, int[] arr) {
        int ops = 0;
        System.out.print(min + " \\ ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= min;
            System.out.print(arr[i] + " ");
            //if (arr[i] < 0) return Integer.MAX_VALUE;
            ops += minops(arr[i]);
        }
        System.out.println(" \\ " + ops);
        return ops;
    }

    private int minops(int i) {
        return i / 5 + (i % 5) / 2 + (i % 5) % 2;
    }
}
