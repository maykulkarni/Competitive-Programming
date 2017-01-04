package Random;

/**
 * Created by mayur on 4/9/16.
 */
class temp {
    int x;
}
public class Demo {
    public static void chage(temp t) {
        t.x = 1000;
    }

    public static void main(String args[]) {
        temp t = new temp();
        t.x = 10;
        //chage(t);
        System.out.println(t.x);
    }
}
