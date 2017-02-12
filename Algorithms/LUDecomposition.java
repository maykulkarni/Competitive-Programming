package Algorithms;

import Utils.NumberUtils;
import Utils.PrintMat;

/**
 * Created by Mayur Kulkarni on 2/8/2017.
 * Email : mayurkulkarni012@gmail.com
 */
public class LUDecomposition {
    private double[][] L = NumberUtils.identityDouble(3, 3);

    public static void main(String[] args) {
        new LUDecomposition().decompose();
    }

    /**
     * Operates onRow a row of matrix in form of:
     * matrix[onRow] = matrix[onRow] op alpha*matrix[intRow]
     * where op can be + - / *
     * This is a O(N) operation
     *
     * @param matrix matrix onRow which the operation is to be performed
     * @param onRow  row on which the operation is to be performed
     * @param op     the operator, + - / *
     * @param alpha  times the intRow
     * @param intRow intermediate row
     */
    private void operate(double[][] matrix, int onRow, char op, double alpha, int intRow) {
        System.out.println("R" + (onRow + 1) + " -> " + "R" + (onRow + 1)
                + " " + op + " " + alpha + "R" + (intRow + 1));
        System.out.println();
        // since we're using minus operation
        // hence no sign change, else's it'd be
        // opposite of whatever sign is used
        L[onRow][intRow] = alpha;
        switch (op) {
            case '+':
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[onRow][i] += alpha * matrix[intRow][i];
                }
                break;
            case '-':
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[onRow][i] -= alpha * matrix[intRow][i];
                }
                break;
            case '/':
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[onRow][i] /= alpha * matrix[intRow][i];
                }
                break;
            case '*':
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[onRow][i] *= alpha * matrix[intRow][i];
                }
                break;
        }
        new PrintMat(matrix);
    }

    private void decompose() {
        double[][] m = {
                {5, 3, 1},
                {3, 3, 1},
                {1, 1, 1}};
        new PrintMat(m);
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i][i] == 0) {
                    throw new RuntimeException("0 element in diagonal");
                }
                operate(m, j, '-', m[j][i] / m[i][i], i);
            }
        }
        System.out.println("L :");
        new PrintMat(L);
        System.out.println("U: ");
        new PrintMat(m);
    }
}