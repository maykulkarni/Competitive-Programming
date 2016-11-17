package Random;

public class Demo {
    private static int determineDirection(Point curr, Point king) {
        if (curr.y == king.y) {             // Horizontal
            if (king.x > curr.x) {
                return 7;
            } else {
                return 3;
            }
        } else if (curr.x == king.x) {      // Vertical
            if (king.y > curr.y) {
                return 5;
            } else {
                return 1;
            }
        } else if (curr.slope(king) == -1) {
            if (king.x > curr.x && king.y > curr.y) {
                return 4;
            } else {
                return 8;
            }
        } else if (curr.slope(king) == 1) {
            if (king.x > curr.x && king.y > curr.y) {
                return 6;
            } else {
                return 2;
            }
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        Point king = new Point('K', -703056331, -575309264);
        Point pseudoNW = new Point('Q', -703054641, -575310954);
        Point actualNW = new Point('Q', -703069494, -575296101);
        //System.out.println(determineDirection(pseudoNW, king));
        System.out.println(pseudoNW.slope(king));
    }

    static class Point {
        char type;
        double x;
        double y;

        Point(char type, double x, double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        public double slope(Point other) {
            System.out.println("y2 : " + other.y + " y1 : " + y + " x2 : " + other.x + " x1 : " + x);
            System.out.println(other.y - y);
            System.out.println(other.x - x);
            return (y - other.y) / (x - other.x);
//            if ((x - other.x) == (y - other.y)) {
//                return 1;
//            } else if ((x - other.x) == -(y - other.y)) {
//                return -1;
//            } else {
//                return -2;
//            }
        }

        public double slope(double x2, double y2) {
            if ((x - x2) == (y - y2)) {
                return 1;
            } else if ((x - x2) == -(y - y2)) {
                return -1;
            } else {
                return -2;
            }
        }

        @Override
        public String toString() {
            return String.format("%s %9.1f %9.1f ", type, x, y);
        }

        public double distance(Point other) {
            return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
        }
    }
}