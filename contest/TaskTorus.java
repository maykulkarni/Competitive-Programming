package contest;

import Utils.BladeReader;

import java.io.PrintWriter;

public class TaskTorus {
    public void solve(int testNumber, BladeReader in, PrintWriter out) {
        int size = in.nextInt();
        int[][] mat = in.readIntMatrix(size, size);
        int[][] ext = new int[(size << 1) - 1][(size << 1) - 1];
        for (int i = 0; i < (size << 1) - 1; i++) {
            for (int j = 0; j < (size << 1) - 1; j++) {
                ext[i][j] = mat[i >= size ? (i - size) : i][j >= size ? (j - size) : j];
            }
        }

//        System.out.println("Extended Matrix");
//        new PrintMat(ext);
        solve(ext, out, size);
    }

    private void solve(int[][] ext, PrintWriter out, int N) {
        long maxSum = Long.MIN_VALUE;
        int[][] costMatrix = getCostMatrix(ext);
        for (int colStart = 0; colStart < N; colStart++) {
            for (int colEnd = colStart; colEnd < colStart + N; colEnd++) {
                int[] currentVector = getCurrentVector(ext, colStart, colEnd, costMatrix);
                maxSum = Math.max(maxSum, kadane(currentVector, N));
            }
        }
        out.println(maxSum);
    }

    private long kadane(int[] currentVector, int N) {
        // Limit the rows
        long max = Long.MIN_VALUE;
        long sum = 0;
        boolean vis[] = new boolean[currentVector.length];
        for (int i = 0; i < currentVector.length; i++) {
            if (i >= N && vis[i - N]) {
                sum -= currentVector[i - N];
                vis[i - N] = false;
                for (int x = i - N + 1; x < i && !vis[x - 1]; x++)
                    if (currentVector[x] < 0) {
                        sum -= currentVector[x];
                        vis[x] = false;
                    }
                max = Math.max(max, sum);
            }
            sum += currentVector[i];
            vis[i] = true;
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
                vis[i] = false;
            }
        }
        return max;
    }

    private int[] getCurrentVector(int[][] ext, int colStart, int colEnd, int[][] costMatrix) {
        int[] currentVector = new int[ext.length];
        for (int i = 0; i < ext.length; i++) {
            currentVector[i] = costMatrix[i][colEnd] - (colStart > 0 ? costMatrix[i][colStart - 1] : 0);
        }
//        System.out.println("Current Vector");
//        System.out.println(Arrays.toString(currentVector));
        return currentVector;
    }

    private int[][] getCostMatrix(int[][] ext) {
        int[][] costMatrix = new int[ext.length][ext[0].length];
        for (int i = 0; i < ext.length; i++) {
            for (int j = 0; j < ext[0].length; j++) {
                costMatrix[i][j] = (j > 0 ? costMatrix[i][j - 1] : 0) + ext[i][j];
            }
        }
//        System.out.println("Cost Matrix");
//        new PrintMat(costMatrix);
        return costMatrix;
    }
}

