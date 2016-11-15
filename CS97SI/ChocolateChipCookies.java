package CS97SI;

/**
 *
 * Created by Mayur Kulkarni on 11/14/2016.
 * Email : mayurkulkarni012@gmail.com
 */
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

    }
}

class ChocolateChipCookiesSolver {
    public void solve() {

    }
}
