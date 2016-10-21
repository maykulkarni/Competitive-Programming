package CPPrograms;

import java.util.Scanner;

public class SamAndSubstring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip = in.next();
        new SamAndSubstringSolver().solve(ip);
    }
}

class SamAndSubstringSolver {

    public void solve(String ip) {
        long mod = 1000000007;
        long ans = 0;
        long ones = 1;
        for (int i = ip.length() - 1, multiplier = ip.length(); i >= 0; i--, multiplier--) {
            int currInt = Character.getNumericValue(ip.charAt(i));
            ans = (ans + (currInt * ones * multiplier) % mod) % mod;
            ones = (ones * 10 + 1) % mod;
        }
        System.out.println(ans);
    }
}