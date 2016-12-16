package fx50.CalcMath;

import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Fn
 */
public class Fn {
    //TODO change setScale to sigfig

    public static BigDecimal isInt(ArrayList<BigDecimal> bigDecimals) {
        BigDecimal n = bigDecimals.get(0);
        return new BigDecimal(n.compareTo(n.setScale(0, BigDecimal.ROUND_HALF_UP)) == 0 ? 1 : 0);
    }

    public static BigDecimal sqrt(ArrayList<BigDecimal> bigDecimals) {
        if (bigDecimals.get(0).compareTo(BigDecimal.ZERO) == 0)
            return new BigDecimal(0);
        return BigDecimalMath.sqrt(bigDecimals.get(0).setScale(200, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    //TODO param will generate 0-that number
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
        //TODO fix precision error
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

    public static BigDecimal asinh(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.asinh(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal acosh(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.acosh(bigDecimals.get(0).setScale(15, BigDecimal.ROUND_HALF_UP));
    }

    public static BigDecimal atanh(ArrayList<BigDecimal> bigDecimals) {
        // 0.5 ln ( (1+x)/(1-x) )
        return BigDecimalMath.log(BigDecimal.ONE
                .add(bigDecimals.get(0))
                .setScale(16, BigDecimal.ROUND_HALF_UP)
                .divide(BigDecimal.ONE.subtract(bigDecimals.get(0)), BigDecimal.ROUND_HALF_UP)
        ).divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal log(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.log(bigDecimals.get(0).setScale(16, BigDecimal.ROUND_HALF_UP))
                .divide(BigDecimalMath.log(BigDecimal.TEN.setScale(15, BigDecimal.ROUND_HALF_UP)), BigDecimal.ROUND_HALF_UP)
                .setScale(15, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal ln(ArrayList<BigDecimal> bigDecimals) {
        return BigDecimalMath.log(bigDecimals.get(0).setScale(16, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP);
    }

}
