package Random;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayur on 4/9/16.
 */
public class Demo {
    public static void main(String args[]) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 3);
        Map<Integer, Integer> map2 = new HashMap<>(map);
        map.clear();
        map2.put(4, 5);
    }
}
