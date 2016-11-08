package aCurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by mayur on 7/11/16.
 */
class BladeReader {
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

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long distanceFromMid() {
        return (long) Math.ceil(Math.sqrt(x * x + y * y));
    }
}

public class CircleRadius {

    public static int binarySearch(List<Long> a, long key) {
        int low = 0, high = a.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a.get(mid);
            if (midVal < key)
                low = mid + 1;
            else if (mid > 0 && a.get(mid - 1) >= key) //we already know midval>=key here
                high = mid - 1;
            else if (midVal == key) //found the 1st key
                return mid;
            else
                return ~mid;      //found insertion point
        }
        return ~(a.size());       //insertion point after everything
    }

    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int nPoints = in.nextInt();
        Point[] points = new Point[nPoints];
        for (int i = 0; i < nPoints; i++) {
            points[i] = new Point(in.nextLong(), in.nextLong());
        }
        List<Long> distances = new ArrayList<>();
        for (Point p : points) {
            distances.add(p.distanceFromMid());
        }
        Collections.sort(distances);
        for (int queries = in.nextInt(); queries > 0; queries--) {
            BigInteger currrentRadius = new BigInteger(in.next());
            if (currrentRadius.compareTo(BigInteger.valueOf(1000000000L)) >= 1) {
                System.out.println(distances.size());
                continue;
            }
            long currRad = currrentRadius.longValue();
            long indx = binarySearch(distances, currRad);
            if (indx < 0) {
                // is insertion point
                System.out.println(Math.abs(indx) - 1);
            } else {
                System.out.println(indx + 1);
            }
        }
    }
}
