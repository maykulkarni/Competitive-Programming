package Hitlist;

import MyImplementations.PrintMat;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mayur on 26/7/16.
 */
public class SquareSubsequence {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int casea = in.nextInt();
//        for (int i = 0; i < casea; i++) {
//            String ip = in.next();
//            System.out.println(new SquareSubsequenceSolver().solve(ip));
//        }
        System.out.println(new SquareSubsequenceSolver().solve("abab"));
    }
}

class SquareSubsequenceSolver {

    public Set<Integer> alreadyVisited = new HashSet<Integer>();

    public int hash(int x, int y) {
        return x * 7 + 12 * y - 3;
    }

    public long commonSubsequence(String input) {
        long[][][] dp = new long[input.length() - 1][input.length()][input.length()];
        long answer = 0;
        int j = 0, k = 0;
        int limitOne = 1;
        int limitTwo = input.length() - 1;
        for (int i = 0; i < input.length() - 1; i++, limitOne++, limitTwo--) {
            for (j = 0; j < limitOne; j++) {
                for (k = 0; k < limitTwo; k++) {
                    System.out.println("comparing : " + input.charAt(j) + " with " + input.charAt(k + limitOne));
                    if (input.charAt(j) == input.charAt(k + limitOne)) {
                        if (i > 0) {
                            // last column
                            if (j == limitOne - 1) {
                                if (dp[i - 1][j - 1][k] > 0 && k > 0) {
                                    dp[i][j][k] = dp[i - 1][j - 1][k] + 1;
                                    answer += dp[i - 1][j - 1][k] + 1;
                                } else {
                                    dp[i][j][k] = 1;
                                    answer++;
                                }
                            } else {
                                if (dp[i - 1][j][k + 1] > 0) {
                                    dp[i][j][k] = 1;
                                } else {
                                    dp[i][j][k] = 1;
                                    answer++;
                                }
                            }
                        } else {
                            dp[i][j][k] = 1;
                            answer++;
                        }
                    }
                }
            }
            new PrintMat(dp[i]);
        }
        return answer;
    }

    public long solve(String ip) {
        long ans = 0;
        return commonSubsequence(ip);

    }
}