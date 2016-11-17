package contest;

import Utils.BladeReader;

import java.io.PrintWriter;

class Point {
    char type;
    double x;
    double y;

    Point(char type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public double slope(Point other) {
        if ((x - other.x) == (y - other.y)) {
            return 1;
        } else if ((x - other.x) == -(y - other.y)) {
            return -1;
        } else {
            return -2;
        }
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

public class TaskD {
    public void solve(int testNumber, BladeReader in, PrintWriter out) {
        long numberOfBlack = in.nextLong();
        Point king = new Point('K', in.nextDouble(), in.nextDouble());
        Point north = null, south = null, east = null, west = null;
        Point northEast = null, southEast = null, southWest = null, northWest = null;
        for (long i = 0; i < numberOfBlack; i++) {
            char currType = in.next().charAt(0);
            double currX = in.nextDouble();
            double currY = in.nextDouble();
            if (king.slope(currX, currY) == 1 || king.slope(currX, currY) == -1 || currX == king.x || currY == king.y) {
                Point curr = new Point(currType, currX, currY);
                int dir = determineDirection(curr, king);
                double slope = king.slope(currX, currY);
                switch (dir) {
                    case 1:
                        if (north == null) {
                            north = curr;
                        } else if (curr.distance(king) < north.distance(king)) {
                            System.out.println(curr + " overriden by " + north + " N");
                            north = curr;
                        }
                        break;
                    case 2:
                        if (northEast == null) {
                            northEast = curr;
                        } else if (curr.distance(king) < northEast.distance(king)) {
                            System.out.println(curr + " overriden by " + northEast + " NE");
                            northEast = curr;
                        }
                        break;
                    case 3:
                        if (east == null) {
                            east = curr;
                        } else if (curr.distance(king) < east.distance(king)) {
                            System.out.println(curr + " overriden by " + east + " E");
                            east = curr;
                        }
                        break;
                    case 4:
                        if (southEast == null) {
                            southEast = curr;
                        } else if (curr.distance(king) < southEast.distance(king)) {
                            System.out.println(curr + " overriden by " + southEast + " SE");
                            southEast = curr;
                        }
                        break;
                    case 5:
                        if (south == null) {
                            south = curr;
                        } else if (curr.distance(king) < south.distance(king)) {
                            System.out.println(curr + " overriden by " + south + " S");
                            south = curr;
                        }
                        break;
                    case 6:
                        if (southWest == null) {
                            southWest = curr;
                        } else if (curr.distance(king) < southWest.distance(king)) {
                            System.out.println(curr + " overriden by " + southWest + " SW");
                            southWest = curr;
                        }
                        break;
                    case 7:
                        if (west == null) {
                            west = curr;
                        } else if (curr.distance(king) < west.distance(king)) {
                            System.out.println(curr + " overriden by " + west + " W");
                            west = curr;
                        }
                        break;
                    case 8:
                        if (northWest == null) {
                            northWest = curr;
                        } else if (curr.distance(king) < northWest.distance(king)) {
                            System.out.println(curr + " overriden by " + northWest + " NW");
                            northWest = curr;
                        }
                        break;
                    default:
                        throw new RuntimeException();
                }
                if (currX == -703069494 && currY == -575296101) {
                    System.out.println("Processing solution " + slope + " dir : " + dir + " NW nearest : " + northWest + " NWN m : " + king.slope(northWest));
                }
            }
        }
        // Got all minimum variables, now check if check mate
        boolean isCheckMate = false;
        if (north != null && (north.type == 'Q' || north.type == 'R')) {
            isCheckMate = true;
        } else if (northEast != null && (northEast.type == 'Q' || northEast.type == 'B')) {
            isCheckMate = true;
        } else if (east != null && (east.type == 'Q' || east.type == 'R')) {
            isCheckMate = true;
        } else if (southEast != null && (southEast.type == 'Q' || southEast.type == 'B')) {
            isCheckMate = true;
        } else if (south != null && (south.type == 'Q' || south.type == 'R')) {
            isCheckMate = true;
        } else if (southWest != null && (southWest.type == 'Q' || southWest.type == 'B')) {
            isCheckMate = true;
        } else if (west != null && (west.type == 'Q' || west.type == 'R')) {
            isCheckMate = true;
        } else if (northWest != null && (northWest.type == 'Q' || northWest.type == 'B')) {
            isCheckMate = true;
        }
        System.out.println("final NW : " + northWest);
        out.println(isCheckMate ? "YES" : "NO");
    }

    private int determineDirection(Point curr, Point king) {
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
}





















