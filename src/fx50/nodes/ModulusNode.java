package fx50.nodes;

import java.math.BigDecimal;

/**
 * Modulus Node
 */
public class ModulusNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public ModulusNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().remainder(right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "mod" + right.toString() + ")";
    }
}
