package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 19/7/16.
 */
public class LongestIncreasingSubsequenceNLogN {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = in.nextInt();
        new LongestIncreasingSubsequenceNLogNSolver().solve(arr);
//        new LongestIncreasingSubsequenceNLogNSolver().prepareListMap(new int[] {-2, -7, -4, -3, -8});
    }
}

class LongestIncreasingSubsequenceNLogNSolver {

    public int ceilBinarySearch(int[] original, int[] indices, int low, int high, int key) {
        if (low == high) {
            return low;
        }
        int mid = (low + high) / 2;
        if (original[indices[mid]] > key) {
            return ceilBinarySearch(original, indices, low, mid, key);
        } else if (original[indices[mid]] == key) {
            return mid;
        } else {
            return ceilBinarySearch(original, indices, mid + 1, high, key);
        }
    }

    public void solve(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 0;
        int length = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > arr[dp[length]]) {
                dp[length + 1] = i;
                length++;
            } else if (arr[dp[0]] > arr[i]) {
                dp[0] = i;
            } else {
                // somewhere in between
                int index = ceilBinarySearch(arr, dp, 0, length, arr[i]);
                dp[index] = i;
            }
        }
        System.out.println(length + 1);
    }
}