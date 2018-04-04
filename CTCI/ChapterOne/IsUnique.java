package CTCI.ChapterOne;

import java.util.BitSet;
import java.util.Scanner;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class IsUnique {
    public static void isUnique(String input) {
        BitSet mask = new BitSet();
        boolean isUnique = true;
        for (char i : input.toCharArray()) {
            int ascii = (int) i;
            if (mask.get(ascii)) {
                System.out.println("Duplicate char: " + i);
                isUnique = false;
            } else {
                mask.set(ascii);
            }
        }
        if (isUnique) System.out.println("Is Unique!");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inp = in.next();
        isUnique(inp);
    }
}