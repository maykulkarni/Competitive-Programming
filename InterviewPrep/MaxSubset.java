package InterviewPrep;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Created by mayur on 9/7/16.
 */
public class MaxSubset {
    public static void main(String[] ar) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int x = 10;
        new MaxSubsetSolver().solve(a, x);
    }
}

class MaxSubsetSolver {

    public void solve(int[] arr, int x) {
        LinkedList<Integer> queue = new LinkedList<>();
        Arrays.stream(arr)
                .sorted()
                .forEach(queue::add);
        System.out.println(queue);
        boolean[][] isPossible = new boolean[arr.length][];
        for (int i = 0; i < arr.length; i++) isPossible[i] = new boolean[x + 1];
        for (int i = 0; i < arr.length; i++) {
            int currNumber = queue.poll();
            isPossible[i][0] = true;
            isPossible[i][currNumber] = true;
            if (i > 0) {
                for (int j = 0; j < currNumber; j++) {
                    isPossible[i][j] = isPossible[i - 1][j];
                }
                for (int j = currNumber + 1; j <= x; j++) {
                    if (j - currNumber >= 0 && (isPossible[i - 1][j - currNumber] || isPossible[i - 1][j])) {
                        isPossible[i][j] = true;
                    }
                }
            }
        }
        int k = 0;
        Stream.of(isPossible)
                .forEach(s -> System.out.println(Arrays.toString(s) + '\t'));
        if (isPossible[arr.length - 1][x]) {
            int currNum = x;
            int currI = arr.length - 1;
            int currJ = x;
            while (true) {
                if (currI == 0 && currJ != 0) {
                    System.out.println(arr[0]);
                    break;
                } else if (currI == 0 && currJ == 0) {
                    break;
                }
                if (isPossible[currI - 1][currJ]) {
                    currI--;
                } else {
                    System.out.print(arr[currI] + " ");
                    currJ -= arr[currI];
                    currI--;
                }
            }
        }
    }
}