package fx50.nodes;

import java.math.BigDecimal;

/**
 * Subtraction Node
 */
public class SubtractionNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public SubtractionNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().subtract(right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}
