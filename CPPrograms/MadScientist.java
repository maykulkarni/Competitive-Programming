package CPPrograms;

/**
 * Created by mayur on 15/10/16.
 */
public class MadScientist {
    public static void main(String[] args) {
        System.out.println(Ans.kadane(new int[]{1, -1, 4, 2, -6, 7, 3, 4}, 1));
    }
}

class Ans {
    public static int findMax(int[][] mat, int k) {
        int max = -999999;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] > max && Math.floor(1 + Math.log10(mat[i][j])) <= k) {
                    max = mat[i][j];
                }
            }
        }
        return max;
    }

    public static void answer(int[] L, int k) {
        int[][] ans = new int[L.length][L.length];
        for (int i = 0, j = 0; i < L.length; i++, j++) {
            ans[i][j] = L[i];
        }
        for (int i = 0; i < L.length - 1; i++) {
            for (int j = i + 1; j < L.length; j++) {
                ans[i][j] = ans[i][j - 1] + L[j];
            }
        }
        //new PrintMat(ans);
        System.out.println(findMax(ans, k));
    }

    public static int kadane(int[] L, int k) {
        int maxSum = 0, end = 0;
        for (int i = 0; i < L.length; i++) {
            end = end + L[i];
            if (end < 0) end = 0;
            if (end > maxSum && Math.floor(1 + Math.log10(end)) <= k) maxSum = end;
        }
        return maxSum;
    }
}
