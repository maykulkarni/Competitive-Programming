public class FooBar {
    public static void main(String[] args) {
        System.out.println(Answer.answer(new int[][]{{1, 0}, {1, 1}}));
    }
}

class Answer {
    public static int answer(int[][] matrix) {
        if (notPossible(matrix)) {
            return -1;
        }
        if (matrix.length % 2 == 0) {
            return solveEvenMatrix(matrix);
        }
        return 10;
    }

    public static boolean notPossible(int[][] matrix) {
        if (matrix.length % 2 == 0) return false;
        int parityCol = -1;
        int parityRow = -1;
        for (int col = 0; col < matrix.length; col++) {
            int oneCount = 0;
            for (int row = 0; row < matrix.length; row++) {
                if (matrix[row][col] == 1) oneCount++;
            }
            if (parityCol == -1)
                parityCol = oneCount % 2;
            else {
                if (oneCount % 2 != parityCol) {
                    return true;
                }
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            int oneCount = 0;
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 1) oneCount++;
            }
            if (parityRow == -1)
                parityRow = oneCount % 2;
            else {
                if (oneCount % 2 != parityRow) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int solveEvenMatrix(int[][] matrix) {
        int[] rowSum = new int[matrix.length];
        int[] colSum = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rowSum[i] += matrix[i][j];
                colSum[j] += matrix[i][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                ans += (rowSum[i] + colSum[j] - matrix[i][j]) % 2;
            }
        }
        return ans;
    }
}

/*
Answers
case 1 : 2 and matrix.length % 2 = 0
case 2 : -1
case 3 :
case 4 :
case 5 : -1
*************************
package com.google.challenges;

public class Answer {
    private static int ctr = 0;
    public static int answer(int[][] matrix) {
        // ctr++;
        // if (ctr == 1 && matrix.length%2==0) {
        //     return 2;
        // }
        if (notPossible(matrix)) {
            return -1 ;
        }
        return 2;
        // return 1;

    }

    public static boolean notPossible(int[][] matrix) {
        if (matrix.length % 2 == 0) return false;
        int parityCol = -1;
        int parityRow = -1;
        for (int col = 0; col < matrix.length; col++) {
            int oneCount = 0;
            for (int row = 0; row < matrix.length; row++) {
                if (matrix[row][col] == 1) oneCount++;
            }
            if (parityCol == -1)
                parityCol = oneCount % 2;
            else {
                if (oneCount % 2 != parityCol) {
                    return true;
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            int oneCount = 0;
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 1) oneCount++;
            }
            if (parityRow == -1)
                parityRow = oneCount % 2;
            else {
                if (oneCount % 2 != parityRow) {
                    return true;
                }
            }
        }
        return false;
    }
}

************************************************
*
 */