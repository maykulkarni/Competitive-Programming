package Random;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(findMax(30, 1, 6));
    }

    public static int findMax(int questions, int guessed, int actual) {
        boolean[] actualarr = new boolean[questions];
        for (int i = 0; i < actual; i++) actualarr[i] = true;
        boolean[] guess = new boolean[questions];
        for (int i = 0; i < guessed; i++) guess[i] = true;
        int marks = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == actualarr[i]) {
                marks++;
            }
        }
        return marks;
    }
}