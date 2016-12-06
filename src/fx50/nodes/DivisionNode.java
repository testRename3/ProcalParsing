package fx50.nodes;

import fx50.CalcMath.CalcMath;

import java.math.BigDecimal;
/**
 * Division Node
 */
public class DivisionNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;


    public DivisionNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().divide(right.evaluate(), CalcMath.precision);
    }

    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
