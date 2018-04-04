package CTCI.ChapterOne;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class IsPermutation {
    public static boolean isPermutation(String one, String two) {
        char[] one_chars = one.toCharArray();
        char[] two_chars = two.toCharArray();
        Arrays.sort(one_chars);
        Arrays.sort(two_chars);
        return Arrays.equals(one_chars, two_chars);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String one = in.next();
        String two = in.next();
        System.out.println(isPermutation(one, two));
    }
}