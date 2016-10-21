package MyImplementations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

class Point {
    long x;
    long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Point other = (Point) o;
        if (other.x == this.x && other.y == this.y)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

class Distance implements Comparable<Distance> {
    Point biker;
    Point bike;
    long distance;

    public Distance(Point bike, Point biker) {
        this.bike = bike;
        this.biker = biker;
        distance = (long) Math.sqrt(Math.pow(biker.x - bike.x, 2) + Math.pow(biker.y - biker.y, 2));
    }

    @Override
    public int compareTo(Distance d2) {
        if (this.distance > d2.distance) {
            return 1;
        } else if (this.distance < d2.distance) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return biker + " " + bike + " " + distance;
    }
}

public class Main {
    private static int sum = 0;

    public static void main(String[] a) {
        InputReader in = new InputReader(System.in);
        int bikers = in.nextInt();
        int bikes = in.nextInt();
        int bikersRequired = in.nextInt();
        ArrayList<Point> bikersPoints = new ArrayList<>(bikers);
        ArrayList<Point> bikesPoints = new ArrayList<>(bikes);
        ArrayList<Distance> distances = new ArrayList<>(bikers * bikes);
        for (int i = 0; i < bikers; i++)
            bikersPoints.add(new Point(in.nextLong(), in.nextLong()));
        for (int i = 0; i < bikes; i++)
            bikesPoints.add(new Point(in.nextLong(), in.nextLong()));
        for (Point p : bikesPoints) {
            for (Point q : bikersPoints) {
                distances.add(new Distance(p, q));
            }
        }
        new Solver().solve(distances, bikersRequired);
    }
}

class Solver {

    BigInteger answer = BigInteger.ZERO;

    public void removeLowestDistances(ArrayList<Distance> distances) {
        Point pointToRemove = distances.get(0).biker;
        Point pointToRemove2 = distances.get(0).bike;
        for (int i = 0; i < distances.size(); i++) {
            Point currBiker = distances.get(i).biker;
            if (currBiker.equals(pointToRemove)) {
                distances.remove(i);
                i--;
            }
        }
        for (int i = 0; i < distances.size(); i++) {
            Point currBike = distances.get(i).bike;
            if (currBike.equals(pointToRemove2)) {
                distances.remove(i);
                i--;
            }
        }
    }

    public void solve(ArrayList<Distance> distances, int bikersRequired) {
        long maxDistance = 0;
        for (int i = 0; i < bikersRequired; i++) {
            Collections.sort(distances);
            //answer = answer.add(BigInteger.valueOf(distances.get(0).distance)).modPow(BigInteger.valueOf(2), new BigInteger("198237401892374709812038947123098234234234"));
            if (maxDistance < distances.get(0).distance) maxDistance = distances.get(0).distance;
            removeLowestDistances(distances);
        }
        answer = BigInteger.valueOf(maxDistance).modPow(BigInteger.valueOf(2), new BigInteger("198237401892374709812038947123098234234234"));
        System.out.println(answer);
    }
}
























