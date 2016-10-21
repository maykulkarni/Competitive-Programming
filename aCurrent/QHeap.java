package aCurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 1/8/16.
 */
class Heap {
//        public static void main(String[] args) {
//        InputReader in = new InputReader(System.in);
//        int q = in.nextInt();
//        Heap heap = new Heap();
//        for (int i = 0; i < q; i++) {
//            int query = in.nextInt();
//            if (query == 3) {
//                heap.printMin();
//            } else {
//                if (query == 1) {
//                    // Insert
//                    int ins = in.nextInt();
//                    heap.insert(ins);
//                } else {
//                    // Del
//                    int del = in.nextInt();
//                    heap.delete(del);
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Heap heap = new Heap();
//        heap.insert(13);
//        heap.insert(30);
//        heap.insert(3);
//        heap.printMin();
//        heap.insert(1);
//        heap.printMin();
//        heap.delete(1);
//        heap.printMin();
//    }
}

public class QHeap {
    public ArrayList<Integer> arr;
    public Map<Integer, Integer> map;
    private int maxLength;

    QHeap() {
        arr = new ArrayList<>();
        map = new HashMap<>();
        maxLength = 0;
    }

    public void printMap() {
        for (Map.Entry e : map.entrySet()) {
            System.out.print("[ " + e.getKey() + " " + e.getValue() + " ], ");
        }
    }

    public void printArr() {
        for (int i = 0; i < maxLength; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }

    private int left(int position) {
        return position << 1;
    }

    private int right(int position) {
        return (position << 1) + 1;
    }

    private int parent(int position) {
        if (position == 0) return -1;
        return (int) Math.ceil(position / 2.0) - 1;
    }

    private boolean inRange(int position) {
        return position >= 0 && position < maxLength;
    }

    private void swap(int first, int second) {
        map.put(arr.get(first), second);
        map.put(arr.get(second), first);
        int tmp = arr.get(first);
        arr.set(first, arr.get(second));
        arr.set(second, tmp);
    }

    public void minHeapifySiftDown(int position) {
        int left = left(position);
        int right = right(position);
        int min = -1;
        if (inRange(left) && inRange(right)) {
            if (arr.get(left) < arr.get(right)) min = left;
            else min = right;
        } else if (inRange(left)) {
            min = left;
        }
        if (inRange(min) && arr.get(position) > arr.get(min)) {
            swap(position, min);
            minHeapifySiftDown(min);
        }
    }

    public void minHeapifySiftUp(int position) {
        int parent = parent(position);
        while (inRange(parent) && arr.get(parent) > arr.get(position)) {
            swap(parent, position);
            position = parent;
            parent = parent(parent);
        }
    }

    public void constructHeap() {
        int mid = (int) Math.floor(maxLength / 2);
        for (int i = 0; i < maxLength; i++) {
            map.put(arr.get(i), i);
        }
        for (int i = mid; i >= 0; i--) {
            minHeapifySiftDown(i);
        }
    }

    public void printMin() {
        System.out.println(arr.get(0));
    }

    public void insert(int ins) {
        maxLength++;
        map.put(ins, maxLength - 1);
        arr.add(ins);
        minHeapifySiftUp(maxLength - 1);
        //  this.printArr();
    }

    public void delete(int del) {
        int delIndex = map.get(del);
        swap(delIndex, maxLength - 1);
        maxLength--;
        minHeapifySiftDown(delIndex);
        //  this.printArr();
    }
}











