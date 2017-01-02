package fx50.nodes;

import java.math.BigDecimal;

/**
 * Boolean Lesser Than Node
 */
public class BooleanLesserThanNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanLesserThanNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")<(" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        return new BigDecimal(left.evaluate().compareTo(right.evaluate()) == -1 ? 1 : 0);
    }
}
