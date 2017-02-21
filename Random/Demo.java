package Random;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Demo {

    public static double step(double xn, double alpha) {
        return (0.5 * xn) + (alpha / (2 * xn));
    }

    public static void main(String[] args) {
        System.out.println("Original : \t1000000");
        BigInteger ans = approxSqrt(new BigInteger("1000000"));
        System.out.println("Sqrt : \t\t" + ans);
        System.out.println("Test : \t\t" + ans.multiply(ans));
    }

    public static BigInteger approxSqrt(BigInteger val) {
        BigDecimal valBD = new BigDecimal(val);
        BigDecimal initialSeed = valBD.divide(BigDecimal.valueOf(10), 15, BigDecimal.ROUND_FLOOR);
        BigDecimal previousStep = BigDecimal.valueOf(10);
        BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal currentStep = initialSeed;
        while (!currentStep.equals(previousStep)) {
            previousStep = currentStep;
            currentStep = currentStep.divide(two, 15, BigDecimal.ROUND_FLOOR).add(valBD.divide(two.multiply(currentStep), 15, BigDecimal.ROUND_FLOOR));
        }
        return currentStep.toBigInteger();
    }
}