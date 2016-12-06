package fx50.CalcMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * CalcMath
 */
public class CalcMath {
    public static final BigDecimal epsilon = new BigDecimal("1E-200");
    public static final MathContext precision = new MathContext(15, RoundingMode.HALF_UP);

    public static BigDecimal factorial(BigDecimal n) {
        if (n.compareTo(new BigDecimal(0)) == 0) return new BigDecimal(1);
        return n.multiply(factorial(n.subtract(new BigDecimal(1))));
    }

    public static BigDecimal permutation (BigDecimal n, BigDecimal r) {
        BigDecimal x = n.round(new MathContext(0));
        BigDecimal y = r.round(new MathContext(0));
        return factorial(x).multiply(factorial(x.subtract(y)));
    }

    public static BigDecimal combination (BigDecimal n, BigDecimal r) {
        BigDecimal y = r.round(new MathContext(0));
        return permutation(n, r).divide(factorial(y), precision);
    }

    public static boolean isInt (BigDecimal n) {
        return (n.setScale(0, BigDecimal.ROUND_DOWN)).equals(n);
    }

    public static void listAllConstants() {
        for (Constants c: Constants.values()) {
            int indentation = 12;
            System.out.print(c.name());
            for (int i = c.name().length() + 1; i < 12; i++) {
                System.out.print(" ");
            }
            System.out.println(c.name);
            for (int i = 1; i < 12; i++) {
                System.out.print(" ");
            }
            System.out.println(c.value + " " + c.unit);
        }
    }

}