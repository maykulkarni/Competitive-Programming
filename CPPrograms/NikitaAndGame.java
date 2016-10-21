package aCurrent;

import MyImplementations.InputReader;

import java.util.Arrays;

/**
 * Created by mayur on 10/7/16.
 */
public class NikitaAndGame {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        for (int testCases = in.nextInt(); testCases > 0; testCases--) {
            int size = in.nextInt();
            int[] ipArray = new int[size];
            for (int i = 0; i < size; i++) ipArray[i] = in.nextInt();
            new NikitaAndGameSolver().solve(ipArray);
        }
    }
}

class NikitaAndGameSolver {

    public void solve(int[] ipArray) {
        if (Arrays.stream(ipArray).sum() == 0) System.out.println((int) Math.ceil(ipArray.length - 1));
        else System.out.println(countMax(ipArray));
    }

    private int getEqIndex(int[] ipArray) {
        int sum = Arrays.stream(ipArray)
                .sum();
        int leftSum = 0;
        for (int i = 0; i < ipArray.length; i++) {
            leftSum += ipArray[i];
            sum -= ipArray[i];
            if (leftSum == sum)
                return i + 1;
        }
        return -1;
    }

    private int countMax(int[] ipArray) {
        int partition = getEqIndex(ipArray);
        if (partition == -1) {
            return 0;
        } else {
            return 1 + Math.max(countMax(Arrays.copyOfRange(ipArray, 0, partition)), countMax(Arrays.copyOfRange(ipArray, partition, ipArray.length)));
        }
    }
}