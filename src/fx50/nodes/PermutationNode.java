package fx50.nodes;

import fx50.CalcMath.CalcMath;

import java.math.BigDecimal;

/**
 * Permutation Node
 */
public class PermutationNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public PermutationNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult = left.evaluate();
        BigDecimal rightResult = right.evaluate();
        if (leftResult.compareTo(rightResult) == -1 ||
                leftResult.compareTo(BigDecimal.ZERO) == -1 ||
                rightResult.compareTo(BigDecimal.ZERO) == -1) {
            throw new ArithmeticException("Math error: n and r must be positive non-zero integers and n must be greater or equals to r");
        }
        return CalcMath.permutation(left.evaluate(),right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "P" + right.toString() + ")";
    }
}
