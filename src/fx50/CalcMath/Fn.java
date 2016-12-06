package fx50.CalcMath;

import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Fn
 */
public class Fn {

    public static BigDecimal isInt(ArrayList<BigDecimal> bigDecimals) {
        BigDecimal n = bigDecimals.get(0);
        return new BigDecimal((n.setScale(0, BigDecimal.ROUND_DOWN))
                .compareTo(n.setScale(0, BigDecimal.ROUND_HALF_UP)) == 0 ? 1 : 0);
    }

    public static BigDecimal sqrt(ArrayList<BigDecimal> bigDecimals) {
        if (bigDecimals.get(0).compareTo(BigDecimal.ZERO) == 0)
            return new BigDecimal(0);
        return BigDecimalMath.sqrt(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal Rnd(ArrayList<BigDecimal> bigDecimals) {
        return bigDecimals.get(0).setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal Ran(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimal.valueOf(Math.random()).setScale(3, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal sin(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.sin(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal cos(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.cos(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal tan(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.tan(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal asin(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.asin(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal acos(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.acos(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal atan(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.atan(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal sinh(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.sinh(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal cosh(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.cosh(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal tanh(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.tanh(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }
}
