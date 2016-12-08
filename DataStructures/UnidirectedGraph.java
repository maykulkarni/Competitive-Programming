package DataStructures;

import java.util.Iterator;

/**
 * Created by mayur on 15/6/16.
 */

class AdjNode {
    public int number;
    public AdjNode next;

    public AdjNode(int number) {
        this.number = number;
        next = null;
    }
}

class AdjList implements Iterable<AdjNode> {
    AdjNode head;
    private AdjNode tail;

    public void addEdgeInList(int to) {
        if (head == null) {
            head = new AdjNode(to);
            tail = head;
        } else {
            tail.next = new AdjNode(to);
            tail = tail.next;
        }
    }

    public void printList() {
        AdjNode temp = head;
        if (temp == null) {
            System.out.println("Empty List");
            return;
        }
        while (temp != null) {
            System.out.print(temp.number + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public Iterator<AdjNode> iterator() {
        return new Iterator() {
            AdjNode temp = head;
            AdjNode retTemp;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public AdjNode next() {
                retTemp = temp;
                temp = temp.next;
                return retTemp;
            }
        };
    }
}

public class UnidirectedGraph {

    private int size;
    private AdjList[] adjLists;

    public UnidirectedGraph(int size) {
        this.size = size;
        adjLists = new AdjList[size];
        for (int i = 0; i < size; i++) {
            adjLists[i] = new AdjList();
        }
    }

    //    public static void main(String[] args) {
//        UnidirectedGraph graph = new UnidirectedGraph(3);
//        graph.addEdge(0, 1);
//        graph.addEdge(1, 2);
//        graph.addEdge(2, 0);graph.addEdge(1, 0);graph.addEdge(2, 1);
//        graph.addEdge(0, 2);
//
//
//
//        for (int i=0; i<3; i++){
//            //graph.getAdjList(i).printList();
//            for (AdjNode node : graph.getAdjList(i)){
//                System.out.print(node.number);
//            }
//            System.out.println();
////            Iterator it = graph.getAdjList(i).iterator();
////            if(it.hasNext())
////                System.out.println("has nex " + i);
////            else
////                System.out.println("no hasnext " + i);
////            while(it.hasNext()){
////                System.out.println(it.next());
////            }
//        }
//    }
    public static void main(String[] args) {
        UnidirectedGraph g = new UnidirectedGraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        for (int i = 0; i < 4; i++)
            for (AdjNode n : g.getAdjList(i)) {
                System.out.println(n.number);
            }
    }

    public int getSize() {
        return size;
    }

    public AdjList getAdjList(int index) {
        return adjLists[index];
    }

    public void addEdge(int from, int to) {
        adjLists[from].addEdgeInList(to);
    }

    public void printGraph() {
        for (int i = 0; i < size; i++) {
            adjLists[i].printList();
        }
    }
}

















