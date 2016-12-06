package fx50.nodes;

import java.math.BigDecimal;

/**
 * Addition Node
 */
public class AdditionNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public AdditionNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().add(right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}
