package fx50.nodes;

import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;

/**
 * Power Node
 */
public class PowerNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public PowerNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        BigDecimal lft = left.evaluate().setScale(15, BigDecimal.ROUND_HALF_UP);
        BigDecimal rgt = right.evaluate().setScale(15, BigDecimal.ROUND_HALF_UP);
        if (lft.compareTo(BigDecimal.ZERO) >= 0) {
            if (rgt.compareTo(BigDecimal.ZERO) == 0){
                throw new ArithmeticException("0^0 is undefined.");
            }
            return BigDecimalMath.pow(lft, rgt);
        } else {
            if (rgt.remainder(new BigDecimal(2)).compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimalMath.pow(lft.negate(), rgt);
            } else if (Math.abs(rgt.remainder(new BigDecimal(2)).compareTo(BigDecimal.ZERO)) == 1){
                return BigDecimalMath.pow(lft.negate(), rgt).negate();
            } else {
                throw new ArithmeticException("negative int power float is not implemented yet.");
            }
        }
    }

    public String toString() {
        return "(" + left.toString() + "^" + right.toString() + ")";
    }
}
