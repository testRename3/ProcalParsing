package fx50.nodes;

import java.math.BigDecimal;

/**
 * Negation Node
 */
public class NegationNode implements CalculatorNode {
    private final CalculatorNode left;

    public NegationNode(CalculatorNode left) {
        this.left = left;
    }

    public BigDecimal evaluate() {
        return left.evaluate().negate();
    }

    public String toString() {
        return "(-" + left.toString() + ")";
    }
}
