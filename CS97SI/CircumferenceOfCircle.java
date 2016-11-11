package CS97SI;

import java.util.Scanner;

/**
 * Created by mayur on 11/11/16.
 */
public class CircumferenceOfCircle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        new CircumferenceSolver().solve(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
    }
}

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class CircumferenceSolver {
    public void solve(double x1, double y1, double x2, double y2, double x3, double y3) {
        Point midPoint1 = new Point((x1 + x2) / 2, (y1 + y2) / 2);
        Point midPoint2 = new Point((x2 + x3) / 2, (y2 + y3) / 2);
        double slope1 = (y2 - y1) / (x2 - x1);
        double c1 = (-slope1 * midPoint1.x) + midPoint1.y;
        double slope2 = (y3 - y2) / (x3 - x2);
        double c2 = (-slope2 * midPoint2.x) + midPoint2.y;
        Point pointOfContact = new Point((c2 - c1) / (slope1 - slope2), (slope1 * c2 - slope2 * c1) / (slope1 - slope2));
        double radius = Math.sqrt(Math.abs(pointOfContact.x - x1) + Math.abs(pointOfContact.y - y1));
        System.out.println(2 * Math.PI * radius);
    }
}