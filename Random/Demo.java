package Random;


import Utils.SearchUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String args[]) {
        Random random = new Random();
        List<Double> normal = new ArrayList<>();
        List<Double> stream = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int size = random.nextInt(100000) + 10;
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = random.nextInt(Integer.MAX_VALUE);
            }
            double startTime = System.currentTimeMillis();
            int max = SearchUtils.maxInArray(arr);
            double normalTime = System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            int maxStream = SearchUtils.maxInArrayStream(arr);
            double streamTime = System.currentTimeMillis() - startTime;
            assert max == maxStream;
            normal.add(normalTime);
            stream.add(streamTime);
        }
        printStatistics(normal, stream);
    }

    public static double average(List<Double> list) {
        double sum = 0;
        for (double a : list) {
            sum += a;
        }
        return sum / list.size();
    }

    private static void printStatistics(List<Double> normal, List<Double> stream) {
        System.out.println("Normal: ");
        System.out.println("Average: " + average(normal) + " ms");
        System.out.println("Worst: " + normal.stream().mapToDouble((x) -> x).max().getAsDouble());
        System.out.println("Best: " + normal.stream().mapToDouble((x) -> x).min().getAsDouble());
        System.out.println("Stream: ");
        System.out.println("Average: " + average(stream) + " ms");
        System.out.println("Worst: " + stream.stream().mapToDouble((x) -> x).max().getAsDouble());
        System.out.println("Best: " + stream.stream().mapToDouble((x) -> x).min().getAsDouble());
    }
}
