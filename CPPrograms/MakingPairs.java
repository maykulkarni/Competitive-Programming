package CPPrograms;

import java.util.Scanner;

/**
 * Created by mayur on 14/7/16.
 */
public class MakingPairs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = in.nextInt();
        new Solver().get(arr);
    }
}

class Solver {
    public int get(int[] card) {
        int ans = 0;
        for (int i = 0; i < card.length; i++) {
            ans += Math.floor(card[i] / 2);
        }
        System.out.println(ans);
        return ans;
    }
}