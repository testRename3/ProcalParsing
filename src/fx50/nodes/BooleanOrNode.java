package fx50.nodes;

import java.math.BigDecimal;

/**
 * Boolean Or Node
 */
public class BooleanOrNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanOrNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")or(" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        return new BigDecimal(left.evaluate().compareTo(BigDecimal.ZERO) == 1 ||
                right.evaluate().compareTo(BigDecimal.ZERO) == 1 ? 1 : 0);
    }
}
