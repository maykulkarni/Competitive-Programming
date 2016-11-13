package CS97SI;

import java.util.Scanner;

/**
 * Created by mayur on 11/11/16.
 */
public class CircumferenceOfCircle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
            new CircumferenceSolver().solve(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
    }
}

class CircumferenceSolver {
    private static final double EPSILON = 0.00001;

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    public void solve(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = dist(x1, y1, x2, y2);
        double b = dist(x2, y2, x3, y3);
        double c = dist(x3, y3, x1, y1);
        double radius = a * b * c / (Math.sqrt((a + b + c) * (b + c - a) * (c + a - b) * (a + b - c)));
        System.out.printf("%.2f\n", 2 * Math.PI * radius);
    }
}