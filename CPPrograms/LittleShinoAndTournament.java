package CPPrograms;

import MyImplementations.InputReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 20/7/16.
 */
public class LittleShinoAndTournament {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int numberOfFighters = in.nextInt();
        int queries = in.nextInt();
        int[] dp = new int[numberOfFighters];
        for (int i = 0; i < numberOfFighters; i++) dp[i] = in.nextInt();
        Map<Integer, Integer> map = new LittleShinoAndTournamentSolver().solve(dp, numberOfFighters);
        int[] queriesa = new int[queries];
        for (int i = 0; i < queries; i++) queriesa[i] = in.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queries; i++) {
            sb.append(map.get(queriesa[i]) + "\n");
        }
        System.out.println(sb.toString());
    }
}

class LittleShinoAndTournamentSolver {

    public int[] dp;

    private int getNextIndex(int from) {
        if (from >= dp.length) return -1;
        for (int i = from; i < dp.length; i++) {
            if (dp[i] != -1) {
                return i;
            }
        }
        return -1;
    }

    public Map<Integer, Integer> solve(int[] dp, int numberOfFighters) {
        this.dp = dp;
        Map<Integer, Integer> map = new HashMap<>(numberOfFighters + 2);
        for (int i = 1; i <= numberOfFighters; i++) map.put(i, 0);
        int currPtr = 0;
        int totalPlayers = numberOfFighters;
        while (totalPlayers > 1) {
            int fir = getNextIndex(currPtr);
            int sec = getNextIndex(fir + 1);
            if (fir == -1 || sec == -1) {
                currPtr = 0;
            } else {
                currPtr = sec + 1;
                map.put(fir + 1, map.get(fir + 1) + 1);
                map.put(sec + 1, map.get(sec + 1) + 1);
                if (dp[fir] > dp[sec]) {
                    dp[sec] = -1;
                } else {
                    dp[fir] = -1;
                }
                totalPlayers--;
            }
        }
        return map;
    }
}