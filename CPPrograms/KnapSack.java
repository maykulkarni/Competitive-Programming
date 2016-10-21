package CPPrograms;

import MyImplementations.InputReader;

/**
 * Created by mayur on 13/7/16.
 */
public class KnapSack {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            int len = in.nextInt();
            int sum = in.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = in.nextInt();
            new KnapSackSolver().solve(arr, sum);
        }
    }
}

class KnapSackSolver {

    public void solve(int[] arr, int sum) {
        boolean[][] maxSum = new boolean[arr.length][sum + 1];
        for (int i = 0; i <= sum; i++) {
            if (i % arr[0] == 0) maxSum[0][i] = true;
        }
        for (int i = 1; i < maxSum.length; i++) {
            for (int j = 0; j < maxSum[0].length; j++) {
                if (j % arr[i] == 0) {
                    maxSum[i][j] = true;
                } else {
                    maxSum[i][j] = maxSum[i - 1][j] || (j - arr[i] >= 0 && maxSum[i - 1][j - arr[i]]) || (j - arr[i] >= 0 && maxSum[i][j - arr[i]]);
                }
            }
        }
        maxSum[arr.length - 1][0] = true;
        // new PrintMat(maxSum);
        for (int i = sum; i >= 0; i--) {
            if (maxSum[arr.length - 1][i]) {
                System.out.println(i);
                break;
            }
        }
    }
}