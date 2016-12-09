package Random;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String args[]) {
//        int number = 9;

        for (int number = 0; number < 1e5; number++) {
            long ans = 0;
            for (int i = 2; i <= number; i++) {
                int x = -1;
                int temp = number;
                while (temp > 0) {
                    x = temp - ((temp / i) * i);
                    temp = temp / i;
                }
                if (x == 1) ans++;
            }
            if (ans >= number * 0.9) {
                System.out.println();
                System.out.println(number + " : " + ans);
            } else
                System.out.print("\rnot " + number + " : " + ans);
        }
    }
}
