package CPPrograms;

import MyImplementations.InputReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayur on 5/7/16.
 */

public class ConnectedCellInAGrid {
    public static void main(String[] at) {
        InputReader in = new InputReader(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(new ConnectedCellInAGridSolver().solve(arr));
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInvalid() {
        return this.x == -1 && this.y == -1;
    }

    public String toString() {
        return "[" + this.x + "," + this.y + "]\t";
    }

    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}

class Node {
    int data;
    Point parent;
    int childCount;

    Node(int data, Point parent) {
        this.data = data;
        this.parent = parent;
        childCount = 0;
    }

    public Point getParent() {
        return parent;
    }
}

class ConnectedCellInAGridSolver {

    Node[][] arrPoints;
    List<Node> parents;

    public int solve(int[][] arr) {
        init(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arrPoints[i][j].data == 1) {
                    Point parent = getClosestParent(i, j);
                    if (parent.isInvalid()) {
                        arrPoints[i][j].parent = new Point(i, j);
                        arrPoints[i][j].childCount++;
                    } else {
                        arrPoints[i][j].parent = parent;
                        arrPoints[parent.x][parent.y].childCount++;
                        Point ifChanged;
                        for (int x = i - 1; x <= i + 1; x++) {
                            for (int y = j - 1; y <= j + 1; y++) {
                                try {
                                    ifChanged = arrPoints[x][y].getParent();
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    ifChanged = new Point(-1, -1);
                                }
                                if (!ifChanged.isInvalid() && !ifChanged.equals(arrPoints[i][j].getParent())) {
                                    int tempx = arrPoints[ifChanged.x][ifChanged.y].childCount;
                                    arrPoints[x][y].parent = arrPoints[i][j].getParent();
                                    arrPoints[arrPoints[i][j].getParent().x][arrPoints[i][j].getParent().y].childCount += tempx;
                                }
                            }
                        }
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                max = Math.max(max, arrPoints[i][j].childCount);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arrPoints[i][j].getParent());
            }
            System.out.println();
        }
        return max;
    }

    private Point getClosestParent(int x, int y) {
        Point parent;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    parent = arrPoints[i][j].getParent();
                } catch (ArrayIndexOutOfBoundsException e) {
                    parent = new Point(-1, -1);
                }
                if (!parent.isInvalid()) {
                    return parent;
                }
            }
        }
        return new Point(-1, -1);
    }

    private void init(int[][] arr) {
        parents = new ArrayList<>();
        arrPoints = new Node[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arrPoints[i][j] = new Node(arr[i][j], new Point(-1, -1));
            }
        }
    }
}



















