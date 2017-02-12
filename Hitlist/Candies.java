package Hitlist;

import Algorithms.InputReader;

import java.util.Arrays;

/**
 * Created by mayur on 10/7/16.
 */
public class Candies {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int len = in.nextInt();
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) arr[i] = in.nextLong();
        new CandiesSolver().solve(arr);
    }
}

class CandiesSolver {
    public void solve(long[] arr) {
        long[] ans = new long[arr.length];
        ans[0] = 1;
        for (int i = 1; i < ans.length; i++) {
            if (arr[i] > arr[i - 1] && ans[i] <= arr[i - 1]) {
                ans[i] = ans[i - 1] + 1;
            } else {
                ans[i] = 1;
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && ans[i] <= arr[i + 1]) {
                ans[i] = ans[i + 1] + 1;
                // For some reason, when I dont use else if, it doesnt work
                // Figure out what's missing
            } else if (arr[i] < arr[i + 1] && ans[i + 1] <= ans[i]) {
                ans[i + 1] = ans[i] + 1;
            }
        }
        //System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.stream(ans).sum());
    }
}