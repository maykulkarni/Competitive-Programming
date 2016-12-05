package Utils;

/**
 * Created by Mayur Kulkarni on 11/14/2016.
 * Email : mayurkulkarni012@gmail.com
 */
public class BitManipulation {
    public static int setBit(int x, int index) {
        if (index < 0 || index > 30) {
            System.err.println("Invalid range : " + index);
            throw new RuntimeException();
        }
        x |= 1 << index;
        return x;
    }

    public static int clearBit(int x, int index) {
        if (index < 0 || index > 30) {
            System.err.println("Invalid range : " + index);
            throw new RuntimeException();
        }
        x &= ~(1 << index);
        return x;
    }

    public static boolean isSet(int x, int index) {
        return !(index < 0 || index > 30) && (x & (1 << index)) != 0;
    }

    public static int getLowestSetBitIndex(int x) {
        if (x == 0) return -1;
        int temp = x & ~(x - 1);
        double ans = Math.log(temp) / Math.log(2);
        return (int) ans;
    }

    public static int getHighestSetBitIndex(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    public static boolean isPowerOfTwo(int x) {
        return x != 0 && clearBit(x, getLowestSetBitIndex(x)) == 0;
    }

    public static void iterateOverSubset() {
        int maxmask = 1 << 5;
        int N = 5;
        String[] arr = {"1", "2", "3", "4", "5"};
        int badmask = 0;
        badmask |= 1 << 4;
        badmask |= 1 << 3;
        for (int i = (maxmask - 1) & ~badmask; i > 0; i = (i - 1) & ~badmask) {
            for (int j = 0; j < 5; j++) {
                if ((i & (1 << j)) > 0) System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
    }

}


class Test {
    public static void main(String[] args) {
        System.out.println(BitManipulation.getHighestSetBitIndex(412));
    }
}
