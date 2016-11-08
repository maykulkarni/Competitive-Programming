package CPPrograms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kulkarni_my on 11/4/2016.
 */
public class BytelandianCoins {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        long test;
        try {
            while ((test = Long.parseLong(br.readLine())) != -1) {
                new BytelandianCoinsSolver().solve(test);
            }
        } catch (Exception e) {

        }
    }
}

class BytelandianCoinsSolver {
    static Map<Long, Long> map;

    static {
        map = new HashMap<Long, Long>();
    }

    public void solve(long coins) {
        long ans = sl(coins);
        System.out.println(ans);
    }

    private long sl(long coins) {
        if (coins == 0) {
            return 0;
        }
        if (map.containsKey(coins)) {
            return map.get(coins);
        } else {
            long max = Math.max(coins, sl(coins / 2) + sl(coins / 3) + sl(coins / 4));
            map.put(coins, max);
            return max;
        }
    }
}