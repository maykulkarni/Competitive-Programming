package CPPrograms;

import Utils.BladeReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mayur on 4/11/16.
 */


public class KxKGrid {
    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int testCases = in.nextInt();
        for (int i = 0; i < testCases; i++) {
            new MainSolver().solve(in.nextInt());
        }
    }
}

class Number {
    int x, y;

    Number(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class MainSolver {
    List<List<Integer>> grid;
    int len;

    public Number minOneCoordinates(List<List<Integer>> currentGrid) {
        int minDistanceFromMid = 999999999;
        Number minOneNumber = new Number(-1, -1);
        int mid = (len + 1) / 2 - 1;
        for (int i = 0; i < len; i++) {
            List<Integer> currList = new ArrayList<>(currentGrid.get(i));
            for (int j = 0; j < len; j++) {
                if (currList.get(j) == 1) {
                    if (!((i == mid) && (j == mid))) {
                        int currentDistance = Math.abs(i - mid) + Math.abs(j - mid);
                        if (currentDistance < minDistanceFromMid) {
                            minDistanceFromMid = currentDistance;
                            minOneNumber.x = i;
                            minOneNumber.y = j;
                        }
                    }
                    //continue;
                }
            }
        }
        return minOneNumber;
    }

    public int nearestOneDistance(List<List<Integer>> currentGrid) {
        Number minOneCoordinates = minOneCoordinates(currentGrid);
        int midX = (len + 1) / 2 - 1;
        int midY = (len + 1) / 2 - 1;
        int cost = Math.abs(midX - minOneCoordinates.x) + Math.abs(midY - minOneCoordinates.y);
        System.out.println("Nearest X :" + minOneCoordinates.x + " Y : " + minOneCoordinates.y + " cost : " + cost);
        return cost;
    }

    public void printGrid(List<List<Integer>> currGrid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sb.append(currGrid.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void solve(int len) {
        if (len == 1) {
            System.out.println(1);
            return;
        }
        int mid = (len + 1) / 2 - 1;
        this.len = len;
        grid = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= len; i++) {
            tmp.add(i);
        }
        for (int i = 0; i < len; i++) {
            grid.add(tmp);
            LinkedList tmpx = new LinkedList(tmp);
            int last = (int) tmpx.removeLast();
            tmpx.addFirst(last);
            tmp = tmpx;
        }
        List<List<Integer>> currentGrid = grid;
        List<List<Integer>> minimumGrid = null;
        int maximumDistance = -1;
        for (int i = 0; i < len; i++) {
            //if (currentGrid.get(mid).get(mid) != 1) {
            printGrid(currentGrid);
            int currentDistance = nearestOneDistance(currentGrid);
            System.out.println("***");
            if (currentDistance > maximumDistance) {
                minimumGrid = currentGrid;
                maximumDistance = currentDistance;
            }
            //}
            List<List<Integer>> tmpz = gridClone(currentGrid);
            List<Integer> tmpLast = (LinkedList) ((LinkedList) tmpz).removeLast();
            ((LinkedList) tmpz).addFirst(tmpLast);
            currentGrid = tmpz;
        }
        //System.out.println("final answer");
        printGrid(minimumGrid);
        System.out.println("final cost : " + maximumDistance);
    }

    private List<List<Integer>> gridClone(List<List<Integer>> currentGrid) {
        List<List<Integer>> toReturn = new LinkedList<>();
        for (List<Integer> list : currentGrid) {
            toReturn.add(new LinkedList<>(list));
        }
        return toReturn;
    }
}






















