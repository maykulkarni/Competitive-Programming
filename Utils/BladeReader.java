package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by mayur on 8/11/16.
 */
public class BladeReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public BladeReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public char nextChar() {
        return next().charAt(0);
    }
    public String nextString() {
        return next();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public int[] readIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = nextInt();
        }
        return array;
    }

    public Integer[] readIntegerArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = nextInt();
        }
        return array;
    }

    public int[][] readIntMatrix(int row, int col) {
        int[][] mat = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = nextInt();
            }
        }
        return mat;
    }

    public long[] readLongArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = nextLong();
        }
        return array;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public char[][] charGrid(int row, int cols) {
        char[][] grid = new char[row][cols];
        for (int i = 0; i < row; i++) {
            grid[i] = next().toCharArray();
        }
        return grid;
    }
}