package CPPrograms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        String inputString = in.next();
        new IDKWwhatSolver().solve(length, inputString);
    }
}

class IDKWwhatSolver {
    public int gCount, cCount, aCount, tCount;
    public int gTotal, cTotal, aTotal, tTotal;
    Map<Character, Integer> map;

    IDKWwhatSolver() {
        gCount = cCount = aCount = tCount = 0;
        gTotal = cTotal = aTotal = tTotal = 0;
        map = new HashMap<>();
        map.put('A', 0);
        map.put('G', 0);
        map.put('C', 0);
        map.put('T', 0);
    }

    public void init(String ip) {
        for (int i = 0; i < ip.length(); i++) {
            //System.out.println("val for " + ip.charAt(i) + map.get(ip.charAt(i)));
            int val = map.get(ip.charAt(i));
            map.put(ip.charAt(i), val + 1);
        }
    }

    public boolean isValid(String ip) {
        int allowedLength = ip.length() / 4;
        if (map.get('A') <= allowedLength && map.get('C') <= allowedLength && map.get('G') <= allowedLength && map.get('T') <= allowedLength) {
            return true;
        } else {
            return false;
        }
    }
    // public void incr(char currChar) {
    //  map.put(currChar, map.get(currChar) + 1);
    // }
    // public void decr(char currChar) {
    //  map.put(currChar, map.get(currChar) - 1);
    // }
// 8
// GAAATAAA

    public void solve(int length, String ip) {
        init(ip);
        int windowStart = 0;
        int windowEnd = 1;
        // int whichSideToIncrease = windowEnd;
        boolean growingSide = true;
        map.put(ip.charAt(0), map.get(ip.charAt(0)) - 1);
        int minSoFar = Integer.MAX_VALUE;
        while (windowEnd < ip.length() && windowStart < windowEnd) {
            //System.out.println(growingSide + " " + windowStart + " " + windowEnd);
            if (growingSide) {
                while (!isValid(ip) && windowEnd < ip.length()) {
                    map.put(ip.charAt(windowEnd), map.get(ip.charAt(windowEnd)) - 1);
                    windowEnd++;
                }
                growingSide = false;
            } else {
                while (isValid(ip) && windowStart < ip.length()) {
                    int val = map.get(ip.charAt(windowStart));
                    map.put(ip.charAt(windowStart), val + 1);
                    windowStart++;
                    if (windowEnd - windowStart + 1 < minSoFar) minSoFar = windowEnd - windowStart + 1;
                }
                growingSide = true;
            }
        }
        System.out.println(minSoFar);
    }
}