package fx50.nodes;

import java.math.BigDecimal;

/**
 * Boolean Xor Node
 */
public class BooleanXorNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanXorNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + "xor" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        boolean leftComp = left.evaluate().compareTo(BigDecimal.ZERO) == 1;
        boolean rightComp = right.evaluate().compareTo(BigDecimal.ZERO) == 1;
        return new BigDecimal(
                (leftComp||rightComp)&&!(leftComp&&rightComp) ? 1 : 0
        );
    }
}
