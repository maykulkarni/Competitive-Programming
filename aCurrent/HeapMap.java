package aCurrent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mayur on 28/7/16.
 */
public class HeapMap {
    private int[] arr;
    private int maxLength;
    private Map<Integer, Integer> map;

    //    private long[] heap;
//
    public HeapMap(int[] arr) {
        this.arr = arr;
        maxLength = arr.length;
        map = new HashMap<>();
    }

    public void printMap() {
        for (Map.Entry e : map.entrySet()) {
            System.out.print("[ " + e.getKey() + " " + e.getValue() + " ], ");
        }
    }

    private int left(int position) {
        return position << 1;
    }

    private int right(int position) {
        return (position << 1) + 1;
    }

    private boolean inRange(int position) {
        return position >= 0 && position < maxLength;
    }

    public void maxHeapify(int position) {
        int left = left(position);
        int right = right(position);
        int max = -1;
        if (inRange(left) && inRange(right)) {
            if (arr[left] > arr[right]) max = left;
            else max = right;
        } else if (inRange(left)) {
            max = left;
        }
        if (inRange(max) && arr[position] < arr[max]) {
            swap(position, max);
            maxHeapify(max);
        }
    }

    private void swap(int first, int second) {
        map.put(arr[first], second);
        map.put(arr[second], first);
//        arr[first] = arr[first] ^ arr[second];
//        arr[second] = arr[first] ^ arr[second];
//        arr[first] = arr[first] ^ arr[second];
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }

    public void constructHeap() {
        int mid = (int) Math.floor(maxLength / 2);
        for (int i = 0; i < maxLength; i++) {
            map.put(arr[i], i);
        }
        for (int i = mid; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public void printArr() {
        System.out.println(Arrays.toString(arr));
    }

    public void pluckMin() {
        int min = arr[0];
        swap(maxLength - 1, 0);
        System.out.println("Min : " + min);
        map.put(arr[maxLength - 1], -1);
        arr[maxLength - 1] = -1;
        maxLength--;
        maxHeapify(0);
    }
}

class Demo {
    public static void main(String[] args) {
        HeapMap h = new HeapMap(new int[]{4, 7, 1, 8, 3, 55, 2, 6, 44});
        h.printArr();
        h.printMap();
        h.constructHeap();
        System.out.println("heap constructed");
        h.printArr();
        h.printMap();
    }
}