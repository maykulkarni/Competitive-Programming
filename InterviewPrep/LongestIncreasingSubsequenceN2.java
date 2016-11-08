import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 18/7/16.
 */
public class LongestIncreasingSubsequenceN2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i) arr[i] = in.nextInt();
        new LongestIncreasingSubsequenceN2Solver().solve(arr);
    }
}

class LongestIncreasingSubsequenceN2Solver {

    public void solve(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int right = 1; right < arr.length; ++right) {
            for (int left = 0; left < right; ++left) {
                if (arr[right] > arr[left]) {
                    dp[right] = dp[right] < dp[left] + 1 ? dp[left] + 1 : dp[right];
                }
            }
        }
        System.out.println(Arrays.stream(dp).max());
    }
}