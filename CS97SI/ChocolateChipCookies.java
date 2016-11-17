package CS97SI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hitlist
 * Created by Mayur Kulkarni on 11/14/2016.
 * Email : mayurkulkarni012@gmail.com
 */
class Line {
    double a;
    double b;
    double c;
    // a*x + b*y + c = 0

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }
}

public class ChocolateChipCookies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Point> list = new ArrayList<>();
        while (in.hasNext()) {
            list.add(new Point(in.nextDouble(), in.nextDouble()));
        }
        new ChocolateChipCookiesSolver().solve(list);
    }
}

class ChocolateChipCookiesSolver {
    public void solve(List<Point> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).distanceTo(list.get(j)) > 5.0)
                    continue;
                Line bisector = getBisector(list.get(i), list.get(j));
            }
        }
    }

    private Line getBisector(Point one, Point two) {
        return null;
    }
}
































