package Utils;

import java.util.Arrays;

/**
 * Created by Mayur Kulkarni on 12/12/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class SearchUtils {

    public static int getMinIntIndex(int[] arr, int endIndex) {
        int maxIndex = 0;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < endIndex; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int maxInArray(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        return maxVal;
    }

    public static int maxInArrayStream(int[] arr) {
        return Arrays.stream(arr)
                .parallel()
                .max()
                .getAsInt();
    }


    public static int binSearchFirstOccurence(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low - 1 : ans;
    }

    public static int binSearchNumberOfItemsLessThan(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low : ans;
    }

    public static int binSearchLastOccurence(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                ans = mid;
            }
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans == -1 ? low - 1 : ans;
    }

    private static int binSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low - 1;
    }

    public static int getMinIntIndex(Integer[] arr) {
        int min = Integer.MAX_VALUE;
        int minIndx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndx = i;
            }
        }
        return minIndx;
    }

    public static int getMaxIntIndex(int[] arr) {
        int maxIndex = -1;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    public static int getMaxLongIndex(long[] arr) {
        int maxIndex = -1;
        long maxVal = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int getMinLongIndex(long[] arr) {
        int minIndex = -1;
        long minVal = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);

    }
}
