package Random;


import java.util.Map;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void ins(Map<Integer, Integer> map, int key) {
        map.compute(key, (k, v) -> v == null ? 1 : v + 1);
    }
    public static void main(String args[]) {

    }

}
