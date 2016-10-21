package CPPrograms;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by mayur on 19/7/16.
 */
public class LittleShinoAndCoins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        String str = in.next();
        new LittleShinoAndCoinsSolver().solve(k, str);
    }
}

class LittleShinoAndCoinsSolver {

    public void solve(int k, String str) {
        Set<Character> set;
        // List<Character> list;
        int length = 0;
        for (int i = 0; i < str.length(); i++) {
            set = new HashSet<>();
            //list = new ArrayList<>();
            for (int j = i; j < str.length(); j++) {
                if (set.size() <= k) {
                    set.add(str.charAt(j));
                    // list.add(str.charAt(j));
                }
                if (set.size() == k) length++;
                if (set.size() > k) break;
            }
            //  System.out.println(list);
        }
        System.out.println(length);
    }
}
