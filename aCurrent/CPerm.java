package aCurrent;

import java.math.BigInteger;

/**
 * Created by mayur on 4/11/16.
 */
public class CPerm {
//    static List<int[]> perms;
//
//
//
//    public static void generatePermutations(int[] arr, int l, int r) {
//        if (l == r) {
//            perms.add(arr.clone());
//        } else {
//            for (int i = l; i <= r; i++) {
//                swap(arr, i, l);
//                generatePermutations(arr, l + 1, r);
//                swap(arr, i, l);
//            }
//        }
//    }
//
//    private static void swap(int[] arr, int a, int b) {
//        int temp = arr[a];
//        arr[a] = arr[b];
//        arr[b] = temp;
//    }
//    public static void solve(int lim) {
//        perms = new ArrayList<>();
//        int arr[] = new int[lim];
//        for (int i = 1; i <= lim; i++) {
//            arr[i - 1] = i;
//        }
//        generatePermutations(arr, 0, lim - 1);
//        Set<int[]> set = new HashSet<>();
//        for (int[] currentPem : perms) {
//            outer:
//            for (int i = 1; i < currentPem.length - 1; i++) {
//                boolean dona = true;
//                boolean donb = true;
//                for (int j = i; j < currentPem.length - 1; j++) {
//                    if (currentPem[j] < currentPem[j + 1]) {
//                        dona = false;
//                    }
//                }
//                for (int j = 2; j <= i; j++) {
//                    if (currentPem[j] < currentPem[j - 1]) {
//                        donb = false;
//                    }
//                }
//                if (dona && donb)
//                    set.add(currentPem);
//            }
//        }
//        System.out.println(set.size());
//    }

    public static void main(String[] args) {
        BladeReader in = new BladeReader(System.in);
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            solve(in.next());
        }
    }

    private static void solve(String next) {
        BigInteger mod = new BigInteger("10000000007");
        BigInteger n = new BigInteger(next);
        BigInteger two = BigInteger.valueOf(2);
        n = n.subtract(two);
        two = two.modPow(n, mod);
        two = two.subtract(BigInteger.ONE);
        two = two.multiply(n.add(BigInteger.valueOf(2)));
        two = two.mod(mod);
        System.out.println(two);
    }
}
