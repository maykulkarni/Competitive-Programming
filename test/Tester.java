package test;

import DataStructures.SegmentTree;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mayur Kulkarni on 11/21/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class Tester {
    public static long solve(int[] a, int size, int k) {
        long ans = 0;
        SegmentTree st = new SegmentTree(a);
//        int p = -1;
//        int q;
//        while (p < a.length) {
//            p++;
//            q = p + 1;
//            while (q <= a.length) {
//                if (st.query(p, q) == 0) {
//                    ans += size - q + 1;
//                    p++;
//                    q = p + 1;
//                } else {
//                    q++;
//                }
//            }
//        }
        for (int p = 0; p < a.length; p++) {
            for (int q = p + 1; q <= a.length; q++) {
                if (st.query(p, q) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static long bruteForce(int[] a, int size, int k) {
        long ans = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j <= size - i; j++) {
                int tempj = j;
                long temp = 1;
                for (int l = 0; l < i; l++) {
                    temp = (temp * a[tempj++]) % k;
                }
                if (temp % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            Random rand = new Random();
            int size = 10;
            int[] arr = new int[500];
            for (int j = 0; j < 500; j++) {
                arr[j] = rand.nextInt(109000) + 1;
            }
            int k = rand.nextInt(500) + 1;
            long op = solve(arr, arr.length, k);
            long bf = bruteForce(arr, arr.length, k);
            if (op != bf) {
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println("Expected : " + bf + " got : " + op);
            }
        }
//        System.out.println(bruteForce(new int[] {6, 4, 9, 3, 2}, 5, 4));
    }
}