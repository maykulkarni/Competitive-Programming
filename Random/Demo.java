package Random;

import java.math.BigInteger;

public class Demo {

    public static double step(double xn, double alpha) {
        return (0.5 * xn) + (alpha / (2 * xn));
    }

    public static void main(String[] args) {
        System.out.println("Original : \t1000000567856785678");
        BigInteger ans = approxSqrt(new BigInteger("1000000567856785678"));
        System.out.println("Sqrt : \t\t" + ans);
        System.out.println("Test : \t\t" + ans.multiply(ans));
    }

    public static BigInteger approxSqrt(BigInteger val) {
        BigInteger initialSeed = val.divide(BigInteger.valueOf(10));
        BigInteger previousStep = BigInteger.valueOf(10);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger currentStep = initialSeed;
        while (!currentStep.equals(previousStep)) {
            previousStep = currentStep;
            currentStep = currentStep.divide(two).add(val.divide(two.multiply(currentStep)));
        }
        return currentStep;
    }
}