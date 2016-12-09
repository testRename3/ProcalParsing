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
        return BigDecimalMath.pow(
                left.evaluate().setScale(15, BigDecimal.ROUND_HALF_UP),
                right.evaluate().setScale(15, BigDecimal.ROUND_HALF_UP)
        );
    }

    public String toString() {
        return "(" + left.toString() + "^" + right.toString() + ")";
    }
}
