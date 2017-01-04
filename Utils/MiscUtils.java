package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mayur Kulkarni on 12/12/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class MiscUtils {
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3};
        do {
            System.out.println(Arrays.toString(arr));
        } while (MiscUtils.nextPermutation(arr) != null);
    }

    public static Comparable[] nextPermutation(Comparable[] arr) {
        int a = getLastSwapIndex(arr);
        if (a == -1) return null;
        int toSwap = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].compareTo(arr[a]) > 0) {
                toSwap = i;
                break;
            }
        }
        swap(arr, a, toSwap);
        int end = arr.length - 1;
        a++;
        while (a < end) {
            swap(arr, a, end);
            a++;
            end--;
        }
        return arr;
    }

    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int getLastSwapIndex(Comparable[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i].compareTo(arr[i + 1]) < 0) {
                return i;
            }
        }
        return -1;
    }

    public int linearSearch(Comparable[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(key) == 0) return i;
        }
        return -1;
    }
}
