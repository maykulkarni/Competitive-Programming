package Hitlist;

import java.util.Scanner;

/**
 * Created by mayur on 21/7/16.
 */
public class SubstringDiff {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int k = in.nextInt();
            String fir = in.next();
            String sec = in.next();
            new SubstringDiffSolver().solve(k, fir, sec);
        }
    }
}

class SubstringDiffSolver {

    public void solve(int k, String first, String sec) {
        int[][] diff = new int[first.length() + 1][sec.length() + 1];
        int[][] len = new int[first.length() + 1][sec.length() + 1];
        int maxLen = -1;
//        for (int i = 1; i < first.length() + 1; i++) {
//            for (int j = 1; j < sec.length() + 1; j++) {
//                if (first.charAt(i - 1) == sec.charAt(j - 1) && diff[i - 1][j - 1] <= k) {
//                    len[i][j] = len[i - 1][j - 1] + 1;
//                    diff[i][j] = diff[i - 1][j - 1];
//                } else if (diff[i - 1][j - 1] <= k){
//                    if (diff[i - 1][j - 1] + 1 <= k) {
//                        len[i][j] = len[i - 1][j - 1] + 1;
//                        diff[i][j] = diff[i - 1][j - 1] + 1;
//                    } else {
//                        if (first.charAt(i - len[i - 1][j - 1] - 1) != sec.charAt(j - len[i - 1][j - 1] - 1)) {
//                            len[i][j] = len[i - 1][j - 1];
//                            diff[i][j] = diff[i - 1][j - 1];
//                        } else {
//                            len[i][j] = len[i-1][j-1];
//                            diff[i][j] = diff[i-1][j-1] + 1;
//                        }
//                    }
//                }
//            }
//        }
        for (int i = 1; i < first.length() + 1; i++) {
            for (int j = 1; j < sec.length() + 1; j++) {
                if (diff[i - 1][j - 1] <= k) {
                    // less than k , can do increment or swap
                    // for diff less than k -> increment
                    // |    |   equal to k -> swap
                    if (diff[i - 1][j - 1] < k || first.charAt(i - 1) == sec.charAt(j - 1)) {
                        if (first.charAt(i - 1) == sec.charAt(j - 1)) {
                            len[i][j] = len[i - 1][j - 1] + 1;
                            diff[i][j] = diff[i - 1][j - 1];
                        } else {
                            len[i][j] = len[i - 1][j - 1] + 1;
                            diff[i][j] = diff[i - 1][j - 1] + 1;
                        }
                    } else {
                        // swap
                        if (first.charAt(i - len[i - 1][j - 1] - 1) != sec.charAt(j - len[i - 1][j - 1] - 1)) {
                            len[i][j] = len[i - 1][j - 1];
                            diff[i][j] = diff[i - 1][j - 1];
                        } else {
                            // can't swap, increment k (diff) let length remain same
                            len[i][j] = len[i - 1][j - 1];
                            diff[i][j] = diff[i - 1][j - 1] + 1;
                        }
                    }
                } else {
                    // more than k , can only swap and check
                    // for global optimum and also increment the
                    // k
                    int currLen = len[i - 1][j - 1] - 1; // -1 for fixing pointer
                    // case same diff <- worst case, increment k
                    if (first.charAt(i - currLen) == sec.charAt(j - currLen) && first.charAt(i - 1) != sec.charAt(j - 1)) {
                        diff[i][j] = diff[i - 1][j - 1] + 1;
                        len[i][j] = len[i - 1][j - 1];
                    } else
                        // case diff same <- best case, decrement k
                        if (first.charAt(i - currLen) != sec.charAt(j - currLen) && first.charAt(i - 1) == sec.charAt(j - 1)) {
                            diff[i][j] = diff[i - 1][j - 1] - 1;
                            len[i][j] = len[i - 1][j - 1];
                        } else
                            // case same same or diff diff <- no increment/decrement in k, just increment pointer
                            if (first.charAt(i - currLen) != sec.charAt(j - currLen) && first.charAt(i - 1) != sec.charAt(j - 1) ||
                                    first.charAt(i - currLen) == sec.charAt(j - currLen) && first.charAt(i - 1) == sec.charAt(j - 1)) {
                                diff[i][j] = diff[i - 1][j - 1];
                                len[i][j] = len[i - 1][j - 1];
                            }
                }
            }
        }
        for (int i = 0; i < first.length() + 1; i++) {
            for (int j = 0; j < sec.length() + 1; j++) {
                //  if (len[i][j] == 256) System.out.println("lol" + diff[i][j]);
                if (diff[i][j] <= k)
                    maxLen = Math.max(maxLen, len[i][j]);
            }
        }
        //System.out.println("maxk : " + maxK + " maxlen : " + maxLen);
//        new PrintMat(diff);
//        System.out.println("**");
//        new PrintMat(len);
        System.out.println(maxLen);
    }
}