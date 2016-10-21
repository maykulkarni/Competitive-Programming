package Hitlist;

import java.util.Arrays;

/**
 * Created by mayur on 6/7/16.
 */
public class NonIncreasingSums {
    public static void main(String[] ar) {
        int[] a;
        int x = 5;
        new Solver().solve(x);
    }
}

class Solver {

    public void solve(int x) {
        int[] a = new int[x];
        Arrays.fill(a, 1);
        int incrIndex, decrIndex;
        incrIndex = 0;
        decrIndex = x - 1;
        int index = x - 1;
        while (a[0] != x) {
            if (incrIndex >= decrIndex) {
                incrIndex = 0;
                decrIndex = index;
            }
            print(a, decrIndex);
            //System.out.println(incrIndex + " " + decrIndex + "\n");
            a[incrIndex]++;
            incrIndex++;
            a[decrIndex]--;
            if (a[decrIndex] == 0) {
                index--;
                decrIndex--;
            }
        }
        System.out.println(x);
    }

    private void print(int[] a, int index) {
        for (int i = 0; i < a.length && a[i] != 0; i++) System.out.print(a[i] + " ");
        System.out.println();
    }
}
