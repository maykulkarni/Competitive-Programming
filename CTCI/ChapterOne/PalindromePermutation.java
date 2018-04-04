package CTCI.ChapterOne;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class PalindromePermutation {
    public static void isPalindromePermutation(String input) {
        HashMap<Character, Integer> characters = new HashMap<>();
        for (char chars : input.toCharArray()) {
            if (characters.containsKey(chars)) {
                int count = characters.get(chars);
                characters.put(chars, count + 1);
            } else {
                characters.put(chars, 1);
            }
        }

        boolean oddCharDetected = false;
        char oddChar = '\n';
        for (char chars : characters.keySet()) {
            if (characters.get(chars) % 2 != 0) {
                if (oddCharDetected) {
                    if (oddChar == chars) {
                        continue;
                    } else {
                        System.out.println("NO");
                        return;
                    }

                }
                oddCharDetected = true;
                oddChar = chars;
            }
        }

        System.out.println("YES");
    }

    public static void main(String[] args) {
        isPalindromePermutation(new Scanner(System.in).nextLine());
    }
}