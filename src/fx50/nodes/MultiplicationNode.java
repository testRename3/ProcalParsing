package fx50.nodes;

import java.math.BigDecimal;

/**
 * Multiplication Node
 */
public class MultiplicationNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public MultiplicationNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().multiply(right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}
