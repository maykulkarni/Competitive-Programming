package Algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mayur Kulkarni on 12/8/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class RMQ {
    int[] arr;
    int[][] M;
    int N;

    public RMQ(int[] inputArr) {
        arr = inputArr;
        M = new int[inputArr.length][(int) (Math.log10(inputArr.length) / Math.log10(2) + 1)];
        N = inputArr.length;
        processUsingSparseTable();
    }

    public static int BF(int[] arr, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, arr[k]);
        }
        return min;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int zz = 1;
        for (int i = 0; i < 1000; i++) {
            int sz = r.nextInt((int) 1e6) + 1;
            int[] arr = new int[sz];
            for (int j = 0; j < sz; j++) {
                arr[j] = r.nextInt((int) 1e6);
            }
            RMQ rmq = new RMQ(arr);
            int L = r.nextInt(sz);
            int R = r.nextInt(sz - L);
            R += L;
            if (R < L) continue;
            int ans1 = rmq.minUsingSparseTable(L, R);
            int ans2 = BF(arr, L, R);

            if (ans1 != ans2) {
                System.out.println("Test Failed ");
                System.out.println("Expected : " + ans2 + " Got: " + ans1);
                System.out.println("Range : " + L + " " + R);
                System.out.println(Arrays.toString(arr));
                break;
            } else {
                zz++;
                System.out.print("\rPassed: " + zz + "/1000");
            }
        }
    }

    private void processUsingSparseTable() {
        for (int i = 0; i < N; i++) {
            M[i][0] = i;
        }
        int i, j;
        for (j = 1; 1 << j <= N; j++)
            for (i = 0; i + (1 << j) - 1 < N; i++) {
                if (arr[M[i][j - 1]] < arr[M[i + (1 << (j - 1))][j - 1]])
                    M[i][j] = M[i][j - 1];
                else
                    M[i][j] = M[i + (1 << (j - 1))][j - 1];
            }
    }

    public int minUsingSparseTable(int i, int j) {
        if (!(i >= 0 && j < N)) {
            System.err.println("Invalid query range for RMQ");
            return -1;
        }
        int k = (int) Math.floor(Math.log10(j - i + 1) / Math.log10(2));
        int z = j - (int) Math.pow(2, k) + 1;
        return Math.min(arr[M[i][k]], arr[M[z][k]]);
    }
}

