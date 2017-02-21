package Random;

import java.math.BigDecimal;

public class Demo {

    public static double step(double xn, double alpha) {
        return (0.5 * xn) + (alpha / (2 * xn));
    }

    public static void main(String[] args) {
        System.out.println("Original : \t3");
        BigDecimal ans = approxSqrt(new BigDecimal("49"));
        System.out.println("Sqrt : \t\t" + ans);
        System.out.println("Test : \t\t" + ans.multiply(ans));
    }

    public static BigDecimal approxSqrt(BigDecimal val) {
        BigDecimal valBD = val;
        BigDecimal initialSeed = valBD.divide(BigDecimal.valueOf(10), 15, BigDecimal.ROUND_FLOOR);
        BigDecimal previousStep = BigDecimal.valueOf(10);
        BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal currentStep = initialSeed;
        while (!currentStep.equals(previousStep)) {
            previousStep = currentStep;
            currentStep = currentStep.divide(two, 15, BigDecimal.ROUND_FLOOR).add(valBD.divide(two.multiply(currentStep), 15, BigDecimal.ROUND_FLOOR));
        }
        return currentStep;
    }
}