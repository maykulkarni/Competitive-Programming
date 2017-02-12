package Random;


import DataStructures.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String args[]) {
        new Demo().meth();
    }

    public void meth() {
        List<Pair<Integer, Integer>> pairList = new ArrayList<>();
        pairList.add(new Pair<>(1, 5));
        pairList.add(new Pair<>(2, 2));
        pairList.add(new Pair<>(2, 22));
        pairList.add(new Pair<>(1, 22));
        pairList.sort(Comparator.comparingInt(Pair::firstValue)
                .thenComparingInt(Pair::secondValue));

        System.out.println(pairList);
    }
}
