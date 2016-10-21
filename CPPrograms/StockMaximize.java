package CPPrograms;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayur on 11/7/16.
 */
public class StockMaximize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        for (int i = 0; i < testCase; i++) {
            int len = in.nextInt();
            long[] stocks = new long[len];
            for (int j = 0; j < len; j++) stocks[j] = in.nextInt();
            new StockMaximizeSolver().solve(stocks);
        }
    }
}

class StockMaximizeSolver {
    private BigInteger profit = BigInteger.ZERO;

    public void solve(long[] stocks) {
        if (isDescending(stocks) || stocks.length == 0) {
            System.out.println(profit);
        } else {
            int lastMaxIndex = getLastMaxIndex(stocks);
            BigInteger invest = BigInteger.ZERO;
            for (int i = 0; i < lastMaxIndex; i++) {
                invest = invest.add(BigInteger.valueOf(stocks[i]));
            }
            profit = profit.add(BigInteger.valueOf(lastMaxIndex * stocks[lastMaxIndex]).subtract(invest));
            solve(Arrays.copyOfRange(stocks, lastMaxIndex + 1, stocks.length));
        }
    }

    private boolean isDescending(long[] stocks) {
        for (int i = 0; i < stocks.length - 1; i++) {
            if (stocks[i] < stocks[i + 1])
                return false;
        }
        return true;
    }

    private int getLastMaxIndex(long[] stocks) {
        long max = Arrays.stream(stocks)
                .max()
                .getAsLong();
        for (int i = stocks.length - 1; i >= 0; i--) {
            if (stocks[i] == max) return i;
        }
        return -1;
    }
}