package CPPrograms;

import Utils.BladeReader;

import java.util.Arrays;

/**
 * Created by mayur on 7/11/16.
 */

public class CountingProblem {
    public static long[] arr;
    public static StringBuilder sb = new StringBuilder();

    //    public static void cnt(int x, long y) {
//        if (x == 0) {
//            int pos = Math.abs(Arrays.binarySearch(arr, y));
//            if (pos < arr.length && arr[pos] != y) pos--;
//            if (pos >= arr.length || pos < 0 ) {
//                sb.append(0).append("\n");
//            } else {
//                sb.append(arr.length - pos).append("\n");
//            }
//        } else {
//            int pos = Math.abs(Arrays.binarySearch(arr, y + 1));
//            if (pos < arr.length && arr[pos - 1] != y) pos--;
//            if (pos >= arr.length || pos < 0) {
//                sb.append(0).append("\n");
//            } else {
//                sb.append(arr.length - pos).append("\n");
//            }
//        }
//    }
    public static void cnt(int type, long val) {
        boolean isInsertionPoint = false;
        if (type == 0) {
            // ge
            if (arr[0] >= val) {
                sb.append(arr.length).append("\n");
                return;
            }
            int pos = binarySearch(arr, val);
            if (pos < 0) isInsertionPoint = true;
            pos = Math.abs(pos);
            if (pos >= arr.length) {
                sb.append(0).append("\n");
                return;
            }
            if (isInsertionPoint && arr[pos - 1] != val) pos--;
            sb.append(arr.length - pos).append("\n");
        } else {
            // g
            if (arr[0] > val) {
                sb.append(arr.length).append("\n");
                return;
            }
            int pos = binarySearch(arr, val);
            if (pos < 0) isInsertionPoint = true;
            pos = Math.abs(pos);
            if (pos >= arr.length) {
                sb.append(0).append("\n");
                return;
            }
            while (!isInsertionPoint && pos < arr.length && arr[pos] == val) {
                pos++;
            }
            if (isInsertionPoint && arr[pos - 1] != val) pos--;
            sb.append(arr.length - pos).append("\n");
        }
    }

    public static int binarySearch(long[] a, long key) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];
            if (midVal < key)
                low = mid + 1;
            else if (mid > 0 && a[mid - 1] >= key) //we already know midval>=key here
                high = mid - 1;
            else if (midVal == key) //found the 1st key
                return mid;
            else
                return ~mid;      //found insertion point
        }
        return ~(a.length);       //insertion point after everything
    }

    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int size = in.nextInt();
        arr = in.readLongArray(size);
        Arrays.parallelSort(arr);
        for (int i = in.nextInt(); i > 0; i--) {
            cnt(in.nextInt(), in.nextLong());
        }
        System.out.print(sb.toString());
    }
}

























