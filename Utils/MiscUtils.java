package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayur Kulkarni on 12/12/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class MiscUtils {
    private static List<List<Integer>> list = new ArrayList<>();

    private static void permute(int start, int[] input) {
        if (start == input.length) {
            //System.out.println(input);
            List<Integer> temp = new ArrayList<>();
            for (int x : input) {
                temp.add(x);
            }
            list.add(temp);
            return;
        }
        for (int i = start; i < input.length; i++) {
            // swapping
            int temp = input[i];
            input[i] = input[start];
            input[start] = temp;
            // swap(input[i], input[start]);
            permute(start + 1, input);
            // swap(input[i],input[start]);
            int temp2 = input[i];
            input[i] = input[start];
            input[start] = temp2;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-10);
        list.add(-10);
        list.add(-4);
        list.add(-99);
        System.out.println(maximumSubsequenceValue(list));
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

    public static int linearSearch(Comparable[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(key) == 0) return i;
        }
        return -1;
    }

    public static long listSum(List<Integer> list) {
        long sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    public static long maximumSubsequenceValue(List<Integer> list) {
        long max = list.stream().max(Integer::compare).get();
        long sum = 0;
        for (int i : list) {
            sum += i;
            if (sum > max) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }

    public static int[][] matrixDeepCopy(int[][] inputArray) {
        int[][] ans = new int[inputArray.length][inputArray[0].length];
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[0].length; j++) {
                ans[i][j] = inputArray[i][j];
            }
        }
        return ans;
    }

    public static char[][] matrixDeepCopy(char[][] inputArray) {
        char[][] ans = new char[inputArray.length][inputArray[0].length];
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[0].length; j++) {
                ans[i][j] = inputArray[i][j];
            }
        }
        return ans;
    }

    public static void incrementInsertIntMap() {

    }
}
