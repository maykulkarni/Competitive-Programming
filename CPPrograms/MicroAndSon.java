package CPPrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mayur on 16/10/16.
 */
public class MicroAndSon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = in.nextInt(); i > 0; i--) {
            String startTime = in.next();
            String endTime = in.next();
            System.out.println(Solver_zz.solve(startTime, endTime));
        }
    }
}

class Solver_zz {

    public static int solve(String startTime, String endTime) {
        List<String> candidates = generateCandidates(startTime, endTime);
        int ans = 0;
        for (String candidate : candidates) {
            if (candidate.equals(new StringBuilder(candidate).reverse().toString())) {
                ans++;
            }
        }
        return ans;
    }

    public static List<String> generateCandidates(String startTime, String endTime) {
        List<String> list = new ArrayList<>();
        int startHour = Integer.parseInt(new StringBuilder().append(startTime.charAt(0)).append(startTime.charAt(1)).toString());
        int endHour = Integer.parseInt(new StringBuilder().append(endTime.charAt(0)).append(endTime.charAt(1)).toString());
        int startMin = Integer.parseInt(new StringBuilder().append(startTime.charAt(2)).append(startTime.charAt(3)).toString());
        int endMin = Integer.parseInt(new StringBuilder().append(endTime.charAt(2)).append(endTime.charAt(3)).toString());
        //System.out.println(startHour + " " + startMin + " " + endHour + " " + endMin);
        boolean firstTime = true;
        for (int i = startHour; i <= endHour; i++) {
            for (int j = firstTime ? startMin : 0; (i == endHour && j <= endMin) || (j < 60 && i < endHour); j++) {
                firstTime = false;
                list.add(String.format("%02d", i) + " " + String.format("%02d", j));
            }
        }
        //System.out.println(list);
        return list;
    }
}