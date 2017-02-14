package Algorithms;

import DataStructures.DisjointSetUnion;
import DataStructures.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Uzumaki Naruto on 2/9/2017.
 */
public class Kruskal {

    public static long getMSTCost(List<Pair<Long, Pair<Long, Long>>> edgeList, int nodes) {
        edgeList.sort(Comparator.comparing(Pair::firstValue));
        DisjointSetUnion dsu = new DisjointSetUnion(nodes + 1);
        List<Pair<Long, Long>> mstList = new ArrayList<>();
        long mstCost = 0;
        for (Pair<Long, Pair<Long, Long>> pair : edgeList) {
            if (dsu.find(pair.q.p.intValue()) != dsu.find(pair.q.q.intValue())) {
                mstCost += pair.p;
                mstList.add(pair.q);
                dsu.union(pair.q.p.intValue(), pair.q.q.intValue());
            }
        }
        return mstCost;
    }

    private List<Pair<Long, Pair<Long, Long>>> acceptEdgeList() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        List<Pair<Long, Pair<Long, Long>>> edgeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            edgeList.add(new Pair<>(in.nextLong(), new Pair<>(in.nextLong(), in.nextLong())));
        }
        return edgeList;
    }
}
