import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Mayur Kulkarni on 15/11/16.
 * E-mail : mayurkulkarni012@gmail.com
 */
class FastIO {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public FastIO(InputStream stream) {
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
}

public class TopCoderOne {
    public static void main(String[] args) {
        FastIO in = new FastIO(System.in);
        int cases = in.nextInt();
    }
}

class TopCoderOneSolver {
    public void solve() {
    }
}

