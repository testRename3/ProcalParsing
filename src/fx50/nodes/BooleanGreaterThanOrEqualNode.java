package fx50.nodes;

import java.math.BigDecimal;

/**
 * Boolean Greater Than Or Equal Node
 */
public class BooleanGreaterThanOrEqualNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanGreaterThanOrEqualNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ">=" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        return new BigDecimal(left.evaluate().compareTo(right.evaluate()) >= 0 ? 1 : 0);
    }
}
