package CS97SI;

import java.util.Scanner;

/**
 * Created by mayur on 13/11/16.
 */

public class PloygonAreaFromCartesianPoints {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            String query = in.next();
            PolygonAreaSolver.solve(query);
        }
    }
}

class PolygonAreaSolver {
    public static void solve(String input) {
        int prevX = 0;
        int currX = 0;
        int currY = 0;
        int prevY = 0;
        long ans = 0;
        if (input.length() < 3) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < input.length() - 1; i++) {
            switch (Character.getNumericValue(input.charAt(i))) {
                case 1:
                    currX--;
                    currY--;
                    break;
                case 2:
                    currY--;
                    break;
                case 3:
                    currX++;
                    currY--;
                    break;
                case 4:
                    currX--;
                    break;
                case 5:
                    break;
                case 6:
                    currX++;
                    break;
                case 7:
                    currX--;
                    currY++;
                    break;
                case 8:
                    currY++;
                    break;
                case 9:
                    currX++;
                    currY++;
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            // update ans
            ans += ((prevX + currX) * (currY - prevY));
            prevX = currX;
            prevY = currY;
        }
        printAnswer(Math.abs(ans));
    }

    private static void printAnswer(long abs) {
        if (abs % 2 == 0) {
            System.out.println(abs / 2);
        } else {
            System.out.println(abs / 2 + ".5");
        }
    }
}
