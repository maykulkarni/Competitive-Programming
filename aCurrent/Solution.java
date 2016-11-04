package aCurrent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 2/11/16.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        long[] ip = new long[size];
        for (int i = 0; i < size; i++) {
            ip[i] = in.nextLong();
        }
        new SolutionSolver().solve(ip);
    }
}

class SolutionSolver {
    public void solve(long[] ip) {
        long[] other = ip.clone();
        Arrays.sort(ip);
        int diff = 0;
        for (int i = 0; i < ip.length; i++) {
            if (ip[i] != other[i]) {
                diff++;
            }
        }
        System.out.print(Arrays.toString(ip));
    }
}