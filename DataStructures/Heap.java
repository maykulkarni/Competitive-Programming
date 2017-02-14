package DataStructures;

import java.util.*;

/**
 * Created by Uzumaki Naruto on 2/12/2017.
 */
public class Heap<T> {
    private List<GraphNode<T>> heapList;
    private Map<GraphNode<T>, Integer> indexOf = new HashMap<GraphNode<T>, Integer>();

    // todo
    Heap(int[] array) {

    }

    public Heap() {
        heapList = new ArrayList<GraphNode<T>>();
        indexOf = new HashMap<GraphNode<T>, Integer>();
    }

    public static void test() {
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            int size = r.nextInt(1000) + 10;
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = r.nextInt(10000);
            }
            Heap<Integer> heap = new Heap<>();
            for (int x : arr) {
                heap.add(new GraphNode<Integer>(x, x));
            }
            int[] arrNew = new int[size];
            for (int j = 0; j < size; j++) {
                arrNew[j] = heap.poll().getWeight();
            }
            Arrays.parallelSort(arr);
            for (int j = 0; j < size; j++) {
                if (arr[j] != arrNew[j]) {
                    System.out.println("Failed at :");
                    System.out.println(Arrays.toString(arr));
                    System.out.println(Arrays.toString(arrNew));
                    System.out.println("At pos : " + j);
                    System.exit(-1);
                }
            }
            System.out.print("\r Passed " + i + "/100,000");
        }
        System.out.println("Passed all cases!");
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        heap.add(new GraphNode<>(1, 1));
        heap.add(new GraphNode<>(2, 2));
        heap.add(new GraphNode<>(3, 3));
        heap.add(new GraphNode<>(4, 4));
        System.out.println(heap.poll());
        heap.alterPriority(2, 100);
        System.out.println(heap.poll());
    }

    private int left(int index) {
        return (index << 1) + 1;
    }

    private int right(int index) {
        return (index << 1) + 2;
    }

    private int parent(int index) {
        return (index - 1) >> 1;
    }

    private boolean hasLeft(int index) {
        return left(index) >= 0 && left(index) < heapList.size();
    }

    private boolean hasRight(int index) {
        return right(index) >= 0 && right(index) < heapList.size();
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    public GraphNode<T> peek() {
        checkIfEmpty();
        return heapList.get(0);
    }

    private void checkIfEmpty() {
        if (heapList.isEmpty()) throw new IllegalStateException("Heap is empty!");
    }

    public GraphNode<T> poll() {
        checkIfEmpty();
        GraphNode item = heapList.get(0);
        heapList.set(0, heapList.get(heapList.size() - 1));
        heapList.remove(heapList.size() - 1);
        indexOf.remove(item);
        indexOf.put(heapList.get(0), 0);
        heapifySiftDown(0);
        return item;
    }

    private void heapifySiftDown(int index) {
        while (true) {
            boolean operated = false;
            if (hasLeft(index) && heapList.get(left(index)).getWeight() < heapList.get(index).getWeight()
                    && (!hasRight(index) || heapList.get(left(index)).getWeight() < heapList.get(right(index)).getWeight())) {
                swapValues(left(index), index);
                index = left(index);
                operated = true;
            } else if (hasRight(index) && heapList.get(right(index)).getWeight() < heapList.get(index).getWeight()
                    && (heapList.get(right(index)).getWeight() <= heapList.get(left(index)).getWeight())) {
                swapValues(right(index), index);
                index = right(index);
                operated = true;
            }
            if (!operated) break;
        }
    }

    public void add(GraphNode<T> item) {
        if (indexOf.containsKey(item)) throw new IllegalStateException("Key : " + item + " already present in Heap");
        heapList.add(item);
        indexOf.put(item, heapList.size() - 1);
        heapifySiftUp(heapList.size() - 1);
    }

    private void swapValues(int indexOne, int indexTwo) {
        GraphNode<T> temp = heapList.get(indexOne);
        heapList.set(indexOne, heapList.get(indexTwo));
        heapList.set(indexTwo, temp);
        swapIndices(heapList.get(indexOne), heapList.get(indexTwo));
    }

    private void swapIndices(GraphNode<T> one, GraphNode<T> two) {
        int oldIndexOne = indexOf.get(one);
        int oldIndexTwo = indexOf.get(two);
        indexOf.put(one, oldIndexTwo);
        indexOf.put(two, oldIndexOne);
    }

    public void alterPriority(T nodeName, int newWeight) {
        GraphNode<T> temp = new GraphNode<T>(nodeName, -1);
        if (!indexOf.containsKey(temp))
            throw new IllegalStateException("Item : " + nodeName + " doesn't exist in heap.");
        int index = indexOf.get(temp);
        GraphNode node = heapList.get(index);
        node.setWeight(newWeight);
        if (hasLeft(index) && node.getWeight() > heapList.get(left(index)).getWeight()
                || hasRight(index) && node.getWeight() > heapList.get(right(index)).getWeight()) {
            heapifySiftDown(index);
        } else {
            heapifySiftUp(index);
        }
    }

    public int getPriority(T node) {
        return heapList.get(
                indexOf.get(new GraphNode<T>(node, -1))
        ).getWeight();
    }

    private void heapifySiftUp(int index) {
        while (hasParent(index) && heapList.get(parent(index)).getWeight() > heapList.get(index).getWeight()) {
            swapValues(parent(index), index);
            index = parent(index);
        }
    }

    // 1 3 7 15
    @Override
    public String toString() {
        int next = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heapList.size(); i++) {
            sb.append(heapList.get(i)).append(" ");
            if ((i + 1) == Math.pow(2, next) - 1) {
                sb.append("\n");
                next++;
            }
        }
        return sb.toString();
    }
}
