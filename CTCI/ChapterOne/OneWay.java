package CTCI.ChapterOne;

import java.util.Scanner;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class OneWay {
    private static boolean oneWay(String one, String two) {
        if (Math.abs(one.length() - two.length()) > 1) {
            System.out.println("NO: 0");
            return false;
        }
        if (one.length() != two.length()) {
            // INSERT CASE AND REMOVE CASE
            if (two.length() > one.length()) {
                String temp = one;
                one = two;
                two = temp;
            }
            int indexOne = 0;
            int indexTwo = 0;
            boolean missedOne = false;
            while (indexOne < one.length() && indexTwo < two.length()) {
                if (one.charAt(indexOne) == two.charAt(indexTwo)) {
                    indexOne++;
                    indexTwo++;
                } else {
                    if (missedOne) {
                        // second miss
                        System.out.println("NO: 1");
                        return false;
                    }
                    missedOne = true;
                    indexOne++;
                }
            }
            System.out.println("YES: 1");
            return true;
        } else {
            // REPLACE CASE
            int index = 0;
            while (index < one.length() && one.charAt(index) == two.charAt(index)) index++;
            if (index == one.length()) {
                System.out.println("YES: 2");
                return true;
            }
            index++;
            while (index < one.length() && one.charAt(index) == two.charAt(index)) index++;
            if (index == one.length()) {
                System.out.println("YES: 3");
                return true;
            }
            System.out.println("NO: 2");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String one = in.next();
        String two = in.next();
        oneWay(one, two);
    }
}